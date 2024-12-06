package fr.sws.json;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class JSONReader {
    public static DataEntry getEntry(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, DataEntry.class);
        } catch (IOException e) {
            return null;
        }
    }
    public static void write(DataEntry entry,String path){
        final Gson gson = new Gson();
        gson.toJson(entry);
        try (FileWriter writer = new FileWriter(path)){
            writer.write(gson.toJson(entry));
        }catch (IOException e){
            return;
        }
    }
}