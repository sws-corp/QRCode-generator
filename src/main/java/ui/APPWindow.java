package ui;

import javax.swing.*;
import java.awt.*;

public class APPWindow extends JFrame {
    Dimension dimension = new Dimension(1024,720);
    int textWidth = 200;
    int margin =10;
    String name = "SWS-QRCODE";
    public SwSErrorHandlerText area= new SwSErrorHandlerText(100,200);
    public JsonAdder json_adder= new JsonAdder(margin,dimension.width/4,3*dimension.height/8);
    public ImageDisplay display = new ImageDisplay(dimension.width,dimension.height);
    public APPWindow(){
        final JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setName("Console");
        scrollPane.setBounds(dimension.width - textWidth - margin, margin, textWidth, dimension.height/2- 2* margin);
        this.add(scrollPane, "Center");
        this.add(json_adder);
        this.add(display);
    }
    public void start(){
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(dimension);
        this.setVisible(true);
        this.revalidate();
    }
}
