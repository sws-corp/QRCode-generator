package fr.sws.json;
import exceptions.QRCodeException;
import fr.sws.FileUtils;
import fr.sws.ImageUtils;
import fr.sws.Main;
import fr.sws.QRCodeUtils;

import javax.xml.crypto.Data;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
public class DataHandler {
    private static ArrayList<DataEntry> entries = new ArrayList<>();
    private static Random random = new Random(System.currentTimeMillis());
    public static void reset(){
        entries.clear();
        File directory = new File(Main.POKEMON_LIBRARY_PATH);
        File[] files = (directory.listFiles()==null)?new File[]{}:directory.listFiles();
        for(final File file: files){
            if(file.getName().contains(".json")){
                DataEntry entry = JSONReader.getEntry(file.getPath());
                entries.add(entry);
            }
        }
    }
    public static DataEntry pick(){
        if(entries.size()==0){
            throw new QRCodeException.NoJsonFoundException();
        }
        return entries.get(random.nextInt(entries.size()));
    }
    public static void generateQRCode(DataEntry entry,int version){
        BufferedImage image = ImageUtils.loadImage(Main.POKEMON_LIBRARY_PATH+"/"+entry.getPath());
        QRCodeUtils.createQRCode(new File(Main.OUTPUT_PATH+"/"+Main.DEFAULT_FILE_NAME),entry.getLink(),image,version);
    }
    public static void generateAll(int version){
        int i =0;
        for(DataEntry entry:entries.stream().toList()){
            String path = Main.POKEMON_LIBRARY_PATH+"/"+entry.getPath();
            Main.window.area.send("Loading: "+path);
            BufferedImage image = ImageUtils.loadImage(path);
            Main.window.area.send("Creating: "+path);
            QRCodeUtils.createQRCode(new File(Main.OUTPUT_PATH+"/"+Main.DEFAULT_FILE_NAME+"_"+i),entry.getLink(),image,version);
            i++;
        }
    }
}
