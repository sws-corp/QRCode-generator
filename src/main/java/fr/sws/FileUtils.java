package fr.sws;

import java.io.*;

public class FileUtils {
    public static File getFile(String parent,String child) {
        return new File(parent, child);
    }
    public static void createFile(File file){
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //TODO GO TO ERROR MANAGER
                return;
            }
        }
    }
    public static FileOutputStream getStream(File file){
        createFile(file);
        FileOutputStream stream = null;
        try{
            stream = new FileOutputStream(file);
        }catch (FileNotFoundException e){
            //TODO go to error handling
        }
        return stream;
    }
}
