package ui;

import fr.sws.Main;
import fr.sws.json.DataEntry;
import fr.sws.json.DataHandler;
import fr.sws.json.JSONReader;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.awt.desktop.OpenFilesEvent;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class JsonAdder extends JPanel {
    final String name = "Add new image";
    final JLabel label = new JLabel(name);
    final JLabel label2 = new JLabel("Link:");
    final JLabel label3 = new JLabel("Version:");
    public JLabel filename = new JLabel("Selected file: ");
    public JTextField area = new JTextField();
    public JTextField area2 = new JTextField();
    final FileChooserButton open_filechooser = new FileChooserButton();
    final JButton createJson = new JButton();
    public final RegenerateButton regenerateButton = new RegenerateButton();
    final OpenOutputButton out_button = new OpenOutputButton();
    public JsonAdder(int margin,int width,int height){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        label.setBackground(Color.LIGHT_GRAY);
        this.add(label);
        this.add(filename);
        this.add(open_filechooser);
        this.add(label2);
        this.add(area);
        this.add(label3);
        this.add(area2);
        this.setBackground(Color.LIGHT_GRAY);
        createJson.addActionListener(
                e -> {
                    if(open_filechooser.getSelectedFile()==null){
                        Main.window.area.send("Selected file is empty or deleted.");
                        return;
                    }
                    if(area.getText().isEmpty()){
                        Main.window.area.send("Link is empty.");
                        return;
                    }
                    if(!area2.getText().matches("^\\d+$")||area2.getText().isEmpty()){
                        Main.window.area.send("Version is unspecified, or version input is not a number.");
                        return;
                    }
                    try {
                        FileUtils.copyFile(open_filechooser.getSelectedFile(),new File(Main.POKEMON_LIBRARY_PATH,open_filechooser.getSelectedFile().getName()));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    DataEntry entry = new DataEntry(open_filechooser.getSelectedFile().getName(),area.getText());
                    JSONReader.write(entry, Main.POKEMON_LIBRARY_PATH+"/"+entry.getLink().hashCode()+".json");
                }
        );
        createJson.setText("Create Json");
        add(createJson);
        add(regenerateButton);
        add(out_button);
        open_filechooser.setBounds(0,0,margin,margin);
        for(Component c: getComponents()){
            c.setSize(margin,margin);
        }
        this.setBounds(margin,margin,width,height);

    }

}
