package fr.sws.json;

import javax.xml.crypto.Data;

public class DataEntry {
    private String path;
    private String link;

    public String getLink() {
        return link;
    }

    public String getPath() {
        return path;
    }
    public DataEntry(String path,String link){
        this.path = path;
        this.link = link;
    }
}
