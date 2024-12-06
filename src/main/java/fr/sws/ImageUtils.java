package fr.sws;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.Random;

public class ImageUtils {
    public static BufferedImage loadImage(String path){
        File file = new File(path);
        try{
            return ImageIO.read(file);
        }catch (IOException e) {
            //TODO Handle ERROR
            return null;
        }
    }
    public static BufferedImage getSubImage(BufferedImage image, int x, int y, int size_x, int size_y){
        return image.getSubimage(x,y,size_x,size_y);
    }
    public static void writeImage(BufferedImage image,String format, String name){
        try {
            ImageIO.write(image,format,new File(Main.DIRECTORY_PATH,name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Color getColorMaj(BufferedImage image){
        //we calculate the weight, being the sum of all alpha component of the image
        int blue= 0;
        int red = 0;
        int green=0;
        for(int i =0; i < image.getWidth();i++){
            for(int j=0;j<image.getHeight();j++){
                int rgb = image.getRGB(i,j);
                double alpha =(double) (rgb>>24);
                int r_comp = (rgb >> 16) & 0xff;
                int g_comp = (rgb >> 8) & 0xff;
                int b_comp = (rgb) & 0xff;
                blue += b_comp;
                red += r_comp ;
                green += g_comp;
            }
        }
        if(green > blue && green > red){
            return Color.RED;
        }
        if(blue > green && blue > red){
            return Color.blue;
        }
        return Color.green;
    }
    private static boolean in_bound(BufferedImage image, BufferedImage QRCODE){
        final Point point = ImageUtils.getCenterOfMass(image);
        for(int i =0; i< QRCODE.getWidth();i++){
            for(int j=0;j<QRCODE.getHeight();j++){
                if(image.getRGB(i-QRCODE.getWidth()/2+point.x,j-QRCODE.getHeight()/2+point.y)==Color.WHITE.getRGB()){
                    return false;
                }
            }
        }
        return true;
    }
    //COMPUTES THE MAXIMAL SCALING THAT THE QR CODE CAN BE
    //PRE CONDITION: IMAGE est une image en blanc/noir
    public static int maximal_scaling(BufferedImage image, BufferedImage QRCODE){
        int lambda =1;
        while(in_bound(image,resizeImage(QRCODE,QRCODE.getWidth()*lambda,QRCODE.getHeight()*lambda))){
            lambda++;
        }
        return (lambda==1)?1:lambda-1;
    }

    //Calculate the center of mass of a black/white image. Used to know where to place the QRCODE
    public static Point getCenterOfMass(BufferedImage image){
        Point p = new Point(0,0);
        int n = 0;
        for(int i =0; i< image.getWidth();i++){
            for(int j=0; j< image.getHeight();j++){
                if(image.getRGB(i,j)==Color.BLACK.getRGB()){
                    p.x =p.x + i;
                    p.y =p.y+j;
                    n++;
                }
            }
        }
        return new Point((p.x/n),(p.y/n));
    }
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;
    }
}
