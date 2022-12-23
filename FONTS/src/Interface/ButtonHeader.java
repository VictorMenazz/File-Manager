package FONTS.src.Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @file ButtonHeader.java
 * Class <em>ButtonHeader</em>
 */

/**
 * Button Header View
 *
 * @author Júlia Alice Amenós Dien
 * @author Víctor Mena Doz
 */

public class ButtonHeader extends JButton {
    /**
     *
     */
    public ButtonHeader(){
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(6,6,6,6));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paint(Graphics graphics){
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillOval(0,0,getWidth(),getHeight());
        super.paint(graphics);
    }
}
