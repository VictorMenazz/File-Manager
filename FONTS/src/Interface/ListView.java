package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * @file ListView.java
 * Class <em>ListView</em>
 */

/**
 * View to show a list of the
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */

public class ListView extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel title;
    private JTable table;
    private DefaultTableModel model;


    public ListView(String t, ArrayList<String> list){
        setPreferredSize(new Dimension(500, 350));
        setMaximumSize(new Dimension(500, 350));
        setMinimumSize(new Dimension(500, 350));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 0, 5, 0);

        String[] col = {"Titles"};
        String[][] data = new String[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }

        model = new DefaultTableModel(data, col);
        table = new JTable(model) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setAutoCreateRowSorter(true);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400, 200));

        title = new JLabel(t);

        c.gridx = 0;
        c.gridy = 0;
        add(title);
        c.gridy = 1;
        add(table);

        setVisible(true);

    }

}
