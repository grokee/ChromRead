/*
 *
 * load file and transform it into string
 * determine the way of array searching
 *
 *  */


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLoader {

    private FileReader loadedFile;

    private String expName;


    public String getDataFromFile(String path) {
        String stringFromFile = "";
        String fileExtenction = "";
        byte[] byteFromFile = "".getBytes();
        try {
            byteFromFile = Files.readAllBytes(Paths.get(path));
            if (byteFromFile == null)  {
                throw new Exception("File cannot be read as text");
//                try {
//                    stringFromFile = new String(byteFromFile);
//                } catch (Exception e) {
//                    System.out.println("File cannot be read as text");
//                }
            }
            stringFromFile = new String(byteFromFile);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please check the file name");
            stringFromFile = "";
        }
        return stringFromFile;

    }

//    public Map<String, Boolean> getStringFromFile(String path) {
//        Map<String, Boolean> checkedString = new TreeMap<>();
//        String stringFromFile = "";
//        String fileExtenction = "";
//        byte[] byteFromFile = "".getBytes();
//        try {
//            byteFromFile = Files.readAllBytes(Paths.get(path));
//            fileExtenction = getExtenction(path);
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Please check the file name");
//        } finally {
//            if (byteFromFile != null) {
//                try {
//                    stringFromFile = new String(byteFromFile);
//                } catch (Exception e) {
//                    System.out.println("File cannot be read as text");
//                }
//            }
//        }
////        if (fileExtenction == "json"){
//        JsonExtractor jsonExtractor = new JsonExtractor();
//        checkedString = jsonExtractor.getMapOfString(jsonExtractor.getExtendedList(jsonExtractor.getListOfString(stringFromFile)));
//        return checkedString;
//    }


    public String getExtenction(String path) {
        String extenction = "";
        Pattern pattern = Pattern.compile(".*\\.([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            extenction = matcher.group(1);
        }
        return extenction;
    }



//    public JSONObject getDataFromJson() {
//        JSONParser parser = new JSONParser();
//        JSONObject jsonObject = new JSONObject();
////        try {
////            FileReader file = new FileReader(path);
//        try {
//            jsonObject = (JSONObject) parser.parse(loadedFile);
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Program cannot read JSON file" + loadedFile);
//        }
////        } catch (Exception e) {
////            System.out.println(e);
////            System.out.println("Please check the file name");
////        }
//        return jsonObject;
//    }
}
