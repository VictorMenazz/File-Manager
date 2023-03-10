package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * @file FolderView.java
 * Class <em>FolderView</em>
 */

/**
 * View to show documents in the app
 *
 * @author Víctor Mena Doz
 */


public class FolderView extends JPanel implements ActionListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Instance of the main view
     */
    private MainView mainView;

    /**
     * Provides nice icons and names for files
     */
    private FileSystemView fileSystemView;

    /**
     * Table model for File[]
     */
    private ListSelectionListener listSelectionListener;

    /**
     * Directory listing
     */
    private JTable table;

    private int folderID;

    private int fatherID;

    /**
     * List of subfolders
     */
    private HashMap<Integer, String> subF;

    /**
     * Model for the table
     */
    private DefaultTableModel model;

    /**
     * Constructor of the Folder View
     * @param mv MainView
     * @param id folder identification to show
     * @param fatherId parent folder
     */
    public FolderView(MainView mv, int id, int fatherId) {
        mainView = mv;
        folderID = id;
        fatherID = fatherId;

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        ArrayList<String> authors = ctrlPres.getDocumentAuthors(id);
        ArrayList<String> documents = ctrlPres.getDocumentTitles(id);
        //HashMap<Integer, String> subfolders = ctrlPres.getSubFolders(id);
        //subF = subfolders;
        fileSystemView = FileSystemView.getFileSystemView();

        Object[][] data = new Object[(authors.size())][3];
        //Icon folderIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/folder-icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        int j = 0;
        /*for (Integer key : subfolders.keySet()) {
            data[j][0] = folderIcon;
            data[j][1] = subfolders.get(key);
            data[j][2] = "-";
            ++j;
        }*/
        for (int i = 0; i < (authors.size()); ++i) {
            data[i][0] = documentIcon;
            data[i][1] = documents.get(i);
            data[i][2] = authors.get(i);
        }

        String[] columnNames = {"Type", "Name", "Author"};

        model = new DefaultTableModel(data, columnNames) {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };

        table = new JTable(model) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoCreateRowSorter(true);
        table.setShowVerticalLines(false);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);


        table.getSelectionModel().addListSelectionListener(listSelectionListener);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(550, 390));

        //Create menuBar
        JMenuBar menuBar = new JMenuBar();

        //Create menu for Bar
        /*JMenu folder = new JMenu("Folder");

        //Create items for menu
        JMenuItem openFolder = new JMenuItem("Open folder");
        JMenuItem deleteFolder = new JMenuItem("Delete folder");*/


        //Adding action listener
        /*openFolder.addActionListener(this);
        deleteFolder.addActionListener(this);

        folder.add(openFolder);
        folder.addSeparator();
        folder.add(deleteFolder);*/


        //JMenu file = new JMenu("File");
        //Create items for menu
        JMenuItem openDoc = new JMenuItem("Open");
        JMenuItem editDoc = new JMenuItem("Edit");
        JMenuItem deleteDoc = new JMenuItem("Delete");

        openDoc.setPreferredSize(new Dimension(40, openDoc.getPreferredSize().height));
        editDoc.setPreferredSize(new Dimension(40, editDoc.getPreferredSize().height));
        deleteDoc.setPreferredSize(new Dimension(40, deleteDoc.getPreferredSize().height));

        //Adding action listener
        openDoc.addActionListener(this);
        editDoc.addActionListener(this);
        deleteDoc.addActionListener(this);

        /*file.add(openDoc);
        file.addSeparator();
        file.add(editDoc);
        file.addSeparator();
        file.add(deleteDoc);*/


        //menuBar.add(folder);
        //menuBar.add(file);
        menuBar.add(openDoc);
        menuBar.add(editDoc);
        menuBar.add(deleteDoc);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                menuBar,
                tableScroll);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Returns the selected Folder from the table
     * @return
     */
    public int getSelectedFolder(){
        int row = table.getSelectedRow();
        String name = (String) table.getValueAt(row, 1);
        int folderID = 0;
        for(Integer aux : subF.keySet()) {
            if(subF.get(aux) == name) {
                return aux;
            }
        }
        return folderID;
    }

    /**
     * Reloads the content of the table
     */
    public void reload(){
        ArrayList<String> authors = ctrlPres.getDocumentAuthors(folderID);
        ArrayList<String> documents = ctrlPres.getDocumentTitles(folderID);
        //HashMap<Integer, String> subfolders = ctrlPres.getSubFolders(folderID);
        //subF = subfolders;
        String[] col = {"Type", "Name", "Author"};
        Object[][] data = new Object[(authors.size())][3];
        //Icon folderIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/folder-icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        int j = 0;
        /*for (Integer key : subfolders.keySet()) {
            data[j][0] = folderIcon;
            data[j][1] = subfolders.get(key);
            data[j][2] = "-";
            ++j;
        }*/
        for (int i = 0; i < (authors.size()); ++i) {
            data[i][0] = documentIcon;
            data[i][1] = documents.get(i);
            data[i][2] = authors.get(i);
        }
        if(data.length == 0) {
            model.setRowCount(0);
        }
        else model.setDataVector(data, col);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setAutoCreateRowSorter(true);
        table.setShowVerticalLines(false);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.updateUI();
        updateUI();
        setVisible(true);
    }

    /**
     * Actions of documents
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        /*if(s.equals("Open folder")) {
            mainView.setGo("Folder View");
        }

        else if(s.equals("Delete folder")) {
            int row = table.getSelectedRow();
            if (row != -1){
                String author = (String) table.getValueAt(row, 2);
                if (author.equals("-")){
                    //?????????????
                } else JOptionPane.showMessageDialog(new JOptionPane(), "Selection is not a folder");
            }
        }*/
        if(s.equals("Open")) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String author = (String) table.getValueAt(row, 2);
                if (!author.equals("-")) {
                    String title = (String) table.getValueAt(row, 1);
                    ctrlPres.toDocument(author, title, "null", false, false);
                } else JOptionPane.showMessageDialog(new JOptionPane(), "Selection is not a document");
            }
        }
        else if(s.equals("Edit")) {
            int row = table.getSelectedRow();
            if (row != -1){
                String author = (String) table.getValueAt(row, 2);
                if (!author.equals("-")) {
                    String title = (String) table.getValueAt(row, 1);
                    System.out.println(author);
                    System.out.println(title);
                    ctrlPres.toDocument(author, title, "null", false, true);
                } else JOptionPane.showMessageDialog(new JOptionPane(), "Selection is not a document");
            }

        }
        else if(s.equals("Delete")) {
            int row = table.getSelectedRow();
            if (row != -1){
                String author = (String) table.getValueAt(row, 2);
                if (!author.equals("-")){
                    String title = (String) table.getValueAt(row, 1);
                    ctrlPres.deleteDocument(author, title);
                    JOptionPane.showMessageDialog(new JOptionPane(), "Deleted document");
                    reload();
                } else JOptionPane.showMessageDialog(new JOptionPane(), "Selection is not a document");
            }
        }
    }
}
