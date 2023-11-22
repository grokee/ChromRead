import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonExtractor {

    private ArrayList<Integer> quot = new ArrayList<>();

    private ArrayList<Integer> colon = new ArrayList<>();

    private ArrayList<Integer> leftSq = new ArrayList<>();

    private ArrayList<Integer> rightSq = new ArrayList<>();

    private ArrayList<Integer> leftCur = new ArrayList<>();

    private ArrayList<Integer> rightCur = new ArrayList<>();

    public void symbolIndexing(String data) {
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == 34) {
                quot.add(i);
            }
            if (data.charAt(i) == 58) {
                colon.add(i);
            }
            if (data.charAt(i) == 58) {
                colon.add(i);
            }
            if (data.charAt(i) == 91) {
                leftSq.add(i);
            }
            if (data.charAt(i) == 93) {
                rightSq.add(i);
            }
            if (data.charAt(i) == 123) {
                leftCur.add(i);
            }
            if (data.charAt(i) == 125) {
                rightCur.add(i);
            }

        }
    }

    public Map<Integer, Integer> getListIndexes(ArrayList<Integer> openIndexes, ArrayList<Integer> closeIndexes) {
        Map<Integer, Integer> lists = new TreeMap<>();
        if (closeIndexes.size() == openIndexes.size()) {
            for (int i = 0; i < closeIndexes.size(); i++) {
                for (int j = 0; j < openIndexes.size(); j++) {
//                    System.out.println(i + " " + j);
                    if (closeIndexes.get(i) > openIndexes.get(j) && !lists.containsValue(openIndexes.get(j))) {
                        if (lists.containsKey(closeIndexes.get(i))) {
                            lists.replace(closeIndexes.get(i), openIndexes.get(j));
                        } else {
                            lists.put(closeIndexes.get(i), openIndexes.get(j));
                        }
                    }
                }
            }
        } else {
            System.out.println("Error");
        }
        return lists;
    }

    public String getNameForList(String data, Integer startIndex) {
        String listName = "";
        Pattern pattern = Pattern.compile(".*\"([a-zA-Z_0-9]+)\"[\s]?:.*$");
        Matcher matcher = pattern.matcher(data.substring(0, startIndex));
        if (matcher.find()) {
            listName = matcher.group(1);
        }
        return listName;

    }

    public double[] getArrayFromString(String stringData) {

        String[] stringArray = stringData.split(",");
        double[] doubleArray = new double[stringArray.length];
        if (stringArray.length != 0) {
            for (int i = 0; i < stringArray.length; i++) {
                doubleArray[i] = Double.parseDouble(stringArray[i]);
                System.out.println(doubleArray[i]);
            }
        } else {
            doubleArray[0] = 0;
        }
        return doubleArray;
    }


    public static void main(String[] args) throws IOException {
        String path = "D:\\Java\\ChromRead\\normal_phase.txt";
//        FileReader file = new FileReader(path);
        String data = new String(Files.readAllBytes(Paths.get(path)));
//        System.out.println(data);
        JsonExtractor je = new JsonExtractor();
        je.symbolIndexing(data);

        System.out.println(je.leftSq.toString());
        System.out.println(je.rightSq.toString());
        Map<Integer, Integer> listOfList = je.getListIndexes(je.leftSq, je.rightSq);
        Map<Integer, Integer> listOfObject = je.getListIndexes(je.leftCur, je.rightCur);
        System.out.println(listOfList.toString());
        System.out.println(listOfObject.toString());
        Iterator<Map.Entry<Integer, Integer>> it = listOfList.entrySet().iterator();
        Map<String, double[]> listArray = new TreeMap<>();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = it.next();
//            listString.add(data.substring(entry.getValue()+1, entry.getKey()));
            String subString = data.substring(entry.getValue() + 1, entry.getKey());
            if (!subString.isEmpty()) {
                listArray.put(je.getNameForList(data, entry.getValue()), je.getArrayFromString(subString));
            }
        }


    }


}
