/* take a data and extenction
 * determine extractor
 * take list of string from extractor
 * split string into peaces and produce array
 * create dictionary with string as key and array as value

 */

import java.util.*;

public class ArrayCooker {

    public ArrayCooker(String dataFromFile, String extenction) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        switch (extenction) {
            case "json":
                JsonExtractor jsonExtractor = new JsonExtractor();
                stringArrayList = jsonExtractor.getListOfString(dataFromFile);
                break;
            case "txt":
                TxtExtractor txtExtractor = new TxtExtractor();
                break;
            case "csv":
                CsvExtractor csvExtractor = new CsvExtractor();
                break;
            default:
                TxtExtractor generalExtractor = new TxtExtractor();
                break;
        }
        if (!stringArrayList.isEmpty()) {
            MainWindow.getMainWindow().getCenterPanel().getOpenTab().setEntryArray(this.getEntryArray(this.getMapOfArray(stringArrayList)));
        } else {
            System.out.println("List of strings is empty");
        }
    }

    public ArrayCooker(ArrayList<String> stringsList) {

    }

    public Map<String, Double[]> getMapOfArray(ArrayList<String> inputListOfString) {
        Map<String, Double[]> outputMapOfArray = new TreeMap<>();
        Comparator<String> lengthComparator = (a, b) -> Integer.compare(a.length(), b.length());
        inputListOfString.sort(lengthComparator.reversed());
        ArrayList<Integer> listSize = new ArrayList<>();
        for (String string : inputListOfString) {
            String[] splittedString = string.split(",");
            if (splittedString.length > 1) {
                Boolean stringIsHomogenic = true;
                Double[] doubleArray = new Double[splittedString.length];
                for (int i = 0; i < splittedString.length; i++) {
                    try {
                        doubleArray[i] = Double.parseDouble(splittedString[i]);
                    } catch (NumberFormatException nfe) {
                        stringIsHomogenic = false;
                    }
                }
                if (stringIsHomogenic == false) {
                    outputMapOfArray.put(getPaddedString(string), null);
                } else {
                    outputMapOfArray.put(getPaddedString(string), doubleArray);
                }
                listSize.add(splittedString.length);
            } else {
                double value = Double.parseDouble(string);
                for (int length : listSize) {
                    Double[] divideArray = new Double[length];
                    Double[] multiplyArray = new Double[length];
                    for (int i = 0; i < length; i++) {
                        if (value != 0) {
                            divideArray[i] = 1 / value * i;
                        }
                        multiplyArray[i] = value * i;
                    }
                    String info_div = value + " // incremented by 1/" + value + " " + length + " times               ";
                    String info_mult = value + " // incremented by " + value + " " + +length + "times                 ";
                    outputMapOfArray.put(getPaddedString(info_div), divideArray);
                    outputMapOfArray.put(getPaddedString(info_mult), multiplyArray);
                }
            }

        }
        return outputMapOfArray;
    }

    public Map.Entry<String, Double[]>[] getEntryArray(Map<String, Double[]> stringList) {
        Set<Map.Entry<String, Double[]>> entrySet = stringList.entrySet();
        Map.Entry<String, Double[]>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        MainWindow.getMainWindow().getCenterPanel().getOpenTab().setEntryArray(entryArray);
        return entryArray;
    }


    public String getPaddedString(String string) {
        String newString = string;
        if (string.length() < 40) {
            newString = String.format("%-40s", string) + "*";
        }
        return newString;
    }
}


