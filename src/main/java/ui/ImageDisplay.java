package ui;

import fr.sws.ImageUtils;
import fr.sws.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImageDisplay extends JPanel {

    public ImageDisplay(int width,int height){
        this.setBounds(0,height/2,800,500);
        this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int image_width = 100;
        int image_height = 100;
        super.paintComponent(g);
        int i =0;
        int j =0;
        File dir = new File(Main.OUTPUT_PATH);
        File[] files = dir.listFiles()==null?new File[]{}:dir.listFiles();
        for(File file: files){
            if(!file.isDirectory()){
                try {
                    BufferedImage image = ImageIO.read(file);
                    g.drawImage(ImageUtils.resizeImage(image,image_width,image_height), i*100, j*100, this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i++;
                if(i>=8){
                    i=0;
                    j++;
                }
            }
        }
         // see javadoc for more info on the parameters
    }
}
