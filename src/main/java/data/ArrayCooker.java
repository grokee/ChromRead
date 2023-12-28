/* take a data and extension (new FileLoader->getFile)
 * determine extractor ()
 * take list of string from extractor
 * split string into peaces and produce array
 * create dictionary with string as key and array as value

 */

import windows.MainWindow;

import java.util.*;

public class ArrayCooker {

    private String fileExtension;

    private String dataFromFile;

    public ArrayCooker() {

    }

    public void loadFile(String path){
        FileLoader fl = new FileLoader();
        dataFromFile = fl.getDataFromFile(path);
        fileExtension = fl.getExtenction(path);
    }

    // method invokes appropriate method to find Arrays (as String) in Data and creates list of these Arrays
    public ArrayList<String> getStringArrayList(String data, String extension){
        ArrayList<String> stringArrayList;
        ExtractorFactory dataExctractor = new ExtractorFactory();
        stringArrayList = dataExctractor.getExctractor(extension).getListOfString(data);
        return stringArrayList;
    }

    // method sends EntryArray to appropriate windows.Tab of the windows.MainWindow
    public void sendArrays(){
        ArrayList<String> arrayToSend = this.getStringArrayList(dataFromFile,fileExtension);
        if (!arrayToSend.isEmpty()) {
            MainWindow.getMainWindow().getCenterPanel().getOpenTab().setEntryArray(this.getEntryArray(this.getMapOfArray(arrayToSend)));
        } else {
            System.out.println("List of strings is empty");
            MainWindow.getMainWindow().fillStatusBar("List of arrays cannot be created from chosen file");
        }

    }

    // methods checks arrays for homogenity and creates dictionary of Arrays
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


    public Map<String, Double[]> getFilteredDictionary(Map<String, Double[]> arrayDictionary){
        Map<String, Double[]> filteredDictionary = new TreeMap<>();
        for (Map.Entry<String, Double[]> entry:arrayDictionary.entrySet()){
            if (entry.getValue()!=null){
                filteredDictionary.put(entry.getKey(), entry.getValue());
            }
        }
        return filteredDictionary;
    }


    // method converts dictionary of Arrays to Set of double arrays
    public Map.Entry<String, Double[]>[] getEntryArray(Map<String, Double[]> stringList) {
        Set<Map.Entry<String, Double[]>> entrySet = this.getFilteredDictionary(stringList).entrySet();
        Map.Entry<String, Double[]>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        return entryArray;
    }



    //
    public String getPaddedString(String string) {
        String newString = string;
        if (string.length() < 40) {
            newString = String.format("%-40s", string) + "*";
        }
        return newString;
    }
}


