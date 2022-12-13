package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * FIRST VIEW WHEN USERS OPEN THE APP AND WHERE STRUCTURE OF FOLDERS AND DOCUMENTS IS SHOW
 */

public class MainView {

    /**
     * Title of the application
     */
    public static final String appTitle = "File Manager";

    /**
     * Provides nice icons and names for files
     */
    private FileSystemView fileSystemView;

    /**
     * Currently selected File
     */
    private File currentFile;

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
    private JProgressBar progressBar;

    /**
     * Table model for File[]
     */
    private ListSelectionListener listSelectionListener;

    private boolean cellSizesSet = false;
    private int rowIconPadding = 6;
    private JFrame mainView;

    /**
     * File controls
     */
    private JButton openFile;
    private JButton editFile;
    private JButton deleteFile;
    private JButton newFile;

    private JButton newDocument;
    private JButton delete;
    private JButton help;

    private JLabel fileName;
    private JTextField path;
    private JLabel date;
    private JLabel size;
    private JRadioButton isDirectory;
    private JRadioButton isFile;


    //private PresentationController CtrlPres = PresentationController.getInstance();

    public MainView(ArrayList<String> authors, ArrayList<String> documents, HashMap<Integer, String> subfolders) {
        //create frame
        mainView = new JFrame(appTitle);
        mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(panel == null) {
            panel = new JPanel(new BorderLayout(3,3));
            panel.setBorder(new EmptyBorder(5,5,5,5));

            fileSystemView = FileSystemView.getFileSystemView();

            JPanel detailView = new JPanel(new BorderLayout(3,3));

            table = new JTable();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            table.setShowVerticalLines(false);

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
            fileDetailsLabels.add(new JLabel("Path/name", JLabel.TRAILING));
            path = new JTextField(5);
            path.setEditable(false);
            fileDetailsValues.add(path);
            fileDetailsLabels.add(new JLabel("Last Modified", JLabel.TRAILING));
            date = new JLabel();
            fileDetailsValues.add(date);
            fileDetailsLabels.add(new JLabel("File size", JLabel.TRAILING));
            size = new JLabel();
            fileDetailsValues.add(size);
            fileDetailsLabels.add(new JLabel("Type", JLabel.TRAILING));

            JPanel flags = new JPanel(new FlowLayout(FlowLayout.LEADING,4,0));
            isDirectory = new JRadioButton("Directory");
            isDirectory.setEnabled(false);
            flags.add(isDirectory);

            isFile = new JRadioButton("File");
            isFile.setEnabled(false);
            flags.add(isFile);
            fileDetailsValues.add(flags);

            int count = fileDetailsLabels.getComponentCount();
            for (int ii=0; ii<count; ii++) {
                fileDetailsLabels.getComponent(ii).setEnabled(false);
            }

            JToolBar toolBar = new JToolBar();
            // mnemonics stop working in a floated toolbar
            toolBar.setFloatable(false);

            openFile = new JButton("Open");
            openFile.setMnemonic('o');

            openFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    try {

                    } catch(Throwable t) {

                    }

                }
            });
            toolBar.add(openFile);

            editFile = new JButton("Edit");
            editFile.setMnemonic('e');
            editFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {
                    try {

                    } catch(Throwable t) {

                    }
                }
            });
            toolBar.add(editFile);

            toolBar.addSeparator();

            newFile = new JButton("New");
            newFile.setMnemonic('n');
            newFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {

                }
            });
            toolBar.add(newFile);

            JButton renameFile = new JButton("Rename");
            renameFile.setMnemonic('r');
            renameFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {

                }
            });
            toolBar.add(renameFile);

            deleteFile = new JButton("Delete");
            deleteFile.setMnemonic('d');
            deleteFile.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae) {

                }
            });
            toolBar.add(deleteFile);

            toolBar.addSeparator();

            JPanel fileView = new JPanel(new BorderLayout(3,3));

            fileView.add(toolBar,BorderLayout.NORTH);
            fileView.add(fileMainDetails,BorderLayout.CENTER);

            detailView.add(fileView, BorderLayout.SOUTH);

            JTree JTree = null;
            JSplitPane splitPane = new JSplitPane(
                    JSplitPane.HORIZONTAL_SPLIT,
                    JTree,
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

    public JPanel getDefaultPanel() {
        return panel;
    }
    //Provisional Main
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
