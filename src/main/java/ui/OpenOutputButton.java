package ui;

import fr.sws.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenOutputButton extends JButton {
    public OpenOutputButton(){
        setText("Show Images");
        addActionListener(
                e -> {
                    try {
                        Desktop.getDesktop().open(new File(Main.OUTPUT_PATH));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );
    }
}
