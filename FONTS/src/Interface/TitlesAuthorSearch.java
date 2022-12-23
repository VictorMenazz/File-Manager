package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TitlesAuthorSearch extends JPanel {
    /** Have an Instance of Presentation Layer  */
    private PresentationController CtrlPres = PresentationController.getInstance();

    /** JLabel que indica el text mostrat */
    private JLabel titleView = new JLabel("Get documents from an author");

    /** JLabel que indica el text mostrat */
    private JLabel l = new JLabel("Author's name: ");

    /*public void setAutor(String txtAutor) {
        this.txtAutor.setText(txtAutor);
        search.doClick();
    }*/

    /** JCombo que indica els noms dels Autors */
    private JComboBox<String> authors;

    /** DefaultComboBoxModel especified the model of the ComboBoc */
    private DefaultComboBoxModel<String> model;

    /** JButton that indicates the action of Search */
    private JButton search;


    /** Restarts the View */
    public void load() {
        removeAll();

        //titleView.setBounds(165,5,200,30);
        //add(titleView);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 0, 20, 0);


        ArrayList<String> list = CtrlPres.getAuthorsName();
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) array[i] = list.get(i);

        //String[] l = list.toArray(new String[0]);

        model = new DefaultComboBoxModel<>(array);
        authors = new JComboBox<>(model);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(titleView, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        add(l, c);
        c.gridx = 1;
        c.gridy = 2;
        add(authors, c);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(search, c);

        authors.revalidate();
        authors.repaint();
        setVisible(true);



        ActionListener SearchTitles = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search && authors.getItemCount() != 0) {
                    String a = (String) authors.getSelectedItem();
                    ArrayList<String> titles = CtrlPres.getAuthorDocuments(a);
                    if (titles.isEmpty()){
                        JOptionPane.showMessageDialog(new JDialog(), "Error, Author does not have any Document");
                    } else {
                        showResults(a, titles);
                    }
                }
            }
        };
        search.addActionListener(SearchTitles);
    }

    /** Initializer of the View */
    public TitlesAuthorSearch(){
        setLayout(null);
        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));

        search = new JButton("Search");
        l.setPreferredSize(new Dimension(100, 20));

        load();

    }

    /** Show the results of the Search */
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

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400, 200));

        JLabel title = new JLabel("Titles from " + a);

        c.gridx = 0;
        c.gridy = 0;
        add(title, c);
        c.gridy = 1;
        //c.gridwidth = 3;
        add(tableScroll, c);

        updateUI();
        setVisible(true);
    }

    /** Main function of the View */
    public static void main(String args[]) {
        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(new TitlesAuthorSearch(), BorderLayout.CENTER);
        f.setSize(1000, 750);
        f.setLocation(100, 100);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
