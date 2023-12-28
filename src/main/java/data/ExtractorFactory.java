package data;

public class ExtractorFactory {

    public Extractor getExctractor(String fileExtension){

        switch (fileExtension) {
            case "json":
                return new JsonExtractor();
            case "txt":
                return new JsonExtractor();
            case "csv":
                break;
            default:
                break;
        }
        return null;

    }
}
