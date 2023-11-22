/*The module is not using*/

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Set;

public class DataExtractor {

    public double[] time;
    public double[] values;


//    public String[] getSetFromFile{
//
//
////           String[] setList = new String[];
//
//
//
//
//    }

    public DataExtractor(String path) {

            JSONObject jsonObject = new FileLoader(path).getDataFromJson();
            Set<String> arrays = jsonObject.keySet();


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


            for (int i=0; i < size; i++) {
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
