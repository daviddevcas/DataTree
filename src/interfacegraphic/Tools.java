package interfacegraphic;

import datatree.Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Tools extends JPanel {

    private JTextField equation;
    private JButton draw_tree;
    private JButton get_postfix;
    private JButton get_infix;

    public Tools(int width, int height) {
        this.equation = new JTextField(30);
        this.draw_tree = new JButton("Transformar arbol");
        this.get_postfix = new JButton("Obtener posfija");
        this.get_infix =  new JButton("Obtener infija");
        this.equation.setMargin(new Insets(5, 5, 5, 5));
        this.equation.setFont(Main.getFontText());
        this.draw_tree.setBackground(Main.getBaseBlue().darker());
        this.draw_tree.setForeground(Color.WHITE);
        this.get_postfix.setBackground(Main.getBaseBlue().darker());
        this.get_postfix.setForeground(Color.WHITE);
        this.get_infix.setBackground(Main.getBaseBlue().darker());
        this.get_infix.setForeground(Color.WHITE);
        
        init(width,height);
    }
    
    private void init(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(30, 88, 124));
        this.setBorder(new EmptyBorder(10, 60, 10, 10));
        this.setBorder(new BorderPanelCustome());
        this.add(this.equation, BorderLayout.CENTER);
        this.add(this.draw_tree, BorderLayout.CENTER);
        this.add(this.get_postfix,BorderLayout.CENTER);
        this.add(this.get_infix,BorderLayout.CENTER);
        this.setBorder(BorderFactory.createTitledBorder(new BorderPanelCustome(), "Herramientas", 0, 0, Main.getFontMenu(), Color.WHITE));
    }

    public JTextField getEquation() {
        return equation;
    }

    public JButton getButtonDrawTree() {
        return draw_tree;
    }

    public JButton getButtonGetPostFix() {
        return get_postfix;
    }
    
    public JButton getButtonGetInfix(){
        return get_infix;
    }
    
}

class BorderPanelCustome implements Border{
    
    private Insets insets;
    private boolean opaque;
    
    public BorderPanelCustome(){
        this.insets = new Insets(15,10,15,10); 
        this.opaque = false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.WHITE);
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