package ui;

import fr.sws.Main;
import fr.sws.json.DataHandler;
import javax.swing.*;
public class RegenerateButton extends JButton {
    public RegenerateButton(){
        this.setText("Generate images");
        this.addActionListener(
                e ->{
                    DataHandler.reset();
                    DataHandler.generateAll(Integer.parseInt(Main.window.json_adder.area2.getText()));
                    Main.window.display.revalidate();
                    Main.window.display.paintComponent(Main.window.display.getGraphics());
                }
        );
    }
}
