package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AuthorsPrefixSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get authors list from a prefix");
    private JLabel l = new JLabel("Author's prefix: ");


    private JTextArea prefix = new JTextArea();

    private JButton search = new JButton("Search");

    public void load() {
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
                        //showResults();
                    }

                }
            }
        };
        search.addActionListener(SearchAuthors);
    }

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

        table.setAutoCreateRowSorter(true);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400, 200));

        JLabel title = new JLabel("Titles from " + a);

        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        c.gridy = 1;
        //c.gridwidth = 3;
        add(table, c);

        updateUI();
        setVisible(true);
    }


    public void reset() {
        prefix.setText("");
        setVisible(true);
    }

    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new AuthorsPrefixSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
