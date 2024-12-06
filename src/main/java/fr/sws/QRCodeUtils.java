package fr.sws;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCodeUtils {
    public static void createQRCode(File file, String link, BufferedImage image,int version){
        FileUtils.createFile(file);
        FileOutputStream stream = FileUtils.getStream(file);
        try{
            QRCode.from(link)
                    .withErrorCorrection(ErrorCorrectionLevel.H)
                    .withColor(Color.WHITE.getRGB(),Color.BLACK.getRGB())
                    .to(ImageType.JPG)
                    .withHint(EncodeHintType.QR_VERSION,version)
                    .writeTo(stream);
        }catch (Exception e){
            Main.window.area.send("QR CODE version is too small to handle that amout of information.");
            return;
        }

        for(int i =0; i < image.getWidth();i++){
            for(int j=0;j<image.getHeight();j++){
                int pixel = image.getRGB(i,j);
                int alpha = (pixel >> 24)&0xff;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                Color c = (alpha==0)?Color.WHITE:Color.BLACK;
                image.setRGB(i,j,c.getRGB());
            }
        }
        try {
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage QRCODE = ImageUtils.loadImage(file.getPath());
        if(QRCODE.getWidth()>image.getWidth()||QRCODE.getHeight()>image.getHeight()){
            double scal_d = Math.max((double)QRCODE.getWidth()/(double)image.getWidth(),(double)QRCODE.getHeight()/(double)image.getHeight()) +1;
            int resize = (int) Math.round(scal_d);
            image = ImageUtils.resizeImage(image,image.getWidth() * resize,image.getHeight() * resize);
        }
        try {
            int scaling_factor = ImageUtils.maximal_scaling(image,QRCODE);
            QRCODE = ImageUtils.resizeImage(QRCODE,QRCODE.getWidth()*scaling_factor,QRCODE.getHeight()*scaling_factor);

        }catch (Exception e){
            Main.window.area.send("An error has occured, maybe the image provided cannot be turned into a QRCODE. The image must be in one part, and the background fully transparent.");
            return;
        }

        final Point point = ImageUtils.getCenterOfMass(image);
        int width_QR = QRCODE.getWidth();
        int height_QR = QRCODE.getHeight();
        for(int i =0; i < width_QR; i++){
            for(int j=0;j<height_QR;j++){
                image.setRGB(i-width_QR/2+point.x,j-height_QR/2+point.y,QRCODE.getRGB(i,j));
            }
        }
        try {
            ImageIO.write(ImageUtils.resizeImage(image,image.getWidth() ,image.getHeight()),"png",file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
