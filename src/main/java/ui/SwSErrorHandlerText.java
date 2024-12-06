package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwSErrorHandlerText extends JTextArea {
    public void send(String line){
        if(this.getText().lines().count()>40){
            this.setText("");
        }
        this.append(line+"\n");
        this.update(getGraphics());
    }
    public SwSErrorHandlerText(int a,int b){
        super(a,b);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.setEditable(false);
    }
}
