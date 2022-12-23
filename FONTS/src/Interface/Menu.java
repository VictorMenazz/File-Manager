package FONTS.src.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class Menu extends JPanel{

    private HashMap<String,MenuItem> items;
    private MainView principalFrame;

    private MouseListener mouseListener;
    public Menu(MainView frame){
        principalFrame = frame;

        setOpaque(false);
        setLayout(new FlowLayout());
        JPanel space = new JPanel();
        space.setOpaque(false);
        space.setPreferredSize(new Dimension(200,20));
        add(space);
        init();

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0,0, Color.decode("#8516f5"),0,getHeight(),Color.decode("#1f013d"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        super.paintComponent(graphics);
    }


    private void init(){
        mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for(String key : items.keySet()) {
                    if (e.getSource() == items.get(key)) {
                        principalFrame.setGo(key);
                    }
                }
            }
        };

        items = new HashMap<>();
        MenuItem mItem = new MenuItem("FONTS/src/Interface/Utils/main.png", "Main");
        mItem.addMouseListener(mouseListener);
        items.put("Main", mItem);
        add(mItem);
        /*mItem = new MenuItem("FONTS/src/Interface/Utils/createFolder.png", "Create Folder");
        mItem.addMouseListener(mouseListener);
        items.put("Create Folder", mItem);
        add(mItem);*/
        mItem = new MenuItem("FONTS/src/Interface/Utils/addDocument.png", "Create Document");
        mItem.addMouseListener(mouseListener);
        items.put("Create Document", mItem);
        add(mItem);
        mItem = new MenuItem("FONTS/src/Interface/Utils/editDocument.png", "Open Document");
        mItem.addMouseListener(mouseListener);
        items.put("Open Document", mItem);
        add(mItem);
        mItem = new MenuItem("FONTS/src/Interface/Utils/deleteDocument.png", "Delete Document");
        mItem.addMouseListener(mouseListener);
        items.put("Delete Document", mItem);
        add(mItem);
        mItem = new MenuItem("FONTS/src/Interface/Utils/importDocument.png", "Import Document");
        mItem.addMouseListener(mouseListener);
        items.put("Import Document", mItem);
        add(mItem);
        mItem = new MenuItem("FONTS/src/Interface/Utils/exportDocument.png", "Export Document");
        mItem.addMouseListener(mouseListener);
        items.put("Export Document", mItem);
        add(mItem);
        mItem = new MenuItem("FONTS/src/Interface/Utils/search.png", "Search");
        mItem.addMouseListener(mouseListener);
        items.put("Search", mItem);
        add(mItem);
        mItem = new MenuItem("FONTS/src/Interface/Utils/boolean.png", "Boolean Expressions");
        mItem.addMouseListener(mouseListener);
        items.put("Boolean Expressions", mItem);
        add(mItem);
    }

    public void setSelected(String item){
        if(items.containsKey(item)){
            for(String key : items.keySet()){
                if(item.equals(key)){
                    items.get(key).setSelected(true);
                }
                else items.get(key).setSelected(false);
            }
        }
    }
}
