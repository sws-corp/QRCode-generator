package fr.sws;
import fr.sws.json.DataHandler;
import ui.APPWindow;

import javax.swing.*;
import java.io.*;

import static java.lang.Thread.sleep;

public class Main {
    public static String DIRECTORY_PATH = "QRCODE_DIRECTORY";
    public static String DEFAULT_FILE_NAME = "QR_CODE";
    public static String POKEMON_LIBRARY_PATH = DIRECTORY_PATH+"/pokelib";
    public static String OUTPUT_PATH  = DIRECTORY_PATH +"/output";
    public static APPWindow window;
    public static void main(String[] args){

        if(args.length!=1) {
            System.out.println("First and only argument must be the working directory of the APP.");
            return;
        }
        DIRECTORY_PATH = args[0];
        File file = new File(DIRECTORY_PATH);
        file.mkdir();
        file = new File(POKEMON_LIBRARY_PATH);
        file.mkdir();
        file = new File(OUTPUT_PATH);
        file.mkdir();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException |
                     ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        window = new APPWindow();
        window.start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DataHandler.reset();
    }
}