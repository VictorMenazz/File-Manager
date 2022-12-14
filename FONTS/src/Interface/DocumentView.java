package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DocumentView implements ActionListener {
    /**
     * Represents text component
     */
    private JTextArea textArea;

    /**
     * Represents window of text editor
     */
    private JFrame textEditor;

    /**
     * @brief Default creation of text editor
     */
    public DocumentView() {
        //create frame
        textEditor = new JFrame("Document Title");
        textEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try { //CHANGE VIEW
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        textArea = new JTextArea();

        //Create menuBar
        JMenuBar menuBar = new JMenuBar();

        //Create menu for Bar
        JMenu file = new JMenu("File");
        //Create items for menu
        JMenuItem newDoc = new JMenuItem("New");
        JMenuItem openDoc = new JMenuItem("Open");
        JMenuItem saveDoc = new JMenuItem("Save");
        JMenuItem importDoc = new JMenuItem("Import");
        JMenuItem exportDoc = new JMenuItem("Export");

        //Adding action listener
        newDoc.addActionListener(this);
        openDoc.addActionListener(this);
        saveDoc.addActionListener(this);
        importDoc.addActionListener(this);
        exportDoc.addActionListener(this);

        file.add(newDoc);
        file.add(openDoc);
        file.add(saveDoc);
        file.add(importDoc);
        file.add(exportDoc);

        //Create another menu for Bar
        JMenu edit = new JMenu("Edit");
        //Create items for menu
        JMenuItem undo = new JMenuItem("Undo");
        JMenuItem redo = new JMenuItem("Redo");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste");

        undo.addActionListener(this);
        redo.addActionListener(this);
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);

        edit.add(undo);
        edit.add(redo);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);

        JMenuItem close = new JMenuItem("Close");

        close.addActionListener(this);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(close);

        JScrollPane scrollableTextArea = new JScrollPane(textArea);

        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //set configs
        textEditor.setJMenuBar(menuBar);
        textEditor.getContentPane().add(scrollableTextArea);
        textEditor.setSize(750, 500);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - textEditor.getWidth()) / 2;
        int y = (screenSize.height - textEditor.getHeight()) / 2;
        textEditor.setLocation(x, y);
        textEditor.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("New")) {
            textArea.setText("");
        }
        else if(s.equals("Open")) {

        }
        else if(s.equals("Save")) {

        }

        else if(s.equals("Import")) {
            //Create an object of JFileChooser class
            JFileChooser fc = new JFileChooser("File:");

            // Invoke the showsOpenDialog function to show the save dialog
            int r = fc.showOpenDialog(null);

            // If the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set the label to the path of the selected directory
                File fi = new File(fc.getSelectedFile().getAbsolutePath());
                String name =  fi.getName();
                int dotIndex = name.lastIndexOf('.');
                if(dotIndex != -1) {
                    String ext = name.substring(dotIndex + 1);
                    if((ext.equals("txt")) | ext.equals("xml")) {
                        try {
                            // String
                            String s1 = "", sl = "";

                            // File reader
                            FileReader fr = new FileReader(fi);

                            // Buffered reader
                            BufferedReader br = new BufferedReader(fr);

                            // Initialize sl
                            sl = br.readLine();

                            // Take the input from the file
                            while ((s1 = br.readLine()) != null) {
                                sl = sl + "\n" + s1;
                            }

                            // Set the text and title
                            textArea.setText(sl);
                            textEditor.setTitle(fi.getName());
                        }
                        catch (Exception evt) {
                            JOptionPane.showMessageDialog(textEditor, evt.getMessage());
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(textEditor, "Incorrect type of document. It must be .txt ot .xml");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(textEditor, "File doesn't have extension");
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(textEditor, "Import operation cancelled");
        }
        else if(s.equals("Export")) {
            // Create an object of JFileChooser class
            JFileChooser j = new JFileChooser("f:");

            // Invoke the showsSaveDialog function to show the save dialog
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Create a file writer
                    FileWriter wr = new FileWriter(fi, false);

                    // Create buffered writer to write
                    BufferedWriter w = new BufferedWriter(wr);

                    // Write
                    w.write(textArea.getText());

                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(textEditor, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(textEditor, "Export operation canceled");
        }

        else if(s.equals("Undo")) {
        }

        else if(s.equals("Redo")) {

        }

        else if(s.equals("Copy")) {
            textArea.copy();
        }

        else if(s.equals("Cut")) {
            textArea.cut();
        }

        else if(s.equals("Paste")) {
            textArea.paste();
        }

        else if(s.equals("Close")) {
            textEditor.setVisible(false);
        }
    }

    //Provisional Main
    public static void main(String args[]) {
        DocumentView te = new DocumentView();
    }
}
