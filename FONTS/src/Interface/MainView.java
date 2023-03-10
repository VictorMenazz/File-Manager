package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
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

public class MainView extends JFrame implements ActionListener {
    /**
     * Instance of the Presentation Controller
     */
    private PresentationController ctrlPres = PresentationController.getInstance();

    /**
     * Menu of items
     */
    private Menu menu;
    /**
     * View AddNewDocument
     */
    private AddNewDocument addNewDocument;
    /**
     * Instance of the view AddNewFolder
     */
    private AddNewFolder addNewFolder;
    /**
     * Instance of the SearchViewN
     */
    private SearchViewN searchViewN;
    /**
     * Instance of ExprManager
     */
    private ExprManager exprManager;
    /**
     * Instance of FolderView
     */
    private FolderView folderView;
    /**
     * Instance of ModifyBoolExpr
     */
    private ModifyBoolExpr modifyBoolExpr;
    /**
     * Instance of CreateBoolExpr
     */
    private CreateBoolExpr createBoolExpr;
    /**
     * Instance of TitlesAuthorSearch
     */
    private TitlesAuthorSearch titlesAuthorSearch;
    /**
     * Instance of AuthorsPrefixSearch
     */
    private AuthorsPrefixSearch authorsPrefixSearch;
    /**
     * Instance of DocumentSearch
     */
    private DocumentSearch documentSearch;
    /**
     * Instance of SimilarityDocumentSearch
     */
    private SimilarityDocumentsSearch similarityDocumentsSearch;
    /**
     * Instance of RelevantDocumentsSearch
     */
    private RelevantDocumentsSearch relevantDocumentsSearch;
    /**
     * Instance of BoolExprSearch
     */
    private BoolExprSearch boolExprSearch;
    /**
     * Instance of ExportDocument
     */
    private ExportDocument exportDocument;
    /**
     * Instance of DeleteDocument
     */
    private DeleteDocument deleteDocument;

    /**
     * Positions for the window
     */
    private int x,y;
    /**
     * Button to close window
     */
    private ButtonHeader bClose;
    /**
     * Button to resize window
     */
    private ButtonHeader bResize;
    /**
     * Button to minimize window
     */
    private ButtonHeader bMin;

    /**
     * Main GUI container
     */
    private JPanel content;
    /**
     * Panel for the header
     */
    private JPanel header;
    /**
     * Panel for the board
     */
    private JPanel panelBoard;
    /**
     * Panel for the title
     */
    private JLabel panelTitle;
    /**
     * Label for the logo
     */
    private JLabel Logo;
    /**
     * Label for the application name
     */
    private JLabel applicationName;
    /**
     * CardLayout for the view
     */
    private CardLayout card;
    /**
     * String for the language
     */
    private String language;
    /**
     * ID of the actual folder
     */
    private int actFolderID;



    /**
     * Constructor of MainView
     */
    public MainView() {
        actFolderID = 0;
        setSize(800, 500);
        setMinimumSize(new Dimension(700,400));
        setLocationRelativeTo(null);//centre window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setUndecorated(true);
        panelBoard = new JPanel();
        panelBoard.setLayout(new BorderLayout());
        setContentPane(panelBoard);

        menu = new Menu(this);
        menu.setPreferredSize(new Dimension(200, 300));

        initializeHeader();
        initializeContent();
        panelBoard.add(menu, BorderLayout.WEST);
        panelBoard.add(content, BorderLayout.CENTER);
        panelBoard.add(header,BorderLayout.NORTH);
        //panelBoard.add(new VistaAbrirDocumento());
        setVisible(true);
    }

    /**
     * Define action of buttons
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bClose){
            try {
                ctrlPres.saveDB();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
        if(e.getSource() == bResize){
            if(this.getExtendedState() == JFrame.MAXIMIZED_BOTH) this.setExtendedState(JFrame.NORMAL);
            else this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        if(e.getSource() == bMin){
            this.setState(JFrame.ICONIFIED);
        }
    }

    /**
     * Initialize view of the header
     */
    private void initializeHeader(){
        //Buttons to close application, minimize and resize
        bClose = new ButtonHeader();
        bResize = new ButtonHeader();
        bMin = new ButtonHeader();

        bClose.addActionListener(this);
        bResize.addActionListener(this);
        bMin.addActionListener(this);

        bClose.setBackground(Color.RED);
        bResize.setBackground(Color.GREEN);
        bMin.setBackground(new Color(255,200,50));

        header = new JPanel();

        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(700,50));

        //Logo and title of the application
        JPanel panelLogo = new JPanel();
        panelLogo.setPreferredSize(new Dimension(200,50));
        panelLogo.setBackground(Color.decode("#8516f5"));

        Logo = new JLabel();
        ImageIcon image = new ImageIcon("FONTS/src/Interface/Utils/fileManager.png");
        Image scaledImage = image.getImage().getScaledInstance(45,45, Image.SCALE_SMOOTH);
        Logo.setIcon(new ImageIcon(scaledImage));

        applicationName = new JLabel("File Manager");
        applicationName.setFont(new Font("sansserif",Font.BOLD ,20));
        applicationName.setForeground(Color.WHITE);

        panelLogo.add(Logo);
        panelLogo.add(applicationName);

        //Panel with buttons
        JPanel buttonsHeader = new JPanel();
        buttonsHeader.setLayout(new FlowLayout());
        buttonsHeader.setPreferredSize(new Dimension(60,50));
        buttonsHeader.add(bMin);
        buttonsHeader.add(bResize);
        buttonsHeader.add(bClose);

        //Rest of the header with the title of the actual content
        panelTitle = new JLabel();
        panelTitle.setFont(new Font("Serif",Font.BOLD,20));
        JPanel restHeader = new JPanel();
        JPanel panelLabel = new JPanel();
        panelLabel.setPreferredSize(new Dimension(75,50));
        restHeader.setLayout(new BorderLayout());
        restHeader.add(panelLabel, BorderLayout.WEST);
        restHeader.add(panelTitle, BorderLayout.CENTER);

        header.add(panelLogo, BorderLayout.WEST);
        header.add(buttonsHeader, BorderLayout.EAST);
        header.add(restHeader);

        MouseMotionAdapter mdrag = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen()-x, e.getYOnScreen()-y);
            }
        };
        MouseAdapter mpres = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        };
        header.addMouseListener(mpres);
        header.addMouseMotionListener(mdrag);
    }

    /**
     * Initialize content of the view
     */
    private void initializeContent(){
        content = new JPanel();
        card = new CardLayout();
        content.setLayout(card);

        addNewDocument = new AddNewDocument(this);
        addNewFolder = new AddNewFolder(this, actFolderID);
        //searchView = new SearchView();
        folderView = new FolderView(this, actFolderID, -1);
        searchViewN = new SearchViewN(this);
        exprManager = new ExprManager(this);
        modifyBoolExpr = new ModifyBoolExpr();
        createBoolExpr = new CreateBoolExpr();
        titlesAuthorSearch = new TitlesAuthorSearch();
        authorsPrefixSearch = new AuthorsPrefixSearch();
        documentSearch = new DocumentSearch();
        similarityDocumentsSearch = new SimilarityDocumentsSearch();
        relevantDocumentsSearch = new RelevantDocumentsSearch();
        boolExprSearch = new BoolExprSearch();
        exportDocument = new ExportDocument(this);
        deleteDocument = new DeleteDocument(this);

        content.add(folderView, "Folder View");
        content.add(searchViewN, "Search");
        content.add(exprManager, "Boolean Expressions");
        content.add(addNewDocument, "Create Document");
        content.add(addNewFolder, "Create Folder");
        content.add(createBoolExpr, "Create Boolean Expression");
        content.add(modifyBoolExpr, "Modify Boolean Expressions List");
        content.add(titlesAuthorSearch, "Titles Author Search");
        content.add(authorsPrefixSearch, "Authors Prefix Search");
        content.add(documentSearch, "Document Search");
        content.add(similarityDocumentsSearch, "Similar Documents Search");
        content.add(relevantDocumentsSearch, "Relevant Documents Search");
        content.add(boolExprSearch, "Boolean Expression Search");
        content.add(exportDocument, "Export Document");
        content.add(deleteDocument, "Delete Document");

        setGo("Main");
    }

    /**
     * Changes the current visible view
     * @param state View to change
     */
    public void setGo(String state) {
        card.show(content, state);
        panelTitle.setText(state);
        menu.setSelected(state);

        addNewDocument.setVisible(false);
        addNewFolder.setVisible(false);
        folderView.setVisible(false);
        searchViewN.setVisible(false);
        exprManager.setVisible(false);
        modifyBoolExpr.setVisible(false);
        createBoolExpr.setVisible(false);
        titlesAuthorSearch.setVisible(false);
        authorsPrefixSearch.setVisible(false);
        documentSearch.setVisible(false);
        similarityDocumentsSearch.setVisible(false);
        relevantDocumentsSearch.setVisible(false);
        boolExprSearch.setVisible(false);
        exportDocument.setVisible(false);
        deleteDocument.setVisible(false);

        switch(state) {
            case "Main":{
                folderView.reload();
                break;
            }
            /*case "Folder View":{
                int aux = folderView.getSelectedFolder();
                new FolderView(this, aux, actFolderID);
                actFolderID = aux;
                break;
            }*/
            case "Import Document":{
                //Create an object of JFileChooser class
                JFileChooser fc = new JFileChooser("File:");
                fc.setMultiSelectionEnabled(true);

                // Invoke the showsOpenDialog function to show the save dialog
                int r = fc.showOpenDialog(null);

                // If the user selects a file
                if (r == JFileChooser.APPROVE_OPTION) {
                    // Set the label to the path of the selected directory
                    File[] selectedFiles = fc.getSelectedFiles();
                    for(File fi : selectedFiles) {
                        String name = fi.getName();
                        int dotIndex = name.lastIndexOf('.');
                        if (dotIndex != -1) {
                            String ext = name.substring(dotIndex + 1);
                            if ((ext.equals("txt")) | ext.equals("xml") | ext.equals("json")) {
                                JDialog chooseLang = new JDialog();
                                chooseLang.setPreferredSize(new Dimension(400, 250));
                                chooseLang.setMaximumSize(new Dimension(400, 250));
                                chooseLang.setMinimumSize(new Dimension(400, 250));
                                chooseLang.setLocationRelativeTo(null);

                                chooseLang.setLayout(new GridBagLayout());
                                GridBagConstraints c = new GridBagConstraints();
                                JComboBox chooser = new JComboBox<>();
                                chooser.addItem("Spanish");
                                chooser.addItem("English");
                                chooser.addItem("Catalan");
                                JButton buttonOK = new JButton("OK");

                                FileReader reader = null;
                                try {
                                    reader = new FileReader(fi.getAbsolutePath());
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                BufferedReader br = new BufferedReader(reader);
                                String title;
                                try {
                                    br.readLine(); //author
                                    title = br.readLine();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                c.gridx = 0;
                                c.gridy = 0;
                                chooseLang.add(new JLabel("Choose the language of the imported file:"), c);
                                c.gridx = 0;
                                c.gridy = 1;
                                chooseLang.add(new JLabel(title), c);
                                c.gridx = 0;
                                c.gridy = 2;
                                chooseLang.add(new JLabel(" "), c);
                                c.gridx = 0;
                                c.gridy = 3;
                                chooseLang.add(chooser, c);
                                c.gridx = 0;
                                c.gridy = 4;
                                chooseLang.add(new JLabel(" "), c);
                                c.gridx = 0;
                                c.gridy = 5;
                                chooseLang.add(buttonOK, c);

                                chooseLang.setVisible(true);

                                ActionListener chooseL = new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == buttonOK) {
                                            language = (String) chooser.getSelectedItem();
                                            chooseLang.setVisible(false);

                                            if (language.equals("Spanish")) language = "ESP";
                                            else if (language.equals("Catalan")) language = "CAT";
                                            else language = "ENG";

                                            ctrlPres.importDocument(fi.getAbsolutePath(), actFolderID, language, ext);

                                            setGo("Main");
                                        }
                                    }
                                };

                                buttonOK.addActionListener(chooseL);

                            } else {
                                JOptionPane.showMessageDialog(this, "Incorrect type of document. It must be .txt, .xml or .json");
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "File doesn't have extension");
                        }
                    }
                    JOptionPane.showMessageDialog(new JOptionPane(), "File/s imported");
                }
                // If the user cancelled the operation
                else JOptionPane.showMessageDialog(this, "Import operation cancelled");
                setGo("Main");
                break;
            }
            case "Export Document":{
                exportDocument.reset();
                break;
            }
            case "Create Document":{
                addNewDocument.reset();
                break;
            }
            case "Delete Document": {
                deleteDocument.reset();
                break;
            }
            /*case "Create Folder":{
                addNewFolder.reset();
                break;
            }*/
            case "Search": {
                searchViewN.restart();
                break;
            }
            case "Boolean Expressions": {
                exprManager.load();
                break;
            }
            case "Create Boolean Expression":{
                createBoolExpr.reset();
                break;
            }
            case "Modify Boolean Expressions List":{
                modifyBoolExpr.reset();
                break;
            }
            case "Titles Author Search":{
                titlesAuthorSearch.load();
                break;
            }
            case "Authors Prefix Search":{
                authorsPrefixSearch.reset();
                break;
            }
            case "Open Document": {
                documentSearch.reset();
                break;
            }
            case "Document Search": {
                documentSearch.reset();
                break;
            }
            case "Similar Documents Search":{
                similarityDocumentsSearch.reset();
                break;
            }
            case "Relevant Documents Search":{
                relevantDocumentsSearch.reset();
                break;
            }
            case "Boolean Expression Search":{
                boolExprSearch.reset();
                break;
            }
        }
    }

    //PROVISIONAL MAIN
    /*public static void main(String args[]) {
        MainView mv = new MainView();
    }*/
}
