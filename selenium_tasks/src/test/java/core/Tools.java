package core;

import java.io.File;

public class Tools {

    public static String getAbsoluteFilePath(String filePathName){
        String result = "undefined";
        try {
            result = new File(filePathName).toURI().toURL().toString();
        } catch (Throwable t) {
            throw new CoreException("Can't generate file URL!");
        }
        result = result.replace("file:/","");
        if(System.getProperty("os.name").toLowerCase().contains("win")){
            result = result.replace("/","\\");
        }
        if(System.getProperty("os.name").toLowerCase().contains("lin")){
            result = "/" + result;
        }
        return result;
    }

}
