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

    private DefaultTableModel model;

    public FolderView(MainView mv, int id, int fatherId) {
        mainView = mv;
        folderID = id;
        fatherID = fatherId;

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        ArrayList<String> authors = ctrlPres.getDocumentAuthors(id);
        ArrayList<String> documents = ctrlPres.getDocumentTitles(id);
        HashMap<Integer, String> subfolders = ctrlPres.getSubFolders(id);
        subF = subfolders;
        fileSystemView = FileSystemView.getFileSystemView();

        Object[][] data = new Object[(authors.size() + subfolders.size())][3];
        Icon folderIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/folder-icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        int j = 0;
        for (Integer key : subfolders.keySet()) {
            data[j][0] = folderIcon;
            data[j][1] = subfolders.get(key);
            data[j][2] = "-";
            ++j;
        }
        for (int i = subfolders.size(); i < (authors.size() + subfolders.size()); ++i) {
            data[i][0] = documentIcon;
            data[i][1] = documents.get(i - subfolders.size());
            data[i][2] = authors.get(i - subfolders.size());
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
        JMenu folder = new JMenu("Folder");

        //Create items for menu
        JMenuItem openFolder = new JMenuItem("Open folder");
        JMenuItem deleteFolder = new JMenuItem("Delete folder");


        //Adding action listener
        openFolder.addActionListener(this);
        deleteFolder.addActionListener(this);

        folder.add(openFolder);
        folder.addSeparator();
        folder.add(deleteFolder);


        JMenu file = new JMenu("File");
        //Create items for menu
        JMenuItem openDoc = new JMenuItem("Open file");
        JMenuItem editDoc = new JMenuItem("Edit file");
        JMenuItem deleteDoc = new JMenuItem("Delete file");

        //Adding action listener
        openDoc.addActionListener(this);
        editDoc.addActionListener(this);
        deleteDoc.addActionListener(this);

        file.add(openDoc);
        file.addSeparator();
        file.add(editDoc);
        file.addSeparator();
        file.add(deleteDoc);


        menuBar.add(folder);
        menuBar.add(file);

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                menuBar,
                tableScroll);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

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

    public int getFather(){
        return fatherID;
    }

    public void reload(){
        ArrayList<String> authors = ctrlPres.getDocumentAuthors(folderID);
        ArrayList<String> documents = ctrlPres.getDocumentTitles(folderID);
        HashMap<Integer, String> subfolders = ctrlPres.getSubFolders(folderID);
        subF = subfolders;
        String[] col = {"Type", "Name", "Author"};
        Object[][] data = new Object[(authors.size() + subfolders.size())][3];
        Icon folderIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/folder-icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        int j = 0;
        for (Integer key : subfolders.keySet()) {
            data[j][0] = folderIcon;
            data[j][1] = subfolders.get(key);
            data[j][2] = "-";
            ++j;
        }
        for (int i = subfolders.size(); i < (authors.size() + subfolders.size()); ++i) {
            data[i][0] = documentIcon;
            data[i][1] = documents.get(i - subfolders.size());
            data[i][2] = authors.get(i - subfolders.size());
        }
        if(data.length != 0)model.setDataVector(data, col);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("Open folder")) {
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
        }
        else if(s.equals("Open file")) {
            int row = table.getSelectedRow();
            if (row != -1) {
                String author = (String) table.getValueAt(row, 2);
                if (!author.equals("-")) {
                    String title = (String) table.getValueAt(row, 1);
                    System.out.println(author);
                    System.out.println(title);
                    ctrlPres.toDocument(author, title, "null", false, false);
                } else JOptionPane.showMessageDialog(new JOptionPane(), "Selection is not a document");
            }
        }
        else if(s.equals("Edit file")) {
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
        else if(s.equals("Delete file")) {
            int row = table.getSelectedRow();
            if (row != -1){
                String author = (String) table.getValueAt(row, 2);
                if (!author.equals("-")){
                    String title = (String) table.getValueAt(row, 1);
                    ctrlPres.deleteDocument(author, title);
                    reload();
                } else JOptionPane.showMessageDialog(new JOptionPane(), "Selection is not a document");
            }
        }
    }
}
