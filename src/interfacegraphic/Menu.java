
package interfacegraphic;

import datatree.Main;
import interfacegraphic.Item;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class Menu extends JMenu{
    
    private final BorderMenuCustome border_menu;
    private final BorderPopupMenuCustome border_popup;
    private Color background = Main.getBaseBlue().brighter(), font_color =  Color.white;
        
    public Menu(String value){
        this.border_menu = new BorderMenuCustome();
        this.border_popup = new BorderPopupMenuCustome();
        
        this.setBackground(background);
        this.setForeground(font_color);
        this.setFont(Main.getFontMenu());
        this.setText(value);
        this.setBorder(border_menu);
        this.getPopupMenu().setBackground(background);
        this.getPopupMenu().setBorder(border_popup);
    }
  
}

class BorderMenuCustome implements Border{
    
    private Insets insets;
    private boolean opaque;
    
    public BorderMenuCustome(){
        this.insets = new Insets(5,5,5,5); 
        this.opaque = false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        //g.drawRect(x, y, width-1, height-1);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return this.insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return this.opaque;
    }
    
}

class BorderPopupMenuCustome implements Border{
    
    private Insets insets;
    private boolean opaque;
    
    public BorderPopupMenuCustome(){
        this.insets = new Insets(1,1,1,1); 
        this.opaque = false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width-1, height-1);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return this.insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return this.opaque;
    }
    
}


