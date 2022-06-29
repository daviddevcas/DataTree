package datatree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Node {

    private Node left_node , right_node;
    private String value, name;
    private Rectangle rectangle;
    private Color color_node = Main.getBaseBlue();
    private int father_x, father_y, x, y, diameter;

    public Node(String value) {
        this.value = value;
        this.left_node = null;
        this.right_node = null;
        this.name = String.valueOf("");
    }

    public Node(String name, String value) {
        this.name = name;
        this.value = value;
        this.left_node = null;
        this.right_node = null;
    }

    public Node(String name, String value, int x, int y, int diameter) {
        this.name = name;
        this.value = value;
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.rectangle = new Rectangle(this.x - (this.diameter / 2), this.y, this.diameter, this.diameter);
    }

    public Node getLeftNode() {
        return left_node;
    }

    public void setLeftNode(Node leftNode) {
        this.left_node = leftNode;
    }

    public Node getRightNode() {
        return right_node;
    }

    public void setRightNode(Node rightNode) {
        this.right_node = rightNode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setCoordinates(int x, int y, int diameter) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.rectangle = new Rectangle(this.x - (this.diameter / 2), this.y, this.diameter, this.diameter);
    }

    public void setLine(int x, int y) {
        this.father_x = x;
        this.father_y = y;
    }

    public void setColor(Color color) {
        this.color_node = color;
    }

    public void drawNode(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawLine(this.father_x, this.father_y + this.diameter / 2, this.x, this.y + this.diameter / 2);
        g.setColor(this.color_node);
        g.fillOval(this.x - (this.diameter / 2), this.y, this.diameter, this.diameter);
        g.setColor(Color.WHITE);
        g.drawString(this.getValue(), this.x - (this.getValue().length() * 5), this.y + (this.diameter / 2) + 7);
        g.setColor(Color.BLACK);
        g.drawOval(this.x - (this.diameter / 2) - 1, this.y - 1, this.diameter + 1, this.diameter + 1);
    }
}
