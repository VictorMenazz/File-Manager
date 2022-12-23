package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class RelevantDocumentsSearch extends JPanel {
    private PresentationController CtrlPres = PresentationController.getInstance();
    private JLabel titleView = new JLabel("Get relevant documents");
    private JLabel l = new JLabel("Words to search: ");
    private JLabel l2 = new JLabel("Number of documents to search: ");

    private JLabel l3 = new JLabel("Language: ");

    private JTextField p;
    private JFormattedTextField k;
    private JComboBox<String> lang;

    private JButton search = new JButton("Search");

    public void load() {
        removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 0, 10, 0);

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        add(l, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        add(p, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        add(l2, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 2;
        add(k, c);
        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 3;
        add(l3, c);
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 3;
        add(lang, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        add(search, c);

    }

    public RelevantDocumentsSearch(){
        setLayout(null);

        setPreferredSize(new Dimension(500,350));
        setMaximumSize(new Dimension(500,350));
        setMinimumSize(new Dimension(500,350));
        //titleView.setBounds(175,5,200,30);
        //add(titleView);

        p = new JTextField();
        p.setPreferredSize(new Dimension(400, 20));

        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);  // only allow integers
        formatter.setMinimum(0);  // set the minimum value
        formatter.setMaximum(100);  // set the maximum value
        formatter.setAllowsInvalid(false);  // don't allow invalid input
        k = new JFormattedTextField(formatter);
        k.setPreferredSize(new Dimension(50, 20));

        lang = new JComboBox<>(new String[]{"Spanish", "Catalan", "English"});

        load();

        setVisible(true);



        ActionListener SearchResult = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == search){
                    String language;
                    if (lang.equals("Spanish")) language = "ESP";
                    else if (lang.equals("Catalan")) language = "CAT";
                    else language = "ENG";

                    int num = Integer.parseInt(k.getText());

                    try {
                        HashMap<String, String> aux = CtrlPres.toResultDocQuery(p.getText(), language, num);
                        if (aux.size() == 0) JOptionPane.showMessageDialog(new JDialog(), "Not found the desired number of documents");
                        else {
                            ArrayList<String> resultT = (ArrayList<String>) aux.keySet();
                            ArrayList<String> resultA = (ArrayList<String>) aux.values();
                            showResults(resultT, resultA);
                        }
                    } catch(Exception exc) {
                        JOptionPane.showMessageDialog(new JDialog(), exc.getMessage());
                    }

                }

            }
        };
        search.addActionListener(SearchResult);
    }

    public void showResults(ArrayList<String> resultT, ArrayList<String> resultA) {
        removeAll();
        //taula

    }

    public void reset(){
        k.setText("");
        p.setText("");
        load();
        setVisible(true);
    }


}
