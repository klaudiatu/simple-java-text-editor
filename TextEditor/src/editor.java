import javax.swing.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.Action;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;


public class editor extends JFrame{

    private JTextArea textArea = new JTextArea(20, 60);
    private JFileChooser fc = new JFileChooser();

    //Constructor for window

    public editor(){
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //Add filter

        FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
        fc.setFileFilter(txtFilter);

        //menu
        add(scrollPane);
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
        JMenu file = new JMenu("File");
        menu.add(file);

        file.add(Open);
        file.add(Save);
        file.add(Exit);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    Action Open = new AbstractAction("Open File") {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            openFile(fc.getSelectedFile().getAbsolutePath()); //Remember to add open file
        }
    }};

        Action Save = new AbstractAction("Save File") {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile(); //Remember to add it later
            }
        };

        Action Exit = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        public void openFile(String fileName){
            FileReader fr = null;
            try{
                fr = new FileReader(fileName);
                textArea.read(fr,null);
                fr.close();
                setTitle(fileName);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void saveFile() {
            if(fc.showSaveDialog(null)== JFileChooser.APPROVE_OPTION){
                FileWriter fw = null;
                try{
                    fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
                    textArea.write(fw);
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void main(String[] args) {
            new editor();

    }
}
