package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @file SearchViewN.java
 * Class <em>SearchViewN</em>
 */

/**
 * View to list all possible queries
 *
 * @author Júlia Alice Amenós Dien
 */


public class SearchViewN extends JPanel {

    /**
     * Instance of the Presentation Controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();

    /**
     * Button to search titles from author
     */
    private JButton b1 = new JButton("Search titles by author");
    /**
     * Button to search authors from prefix
     */
    private JButton b2 = new JButton("Search authors by prefix");
    /**
     * Button to search a concrete document
     */
    private JButton b3 = new JButton("Search concrete document");
    /**
     * Button to search similar documents
     */
    private JButton b4 = new JButton("Search similar documents");
    /**
     * Button to search relevant documents
     */
    private JButton b5 = new JButton("Search relevant documents");
    /**
     * Button to search by boolean expression
     */
    private JButton b6 = new JButton("Search documents by boolean expression");

    /**
     * Panel for the results view
     */
    private TitlesAuthorSearch titlesAuthorSearch;

    /**
     * Creator of the SearchViewN
     * @param mv Main View
     */
    public SearchViewN(MainView mv) {
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        b1.setMinimumSize(new Dimension(300, 40));
        b1.setMaximumSize(new Dimension(300, 40));
        b1.setPreferredSize(new Dimension(300, 40));
        b2.setMinimumSize(new Dimension(300, 40));
        b2.setMaximumSize(new Dimension(300, 40));
        b2.setPreferredSize(new Dimension(300, 40));
        b3.setMinimumSize(new Dimension(300, 40));
        b3.setMaximumSize(new Dimension(300, 40));
        b3.setPreferredSize(new Dimension(300, 40));
        b4.setMinimumSize(new Dimension(300, 40));
        b4.setMaximumSize(new Dimension(300, 40));
        b4.setPreferredSize(new Dimension(300, 40));
        b5.setMinimumSize(new Dimension(300, 40));
        b5.setMaximumSize(new Dimension(300, 40));
        b5.setPreferredSize(new Dimension(300, 40));
        b6.setMinimumSize(new Dimension(300, 40));
        b6.setMaximumSize(new Dimension(300, 40));
        b6.setPreferredSize(new Dimension(300, 40));

        /*b1.setBackground(Color.decode("#8516f5"));
        b2.setBackground(Color.decode("#8516f5"));
        b3.setBackground(Color.decode("#8516f5"));
        b4.setBackground(Color.decode("#8516f5"));
        b5.setBackground(Color.decode("#8516f5"));
        b6.setBackground(Color.decode("#8516f5"));

        b1.setForeground(Color.white);
        b2.setForeground(Color.white);
        b3.setForeground(Color.white);
        b4.setForeground(Color.white);
        b5.setForeground(Color.white);
        b6.setForeground(Color.white);*/

        c.gridx = 0;
        c.gridy = 0;
        add(b1, c);
        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 2;
        add(b2, c);
        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 4;
        add(b3, c);
        c.gridx = 0;
        c.gridy = 5;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 6;
        add(b4, c);
        c.gridx = 0;
        c.gridy = 7;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 8;
        add(b5, c);
        c.gridx = 0;
        c.gridy = 9;
        add(new JLabel(" "), c);
        c.gridx = 0;
        c.gridy = 10;
        add(b6, c);

        setVisible(true);

        ActionListener chooseSearch = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1){
                    mv.setGo("Titles Author Search");
                } else if (e.getSource() == b2){
                    mv.setGo("Authors Prefix Search");
                } else if (e.getSource() == b3){
                    mv.setGo("Document Search");
                } else if (e.getSource() == b4){
                    mv.setGo("Similar Documents Search");
                } else if (e.getSource() == b5){
                    mv.setGo("Relevant Documents Search");
                } else if (e.getSource() == b6){
                    mv.setGo("Boolean Expression Search");
                }
            }

        };
        b1.addActionListener(chooseSearch);
        b2.addActionListener(chooseSearch);
        b3.addActionListener(chooseSearch);
        b4.addActionListener(chooseSearch);
        b5.addActionListener(chooseSearch);
        b6.addActionListener(chooseSearch);

    }

    /**
     * Restarts the view
     */
    public void restart(){
        setVisible(true);
    }

    /*public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new SearchViewN(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }*/
}
