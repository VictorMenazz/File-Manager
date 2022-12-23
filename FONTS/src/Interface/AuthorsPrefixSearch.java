package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @file AuthorsPrefixSearch.java
 * Class <em>AuthorsPrefixSearch</em>
 */

/**
 * Add New Document View
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */


public class AuthorsPrefixSearch extends JPanel {
    /**
     * Instance of the presentation controller
     */
    private PresentationController CtrlPres = PresentationController.getInstance();
    /**
     * Title label of the view
     */
    private JLabel titleView = new JLabel("Get authors list from a prefix");
    /**
     * Authors prefix label
     */
    private JLabel l = new JLabel("Author's prefix: ");

    /**
     * TextField for the prefix
     */
    private JTextField prefix = new JTextField();
    /**
     * Button to make the query
     */
    private JButton search = new JButton("Search");

    /**
     * Loads and shows the view
     */
    public void load() {
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 20, 0);

        c.gridx = 0;
        c.gridy = 1;
        add(l, c);
        c.gridx = 1;
        c.gridy = 1;
        add(prefix, c);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(titleView, c);
        c.gridx = 0;
        c.gridy = 2;
        add(search, c);

    }

    /**
     * Constructor of the AuthorsPrefixSearch
     */
    public AuthorsPrefixSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        prefix.setPreferredSize(new Dimension(250, 20));

        load();

        setVisible(true);



        ActionListener SearchAuthors = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search) {
                    ArrayList<String> result = CtrlPres.toResultSearchAuthors(prefix.getText());
                    if (result.size() == 0) JOptionPane.showMessageDialog(new JDialog(), "No authors starting with " + prefix.getText() + " found.");
                    else {
                        showResults(prefix.getText(), result);
                    }

                }
            }
        };
        search.addActionListener(SearchAuthors);
    }

    /**
     * Shows the results of the query
     * @param a Prefix searched
     * @param titles Titles from the author
     */
    public void showResults(String a, ArrayList<String> titles) {
        removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);

        String[] col = {"Titles"};
        String[][] data = new String[titles.size()][1];
        for (int i = 0; i < titles.size(); i++) {
            data[i][0] = titles.get(i);
        }

        DefaultTableModel model = new DefaultTableModel(data, col);
        JTable table = new JTable(model) {
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

        table.setAutoCreateRowSorter(true);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400, 200));

        JLabel title = new JLabel("Authors with prefix " + a);

        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        c.gridy = 1;
        //c.gridwidth = 3;
        add(tableScroll, c);

        updateUI();
        setVisible(true);
    }

    /**
     * Resets the view
     */
    public void reset() {
        prefix.setText("");
        load();
        setVisible(true);
    }
}
