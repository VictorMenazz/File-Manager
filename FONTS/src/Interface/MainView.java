package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file MainView.java
 * Class <em>MainView</em>
 */

/**
 * First view when users open the app and where structure of folders and documents is show
 *
 * @author Víctor Mena Doz
 */

public class MainView implements ActionListener, MouseListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Title of the application
     */
    public static final String appTitle = "File Manager";

    /**
     * Provides nice icons and names for files
     */
    private FileSystemView fileSystemView;

    /**
     * Main GUI container
     */
    private JPanel panel;

    /** File-system tree. Built Lazily
     private JTree tree;
     private DefaultTreeModel treeModel; */

    /**
     * Directory listing
     */
    private JTable table;

    /**
     * To see the progress of an action
     */
    private JProgressBar progressBar;

    /**
     * Table model for File[]
     */
    private ListSelectionListener listSelectionListener;

    /**
     * Frame of the view
     */
    private JFrame mainView;

    /**
     * File controls
     */
    /**
     * Label for show file's name
     */
    private JLabel fileName;

    /**
     * Label for show author's name
     */
    private JLabel author;

    /**
     * Radiobutton that says if it's a directory
     */
    private JRadioButton isDirectory;

    /**
     * Radiobutton that says if it's a file
     */
    private JRadioButton isFile;

    /**
     * List of subfolders
     */
    private HashMap<Integer, String> subF;

    /**
     * Constructor of MainView
     * @param authors, list of authors of files inside the folder
     * @param documents, list of documents' titles of files inside the folder
     * @param subfolders, list of subfolders
     */
    public MainView(ArrayList<String> authors, ArrayList<String> documents, HashMap<Integer, String> subfolders) {
        subF = subfolders;

        //create frame
        mainView = new JFrame(appTitle);
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(panel == null) {
            panel = new JPanel(new BorderLayout(3,3));
            panel.setBorder(new EmptyBorder(5,5,5,5));

            fileSystemView = FileSystemView.getFileSystemView();

            JPanel detailView = new JPanel(new BorderLayout(3,3));

            Object[][] data = new Object[(authors.size()+ subfolders.size())][3];
            Icon folderIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/folder-icon.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            Icon documentIcon = new ImageIcon(new ImageIcon("FONTS/src/Interface/Utils/icone-fichier-document-noir.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
            int j = 0;
            for(Integer key : subfolders.keySet()) {
                data[j][0] = folderIcon;
                data[j][1] = subfolders.get(key);
                data[j][2] = "-";
                ++j;
            }
            for(int i = subfolders.size(); i < (authors.size()+ subfolders.size()); ++i) {
                data[i][0] = documentIcon;
                data[i][1] = documents.get(i - subfolders.size());
                data[i][2] = authors.get(i - subfolders.size());
            }

            String[] columnNames = {"Type", "Name", "Author"};

            DefaultTableModel model = new DefaultTableModel(data, columnNames)
            {
                //  Returning the Class of each column will allow different
                //  renderers to be used based on Class
                public Class getColumnClass(int column)
                {
                    return getValueAt(0, column).getClass();
                }
            };

            table = new JTable(model) {
                private static final long serialVersionUID = 1L;
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
            //table.set;
            table.setAutoCreateRowSorter(true);
            table.setShowVerticalLines(false);
            table.setRowHeight(30);
            table.getColumnModel().getColumn(0).setMinWidth(50);
            table.getColumnModel().getColumn(0).setMaxWidth(50);



            table.getSelectionModel().addListSelectionListener(listSelectionListener);
            JScrollPane tableScroll = new JScrollPane(table);
            Dimension d = tableScroll.getPreferredSize();
            tableScroll.setPreferredSize(new Dimension((int)d.getWidth(), (int)d.getHeight()/2));
            detailView.add(tableScroll, BorderLayout.CENTER);

            // details for a File
            JPanel fileMainDetails = new JPanel(new BorderLayout(4,2));
            fileMainDetails.setBorder(new EmptyBorder(0,6,0,6));

            JPanel fileDetailsLabels = new JPanel(new GridLayout(0,1,2,2));
            fileMainDetails.add(fileDetailsLabels, BorderLayout.WEST);

            JPanel fileDetailsValues = new JPanel(new GridLayout(0,1,2,2));
            fileMainDetails.add(fileDetailsValues, BorderLayout.CENTER);

            fileDetailsLabels.add(new JLabel("File", JLabel.TRAILING));
            fileName = new JLabel();
            fileDetailsValues.add(fileName);
            fileDetailsLabels.add(new JLabel("Author", JLabel.TRAILING));
            author = new JLabel();
            fileDetailsValues.add(author);

            fileDetailsLabels.add(new JLabel("Type", JLabel.TRAILING));

            JPanel flags = new JPanel(new FlowLayout(FlowLayout.LEADING,4,0));
            isDirectory = new JRadioButton("Directory");
            isDirectory.setEnabled(false);
            flags.add(isDirectory);

            isFile = new JRadioButton("File");
            isFile.setEnabled(false);
            flags.add(isFile);
            fileDetailsValues.add(flags);

            JPanel fileView = new JPanel(new BorderLayout(3,3));
            fileView.add(fileMainDetails,BorderLayout.CENTER);
            detailView.add(fileView, BorderLayout.SOUTH);

            //Create menuBar
            JMenuBar menuBar = new JMenuBar();

            //Create menu for Bar
            JMenu folder = new JMenu("Folder");
            //Create items for menu
            JMenuItem newFolder = new JMenuItem("New folder");
            JMenuItem openFolder = new JMenuItem("Open folder");
            JMenuItem editFolder = new JMenuItem("Edit folder");
            JMenuItem deleteFolder = new JMenuItem("Delete folder");


            //Adding action listener
            newFolder.addActionListener(this);
            openFolder.addActionListener(this);
            openFolder.addMouseListener(this);
            editFolder.addActionListener(this);
            deleteFolder.addActionListener(this);

            folder.add(newFolder);
            folder.addSeparator();
            folder.add(openFolder);
            folder.addSeparator();
            folder.add(editFolder);
            folder.addSeparator();
            folder.add(deleteFolder);



            JMenu file = new JMenu("File");
            //Create items for menu
            JMenuItem newDoc = new JMenuItem("New file");
            JMenuItem openDoc = new JMenuItem("Open file");
            JMenuItem importDoc = new JMenuItem("Import file");
            JMenuItem exportDoc = new JMenuItem("Export file");
            JMenuItem editDoc = new JMenuItem("Edit file");
            JMenuItem deleteDoc = new JMenuItem("Delete file");

            //Adding action listener
            newDoc.addActionListener(this);
            openDoc.addActionListener(this);
            importDoc.addActionListener(this);
            exportDoc.addActionListener(this);
            editDoc.addActionListener(this);
            deleteDoc.addActionListener(this);

            file.add(newDoc);
            file.addSeparator();
            file.add(openDoc);
            file.addSeparator();
            file.add(importDoc);
            file.addSeparator();
            file.add(exportDoc);
            file.addSeparator();
            file.add(editDoc);
            file.addSeparator();
            file.add(deleteDoc);

            JMenuItem search = new JMenuItem("Search");
            search.setMinimumSize(new Dimension(40, search.getPreferredSize().height));
            search.setMaximumSize(new Dimension(40, search.getPreferredSize().height));
            search.addActionListener(this);

            JMenuItem help = new JMenuItem("Help");
            help.setMinimumSize(new Dimension(30, help.getPreferredSize().height));
            help.setMaximumSize(new Dimension(30, help.getPreferredSize().height));
            help.addActionListener(this);

            JMenuItem close = new JMenuItem("Close");
            close.setMinimumSize(new Dimension(35, close.getPreferredSize().height));
            close.setMaximumSize(new Dimension(35, close.getPreferredSize().height));
            close.addActionListener(this);

            menuBar.add(folder);
            menuBar.add(file);
            menuBar.add(search);
            menuBar.add(help);
            menuBar.add(Box.createGlue());
            menuBar.add(Box.createGlue());
            menuBar.add(close);

            JSplitPane splitPane = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    menuBar,
                    detailView);
            panel.add(splitPane, BorderLayout.CENTER);

            JPanel simpleOutput = new JPanel(new BorderLayout(3,3));
            progressBar = new JProgressBar();
            simpleOutput.add(progressBar, BorderLayout.EAST);
            progressBar.setVisible(false);

            panel.add(simpleOutput, BorderLayout.SOUTH);
        }

        mainView.setContentPane(panel);
        mainView.setSize(1000, 750);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - mainView.getWidth()) / 2;
        int y = (screenSize.height - mainView.getHeight()) / 2;
        mainView.setLocation(x, y);
        mainView.setVisible(true);
    }

    /**
     * Getter of the panel of the view class
     * @return panel of the MainView
     */
    public JPanel getDefaultPanel() {
        return panel;
    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if(s.equals("New folder")) {
            ctrlPres.toAddNewFolder();
        }
        else if(s.equals("Open folder")) {
            int row = table.getSelectedRow();
            String name = (String) table.getValueAt(row, 1);
            int folderID = 0;
            for(Integer aux : subF.keySet()) {
                if(subF.get(aux) == name) {
                    folderID = aux;
                    break;
                }
            }
            ctrlPres.toFolderView(folderID);
        }
        else if(s.equals("Edit folder")) {

        }
        else if(s.equals("Delete folder")) {
            ctrlPres.toDeleteFolder();
        }
        else if(s.equals("New file")) {
            ctrlPres.toAddNewDocument();
        }
        else if(s.equals("Open file")) {
            int row = table.getSelectedRow();
            String author = (String) table.getValueAt(row, 2);
            String title = (String) table.getValueAt(row, 1);
            ctrlPres.toDocument(author, title, false);
        }
        else if(s.equals("Import file")) {
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
                    if((ext.equals("txt")) | ext.equals("xml") | ext.equals("json")) {
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
                            String n = fi.getName();
                            String author = "IMPORTED";
                            //HACER LLAMADA PARA SABER AUTOR IDIOMA Y GUARDAR
                        }
                        catch (Exception evt) {
                            JOptionPane.showMessageDialog(mainView, evt.getMessage());
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(mainView, "Incorrect type of document. It must be .txt, .xml or .json");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(mainView, "File doesn't have extension");
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(mainView, "Import operation cancelled");
        }
        else if(s.equals("Export file")) {
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
                    //w.write(); OBTENER CONTENIDO DEL DOCUMENTO

                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(mainView, evt.getMessage());
                }
            }
            // If the user cancelled the operation
            else
                JOptionPane.showMessageDialog(mainView, "Export operation canceled");
        }
        else if(s.equals("Edit file")) {
            ctrlPres.toDeleteDoc();
        }
        else if(s.equals("Delete file")) {
            ctrlPres.toDeleteDoc();
        }
        else if(s.equals("Search")) {
            ctrlPres.toSearch();
        }
        else if(s.equals("Help")) {

        }
        else if(s.equals("Close")) {
            try {
                ctrlPres.saveDB();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
    }

    /**
     * Define function of mouse clicked
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Define function of mouse pressed
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Define function of mouse released
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Define function of mouse entered
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Define function of mouse exited
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    //PROVISIONAL MAIN
    public static void main(String args[]) {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Victor");
        authors.add("Jesus Andujar");

        ArrayList<String> documents = new ArrayList<>();
        documents.add("Marc burro");
        documents.add("Julia la mas lista");

        HashMap<Integer, String> subfolders = new HashMap<>();
        subfolders.put(1, "AUX");

        MainView mv = new MainView(authors, documents, subfolders);
    }
}
