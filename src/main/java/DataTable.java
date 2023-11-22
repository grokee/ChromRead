/*The module was firstly written for processing json files. However it had to be modified because of heterogenity of array list*/

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTable {

    public double[] time;
    public double[] values;
    ArrayList<String> listOfArrays;

    public DataTable(String path) {

        JSONObject jsonObject = new FileLoader(path).getDataFromJson();
        Set<String> arraysKeys = jsonObject.keySet();
        listOfArrays = new ArrayList<String>();
        Iterator<String> it = arraysKeys.iterator();
        while (it.hasNext()) {
            String field = it.next();
            if (jsonObject.get(field).toString().length() > 0) {
                Pattern pattern = Pattern.compile("(.*)([0-9.,]+)(.*)");
                Matcher matcher = pattern.matcher(jsonObject.get(field).toString());
                if (matcher.find()) {
                    System.out.println(field + "/" + matcher.group(1));
                }

//                        JSONArray subArray = (JSONArray) jsonObject.get(it.next());
//
//                        JSONArray aaa = (JSONArray) subArray.get(0);
//                        System.out.println(aaa);
            }
//               System.out.println(jsonObject.get(field));
//                JSONArray subArray = (JSONArray) it.next().get(0);
//                System.out.println(subArray.size());
//                JSONArray subArray = it.next();
//                System.out.println(subArray);
//                if (subArray.size()>1){
//                    for (int i=0; i < subArray.size();i++){
//                        listOfArrays.add((String)subArray.get(i));
//                        System.out.println();
//                    }
//                }
//                else if (subArray.size()==1){
//
//                }
//                listOfArrays.add(arr + "/" + jsonObject.get(arr));
//                }
        }

        System.out.println(listOfArrays);


        JSONArray arr = (JSONArray) jsonObject.get("data");  // get array from JSONObject titled "data"
        JSONArray y = (JSONArray) arr.get(0);             // get first subarray of "data" array
        //        System.out.println(y.toString());
        //        for (int i=0; i<y.l)
        //        String[] stringArray = y.toString().split(",");
        //        System.out.println(stringArray[3]);
        //        ArrayList<String> arr = (ArrayList<String>) jsonObject("data");
        //
        //        ArrayList<Double> newValueArray = new ArrayList();
        //        ArrayList<Double> newTimeArray = new ArrayList<Double>();
        //        ArrayList<Integer> removedIndexes = new ArrayList<Integer>();
        Long dataRate = (Long) jsonObject.get("datarate");
        //
        //
        //
        //
        //        for (int i=0; i < stringArray.length-1; i++){
        //            if (stringArray[i+1].matches("[0-9.]+") == true){
        //                newValueArray.add(Double.parseDouble(stringArray[i+1]));
        //                newTimeArray.add((double)i*dataRate);
        //            } else {
        //              removedIndexes.add(i+1);
        //            }
        //        }
        //
        //        int size = newValueArray.size();
        int size = y.size();
        this.values = new double[size];
        this.time = new double[size];

        //        for (int i=0; i< size; i++){
        ////            System.out.println(newValueArray.get(i));
        //            this.values[i] =  newValueArray.get(i);
        //            this.time[i] =  newTimeArray.get(i);
        //
        //
        //        }


        for (int i = 0; i < size; i++) {
            if (y.get(i).toString().matches("[a-zA-z]+") == false) {
                this.values[i] = (double) y.get(i);
                this.time[i] = (double) i / dataRate;
            }
        }
        //
        //        System.out.println(newValueArray.get(3));
        ////        System.out.println(removedIndexes);


    }
}
