
package interfacegraphic;

import datatree.Main;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

public class Item extends JMenuItem{
    
    private final BorderItemCustome border;
    private Color background = Main.getBaseBlue(), font_color =  Color.white;
    
    public Item(String value){
        this.border = new BorderItemCustome();
        
        this.setText(value);
        this.setBackground(background);
        this.setFont(Main.getFontMenu());
        this.setBorder(border);
        this.setForeground(font_color);
    }
}

class BorderItemCustome implements Border{
    
    private Insets insets;
    private boolean opaque;
    
    public BorderItemCustome(){
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