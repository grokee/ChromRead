import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonExtractor {

    private ArrayList<String> stringArrayList;

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

    public ArrayList<String> getListOfString(String data) {
        ArrayList<String> stringList = new ArrayList<>();
        this.symbolIndexing(data);
        colon.add(data.length());
        for (int i = 1; i < colon.size(); i++) {
            String listBetweenColon;
            listBetweenColon = data.substring(colon.get(i - 1), colon.get(i));
            if (!getStringBetweenBrackets(listBetweenColon).isEmpty()) {
                stringList.addAll(getStringBetweenBrackets(listBetweenColon));
            } else {
                Pattern pattern = Pattern.compile("([0-9.]+)");
                Matcher matcher = pattern.matcher(listBetweenColon);
                if (matcher.find()) {
                    stringList.add(matcher.group(1));
                }
            }
        }
        return stringList;
    }


    public ArrayList<String> getExtendedList(ArrayList<String> inputStringList) {
        ArrayList<String> outputStringList = new ArrayList<>();
        ArrayList<Integer> listSize = new ArrayList<>();
        Comparator<String> lengthComparator = (a, b) -> Integer.compare(a.length(), b.length());
        inputStringList.sort(lengthComparator.reversed());
        for (String string : inputStringList) {
            String[] splittedString = string.split(",");
            if (splittedString.length > 1) {
                outputStringList.add(string);
                listSize.add(splittedString.length);
            } else {
                double value = Double.parseDouble(string);
                for (int length : listSize) {
                    String[] divideArray = new String[length];
                    String[] multiplyArray = new String[length];
                    for (int i = 0; i < length; i++) {
                        if (value != 0) {
                            divideArray[i] = Double.toString(1 / value * i);
                        }
                        multiplyArray[i] = Double.toString(value * i);
                    }
                    outputStringList.add(String.join(",", divideArray));
                    outputStringList.add(String.join(",", multiplyArray));
                }
            }
        }
        return outputStringList;
    }

    public Boolean checkStringHomogenity(String string) {
        Boolean stringIsHomogenic = true;
        try {
            String[] splittedString = string.split(",");
            for (String peace : splittedString) {
                try {
                    double d = Double.parseDouble(peace);
                } catch (NumberFormatException nfe) {
                    stringIsHomogenic = false;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                double d = Double.parseDouble(string);
            } catch (NumberFormatException nfe) {
                stringIsHomogenic = false;
            }
        }
        return stringIsHomogenic;
    }


    public ArrayList<String> getStringBetweenBrackets(String data) {
        ArrayList<String> stringLists = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[([0-9.,\\s\\-a-z]+)\\]");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            stringLists.add(matcher.group(1));
        }
        return stringLists;

    }

    public Map<String, Boolean> getMapOfString(ArrayList<String> stringList) {
        Map<String, Boolean> mappedString = new TreeMap<>();
        for (String string : stringList) {
            if (checkStringHomogenity(string)) {
                mappedString.put(string, true);

            } else {
                mappedString.put(string, false);
            }
        }
        return mappedString;
    }


    public Map<Integer, Integer> getListIndexes(
            ArrayList<Integer> openIndexes,
            ArrayList<Integer> closeIndexes) {
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


}
