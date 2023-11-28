/*
 *
 * load file and transform it into string
 * determine the way of array searching
 *
 *  */


import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileLoader {

    private FileReader loadedFile;


//    public FileReader getFile(){
//        return loadedFile;
//    }

    private String expName;



//    public FileLoader(String path) {
//        try {
//            FileReader file = new FileReader(path);
//            this.loadedFile = file;
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Please check the file name");
//        }
//    }

    public void convertFileToDictionary(String path){
        String stringFromFile = "";
        String fileExtenction = "";
        byte[] byteFromFile = "".getBytes();
        try {
            byteFromFile = Files.readAllBytes(Paths.get(path));
            fileExtenction = getExtenction(path);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please check the file name");
        } finally {
            if (byteFromFile != null) {
                try {
                    stringFromFile = new String(byteFromFile);
                } catch (Exception e) {
                    System.out.println("File cannot be read as text");
                }
            }
        }
//        fileExtenction = getExtenction(path);
        fileExtenction = "json";
        ArrayCooker arrayCooker = new ArrayCooker(stringFromFile,fileExtenction);

    }

    public Map<String,Boolean> getStringFromFile(String path) {
        Map<String,Boolean> checkedString = new TreeMap<>();
        String stringFromFile = "";
        String fileExtenction = "";
        byte[] byteFromFile = "".getBytes();
        try {
            byteFromFile = Files.readAllBytes(Paths.get(path));
            fileExtenction = getExtenction(path);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please check the file name");
        } finally {
            if (byteFromFile != null) {
                try {
                    stringFromFile = new String(byteFromFile);
                } catch (Exception e) {
                    System.out.println("File cannot be read as text");
                }
            }
        }
//        if (fileExtenction == "json"){
            JsonExtractor jsonExtractor = new JsonExtractor();
            checkedString = jsonExtractor.getMapOfString(jsonExtractor.getExtendedList(jsonExtractor.getListOfString(stringFromFile)));
//        }
//         else if (fileExtenction == "txt"){
//            System.out.println("I cannot read txt-file yet");
//        } else {
//            System.out.println("I can read only json-files");
//        }

        return checkedString;
    }


    public String getExtenction(String path){
        String extenction = "";
        Pattern pattern = Pattern.compile(".*\\.([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()){
            extenction = matcher.group(1);
        }
        return extenction;
    }




//    public void setExpName(String s) {
//        try {
//            FileReader readFile = new FileReader(s);
//            expName = s;
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("File " + s + " has not been found");
//        }
//    }

//    public int getNumberOfObjects(char symbol){
//        int maxNumber = 0;
//
//
//        return maxNumber;
//    }

    public JSONObject getDataFromJson() {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
//        try {
//            FileReader file = new FileReader(path);
        try {
            jsonObject = (JSONObject) parser.parse(loadedFile);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Program cannot read JSON file" + loadedFile);
        }
//        } catch (Exception e) {
//            System.out.println(e);
//            System.out.println("Please check the file name");
//        }
        return jsonObject;
    }
}
