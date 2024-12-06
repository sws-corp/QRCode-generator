package ui;

import fr.sws.Main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

import static java.lang.Thread.sleep;

public class FileChooserButton extends JButton {
    private JFileChooser chooser= new JFileChooser();
    File file;
    public FileChooserButton(){
        this.setText("Open new image");
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().contains(".png")||f.isDirectory();
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        addActionListener(e-> chooser.showOpenDialog(this));
        chooser.addActionListener(
                e -> {
                    file = chooser.getSelectedFile();
                    String name = file==null?"None":file.getName();
                    Main.window.area.send("Selected file: "+name);
                    Main.window.json_adder.filename.setText("Selected file: "+name);
                }
        );
    }
    public File getSelectedFile(){
        return chooser.getSelectedFile();
    }
}
