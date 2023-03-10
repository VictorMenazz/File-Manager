package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @file MenuItem.java
 * Class <em>MenuItem</em>
 */

/**
 * View for every item of the menu
 *
 * @author Víctor Mena Doz
 */

public class MenuItem extends JPanel{

    /**
     * Indicates if the item is selected
     */
    private boolean selected;
    /**
     * Label for the name
     */
    private JLabel labelName;
    /**
     * Label for the icon
     */
    private JLabel labelIcon;

    /**
     * Constructor for the MenuItem
     * @param icon
     * @param name
     */
    public MenuItem(String icon, String name){
        setLayout(new BorderLayout(5,5));
        setOpaque(false);
        JPanel layout = new JPanel();
        labelName = new JLabel();
        labelIcon = new JLabel();

        ImageIcon image = new ImageIcon(icon);
        Image scaledImage = image.getImage().getScaledInstance(25,25, Image.SCALE_SMOOTH);
        labelIcon.setIcon(new ImageIcon(scaledImage));
        labelName.setText(name);
        labelName.setForeground(Color.white);
        setPreferredSize(new Dimension(200,30));
        layout.setPreferredSize(new Dimension(30,30));

        labelIcon.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        add(labelIcon, BorderLayout.WEST);
        add(labelName, BorderLayout.CENTER);

    }

    /**
     * Selects the item
     * @param selected
     */
    public void setSelected(boolean selected){
        this.selected = selected;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics){
        if(selected){
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255,255,255,80));
            g2.fillRoundRect(10,0, getWidth()-20,getHeight(),5,5);
        }
        super.paintComponent(graphics);
    }

}
