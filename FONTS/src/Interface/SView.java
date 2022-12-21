package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SView implements ItemListener {
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";

    JButton bSearch;

    public void addComponentToPane(Container pane) {

        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {"Title list", "Authors list", "Find document", "Similarity", "Boolean", "Relevance"};
        JComboBox cb = new JComboBox(comboBoxItems);
        bSearch = new JButton("Get Results");
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        //ENTRY
        JPanel jp1 = new JPanel();
        JTextField a = new JTextField("", 10);
        jp1.add(new JLabel("Author prefix "));
        jp1.add(a);    //Agafar text amb entry.getText()

        JPanel jp2 = new JPanel();
        JTextField t = new JTextField("", 10);
        jp2.add(new JLabel("Title "));
        jp2.add(t);    //Agafar text amb entry.getText()

        JPanel jp3 = new JPanel();
        JTextField author = new JTextField("", 10);
        jp3.add(new JLabel("Title "));
        jp3.add(author);    //Agafar text amb entry.getText()

        JPanel jp4 = new JPanel();
        JTextField title = new JTextField("", 10);
        jp4.add(new JLabel("Author "));
        jp4.add(title);    //Agafar text amb entry.getText()

        JPanel jp5 = new JPanel();
        JTextField k = new JTextField("", 10);
        jp5.add(new JLabel("k "));
        jp5.add(k);    //Agafar text amb entry.getText()




        //CARD1
        JPanel card1 = new JPanel();
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));
        card1.add(jp1);


        //CARD2
        JPanel card2 = new JPanel();
        card2.setLayout(new BoxLayout(card2, BoxLayout.Y_AXIS));
        card2.add(jp2);


        //CARD3
        JPanel card3 = new JPanel();
        card3.setLayout(new BoxLayout(card3, BoxLayout.Y_AXIS));
        card3.add(jp4);
        card3.add(jp3);


        //CARD4
        JPanel card4 = new JPanel();
        card4.setLayout(new BoxLayout(card4, BoxLayout.Y_AXIS));
        card4.add(jp5);




        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, comboBoxItems[0]);
        cards.add(card2, comboBoxItems[1]);
        cards.add(card3, comboBoxItems[2]);
        cards.add(card4, comboBoxItems[3]);

        pane.setSize(1000, 750);


        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);


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
