import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileLoader {

    private FileReader loadedFile;

//    public FileReader getFile(){
//        return loadedFile;
//    }

    private String expName;

    public FileLoader(String path){
        try {
           FileReader file = new FileReader(path);
            this.loadedFile = file;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Please check the file name");
        }
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
