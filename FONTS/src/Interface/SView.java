package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

public class SView implements ItemListener, ActionListener {
    JPanel cards; //a panel that uses CardLayout

    JTextField f1, f2, f31, f32, f41, f42, f43, f61, f62, f5;

    JButton b51;
    JButton s1, s2, s3, s4, s5, s6;


    public void initTextFields() {
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);  // only allow integers
        formatter.setMinimum(0);  // set the minimum value
        formatter.setMaximum(100);  // set the maximum value
        formatter.setAllowsInvalid(false);  // don't allow invalid input

        f1 = new JTextField();
        f1.setPreferredSize(new Dimension(300, 20));
        f2 = new JTextField();
        f2.setPreferredSize(new Dimension(300, 20));
        f31 = new JTextField();
        f31.setPreferredSize(new Dimension(300, 20));
        f32 = new JTextField();
        f32.setPreferredSize(new Dimension(300, 20));
        f41 = new JTextField();
        f41.setPreferredSize(new Dimension(300, 20));
        f42 = new JTextField();
        f42.setPreferredSize(new Dimension(300, 20));
        f43 = new JFormattedTextField(formatter);
        f43.setPreferredSize(new Dimension(300, 20));
        f61 = new JTextField();
        f61.setPreferredSize(new Dimension(300, 20));
        f5 = new JTextField();
        f5.setPreferredSize(new Dimension(700, 20));
        f62 = new JFormattedTextField(formatter);
        f62.setPreferredSize(new Dimension(300, 20));

        b51 = new JButton("Add");
        //b52 = new JButton("Remove");


    }

    public void addComponentToPane(Container pane) {

        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {"Title list", "Authors list", "Find document", "Similarity", "Boolean", "Relevance"};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        initTextFields();


        //GRID
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);


        //CARD1
        JPanel card1 = new JPanel();
        card1.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        card1.add(new JLabel("Title: "), c);
        c.gridx = 1;
        c.gridy = 0;
        card1.add(f1, c);

        //CARD2
        JPanel card2 = new JPanel();
        card2.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        card2.add(new JLabel("Author's prefix: "), c);
        c.gridx = 1;
        c.gridy = 0;
        card2.add(f2, c);

        //CARD3
        JPanel card3 = new JPanel();
        card3.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        card3.add(new JLabel("Title: "), c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        card3.add(f31, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        card3.add(new JLabel("Author: "), c);
        c.gridx = 1;
        c.gridy = 1;
        card3.add(f32, c);

        //CARD4
        JPanel card4 = new JPanel();
        card4.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        card4.add(new JLabel("Title: "), c);
        c.gridx = 1;
        c.gridy = 0;
        card4.add(f41, c);
        c.gridx = 0;
        c.gridy = 1;
        card4.add(new JLabel("Author: "), c);
        c.gridx = 1;
        c.gridy = 1;
        card4.add(f42, c);
        c.gridx = 0;
        c.gridy = 2;
        card4.add(new JLabel("Number of desired documents: "), c);
        c.gridx = 1;
        c.gridy = 2;
        card4.add(f43, c);

        //CARD5
        String boolExpr[] = {"Ejemplo1", "Ejemplo2", "Find document", "Similarity", "Boolean", "Relevance"};    //conectar para obtener data
        JComboBox beList = new JComboBox(boolExpr);
        beList.setPreferredSize(new Dimension(700, 25));

        JPanel card5 = new JPanel();
        card5.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        card5.add(beList, c);
        c.gridx = 0;
        c.gridy = 1;
        card5.add(f5, c);
        c.gridx = 0;
        c.gridy = 2;
        card5.add(b51, c);


        //CARD6
        JPanel card6 = new JPanel();
        card6.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        card6.add(new JLabel("Words to search: "), c);
        c.gridx = 1;
        c.gridy = 0;
        card6.add(f61, c);
        c.gridx = 0;
        c.gridy = 1;
        card6.add(new JLabel("Number of documents to search: "), c);
        c.gridx = 1;
        c.gridy = 1;
        card6.add(f62, c);


        //BUTTONS
        JButton b = new JButton("Search");

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, comboBoxItems[0]);
        cards.add(card2, comboBoxItems[1]);
        cards.add(card3, comboBoxItems[2]);
        cards.add(card4, comboBoxItems[3]);
        cards.add(card5, comboBoxItems[4]);
        cards.add(card6, comboBoxItems[5]);

        pane.setSize(1000, 750);
        pane.setLayout(new GridBagLayout());

        GridBagConstraints cPane = new GridBagConstraints();
        cPane.insets = new Insets(0, 0, 30, 0);

        cPane.gridx = 0;
        cPane.gridy = 0;
        pane.add(comboBoxPane, cPane);

        //cPane.insets = new Insets(0, 0, 0, 0);
        cPane.gridx = 0;
        cPane.gridy = 1;
        pane.add(cards, cPane);

        cPane.gridx = 4;
        cPane.gridy = 2;
        //pane.add(b, cPane);


    }



    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Search Menu");
        frame.setSize(1000, 750);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        SView demo = new SView();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }

    private void titleListSearch(){
        //resultats busqueda 1

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("s1")){
            titleListSearch();
        } else if (s.equals("s2")){

        }

    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }




}
