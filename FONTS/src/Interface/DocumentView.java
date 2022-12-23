package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * @file DocumentView.java
 * Class <em>DocumentView</em>
 */

/**
 * View of document
 *
 * @author Víctor Mena Doz
 */

public class DocumentView implements ActionListener {
    /**
     * Instance of Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Represents text component
     */
    private JTextArea textArea;

    /**
     * Represents window of text editor
     */
    private JFrame textEditor;

    /**
     * title of the document shown
     */
    private String title;

    /**
     * author's name of the document shown
     */
    private String author;

    /**
     * language of the document shown
     */
    private String language;

    /**
     * if true: it's a new document
     */
    private boolean newDoc;

    /**
     * Default creation of text editor
     * @param aut, author's name of the document
     * @param tit, title of the document
     * @param lang, language of the document
     * @param newD, boolean that if true: it's a new document
     */
    public DocumentView(String aut, String tit, String lang, boolean newD, boolean modi) {
        author = aut;
        title = tit;
        if(!lang.equals("null")) language = lang;
        newDoc = newD;

        //create frame
        textEditor = new JFrame("Document Title");
        textEditor.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        try { //CHANGE VIEW
            // Set metal look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            // Set theme to ocean
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        textArea = new JTextArea();

        //Create menuBar
        JMenuBar menuBar = new JMenuBar();

        //Create menu for Bar
        JMenuItem saveDoc = new JMenuItem("Save");
        saveDoc.setMinimumSize(new Dimension(35, saveDoc.getPreferredSize().height));
        saveDoc.setMaximumSize(new Dimension(35, saveDoc.getPreferredSize().height));

        saveDoc.addActionListener(this);

        //Create another menu for Bar
        JMenu edit = new JMenu("Edit");
        //Create items for menu

        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste");

        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);

        JMenuItem close = new JMenuItem("Close");
        close.setMinimumSize(new Dimension(35, close.getPreferredSize().height));
        close.setMaximumSize(new Dimension(35, close.getPreferredSize().height));

        close.addActionListener(this);

        menuBar.add(saveDoc);
        menuBar.add(edit);
        menuBar.add(Box.createGlue());
        menuBar.add(close);

        JScrollPane scrollableTextArea = new JScrollPane(textArea);

        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //set configs
        textEditor.setJMenuBar(menuBar);
        textEditor.getContentPane().add(scrollableTextArea);
        textEditor.setSize(750, 500);

        if(!newDoc) {
            ArrayList<String> aux = ctrlPres.getDocument(author, title);
            language = aux.get(3);
            textArea.setText(aux.get(2));
            textEditor.setTitle(aux.get(0));
        }
        else {
            textEditor.setTitle(title);
        }
        if(!modi) {
            textArea.setEditable(false);
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - textEditor.getWidth()) / 2;
        int y = (screenSize.height - textEditor.getHeight()) / 2;
        textEditor.setLocation(x, y);
        textEditor.setVisible(true);
    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("Save")) {
            if(!newDoc) {
                try {
                    /*if(textArea.getText().length() == 0) JOptionPane.showMessageDialog(new JDialog(), "Document cannot be empty");
                    else {

                    }*/
                    ctrlPres.modifyContent(author, title, textArea.getText());
                    JOptionPane.showMessageDialog(new JDialog(), "Document saved");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(new JDialog(), "Can't modify the content");
                }
            }
            else {
                try {
                    /*if(textArea.getText().length() == 0) JOptionPane.showMessageDialog(new JDialog(), "Document cannot be empty");*/
                    if(language.equals("Spanish")) {
                        ctrlPres.newDocument(author, title, textArea.getText(), "ESP");
                        JOptionPane.showMessageDialog(new JDialog(), "Document saved");
                    }
                    else if (language.equals("Catalan")) {
                        ctrlPres.newDocument(author, title, textArea.getText(), "CAT");
                        JOptionPane.showMessageDialog(new JDialog(), "Document saved");
                    }
                    else {
                        ctrlPres.newDocument(author, title, textArea.getText(), "ENG");
                        JOptionPane.showMessageDialog(new JDialog(), "Document saved");
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(new JDialog(), "Can't save the document");
                }
            }
            textEditor.setVisible(false);
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
    /*public static void main(String args[]) {
        String title = "Julia la crack";
        String author = "Victor Mena";
        String lang = "Spanish";

        DocumentView te = new DocumentView(author, title, lang,true, true);
    }*/
}
