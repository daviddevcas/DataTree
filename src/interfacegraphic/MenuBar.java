package interfacegraphic;

import datatree.Main;
import interfacegraphic.Menu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class MenuBar extends JMenuBar {

    private Color background = Main.getBaseBlue(), font_color = Color.white;
    private Menu about,file;
    private Item developer, close;
    private Border border;

    public MenuBar() {
        this.background = this.background.darker();
        this.setBackground(background);
        this.setForeground(font_color);
        this.setFont(Main.getFontMenu());
        this.setBorder(border);

        init();
    }

    private void init() {
        this.file = new Menu("Archivo");
        this.about = new Menu("Acerca de");
        
        items();
        
        this.add(file);
        this.add(about);
    }
    
    private void items(){
        this.developer = new Item("Desarrollador");
        this.developer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_DOWN_MASK));
        
        this.developer.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this.developer,"<html><h1>Desarrollado por:</h1><h2>Hansell David Devilet Castellanos", "Desarrollador", JOptionPane.INFORMATION_MESSAGE);
        });
        this.about.add(this.developer);
        
        this.close = new Item("Salir");
        this.close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_DOWN_MASK));
        this.close.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        this.file.add(this.close);
    }
}
