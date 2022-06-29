package datatree;

import interfacegraphic.MenuBar;
import interfacegraphic.Tools;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JPanel {

    private static final Font FONT_GENERAL = new Font("Arial", Font.CENTER_BASELINE, 20), FONT_MENU = new Font("Arial", Font.CENTER_BASELINE, 13);
    private static final Font FONT_TEXT = new Font("Arial", Font.PLAIN, 18), FONT_LABEL_PANE = new Font("Arial", Font.PLAIN, 28);
    private static final Color BASE_BLUE = new Color(30, 88, 124), BASE_GREEN = new Color(0, 70, 50);
    private final int DIAMETER = 45;
    private int FRAME_HEIGHT = 600, FRAME_WIDTH = 1000;
    private BufferedImage buffer;
    private Graphics2D g2d;
    private DataTree datatree;
    private ArrayList<Node> nodes;

    public Main() {
        super();
        this.setBorder(BorderFactory.createTitledBorder("Lienzo"));
        init();
    }

    private void init() {
        this.setPreferredSize(new Dimension(this.FRAME_WIDTH, this.FRAME_HEIGHT));
        this.datatree = new DataTree();
        this.nodes = new ArrayList<>();
        this.buffer = new BufferedImage(this.FRAME_WIDTH, this.FRAME_HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.g2d = buffer.createGraphics();
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void getNodesTree(Node root, int width, int height, int pos) {
        if (pos == 1) {
            root.setCoordinates(width, height, this.DIAMETER);
            root.setLine(width, height);
            nodes.add(root);
        }

        if (root.getRightNode() != null) {
            root.getRightNode().setCoordinates(width + (100 * pos) + this.DIAMETER, height + this.DIAMETER + 10, this.DIAMETER);
            root.getRightNode().setLine(width, height);
            nodes.add(root.getRightNode());
            getNodesTree(root.getRightNode(), width + (100 * pos) + this.DIAMETER, height + this.DIAMETER + 10, 0);
        }

        if (root.getLeftNode() != null) {
            root.getLeftNode().setCoordinates(width - (100 * pos) - this.DIAMETER, height + this.DIAMETER + 10, this.DIAMETER);
            root.getLeftNode().setLine(width, height);
            nodes.add(root.getLeftNode());
            getNodesTree(root.getLeftNode(), width - (100 * pos) - this.DIAMETER, height + this.DIAMETER + 10, 0);
        }

        if (root.getLeftNode() == null && root.getRightNode() == null) {
            root.setColor(this.BASE_GREEN);
        }
    }

    private void renderingTree() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (i != j) {
                    if (nodes.get(j).getRectangle().intersects(nodes.get(i).getRectangle())) {
                        Node node_j = nodes.get(j);
                        Node node_i = nodes.get(i);

                        setCoordinatesTree(node_j, false);
                        setCoordinatesTree(node_i, true);

                        renderingTree();
                        return;
                    }
                }
            }
        }
    }

    private void setCoordinatesTree(Node root, boolean orientation) {
        int change = 0;

        if (orientation) {
            change = this.DIAMETER / 2 + 5;
        } else {
            change = -this.DIAMETER / 2 - 5;
        }

        root.setCoordinates(root.getX() + change, root.getY(), root.getDiameter());

        if (root.getRightNode() != null) {
            root.getRightNode().setLine(root.getX(), root.getY());
            setCoordinatesTree(root.getRightNode(), orientation);
        }

        if (root.getLeftNode() != null) {
            root.getLeftNode().setLine(root.getX(), root.getY());
            setCoordinatesTree(root.getLeftNode(), orientation);
        }
    }

    public static Font getFontGeneral() {
        return FONT_GENERAL;
    }

    public static Color getBaseBlue() {
        return BASE_BLUE;
    }

    public static Font getFontMenu() {
        return FONT_MENU;
    }

    public static Font getFontText() {
        return FONT_TEXT;
    }

    public static Font getFontLabelPanel() {
        return FONT_LABEL_PANE;
    }

    public void setBuffer(int width, int height) {
        this.FRAME_WIDTH = width;
        this.FRAME_HEIGHT = height;
        this.buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.g2d = buffer.createGraphics();
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2d.setColor(new Color(240, 240, 240));
        this.g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void drawRoot(String expression) {
        this.datatree.testExpression(expression);
        this.nodes.clear();
        this.g2d.setColor(Color.WHITE);
        this.g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.g2d.setColor(Color.BLACK);
        this.g2d.setFont(Main.getFontGeneral());
        if (this.datatree.getRoot() != null) {
            getNodesTree(this.datatree.getRoot(), this.FRAME_WIDTH / 2, 20, 1);
            if (!nodes.isEmpty()) {
                renderingTree();
                for (int i = nodes.size() - 1; i >= 0; i--) {
                    nodes.get(i).drawNode(g2d);
                }
            }
        }
        this.repaint();
    }

    public void showPostFix(String expression) {
        this.datatree.showPostFix(expression);
    }

    public void showInfix(String expression) {
        this.datatree.showInfix(expression);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        g.drawImage(buffer, 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Arbol de datos");
            Tools tools = new Tools(1000, 70);
            Main main = new Main();
            MenuBar menu_bar = new MenuBar();

            frame.setFont(Main.FONT_MENU);
            frame.setJMenuBar(menu_bar);

            tools.getButtonDrawTree().addActionListener((ActionEvent e) -> {
                if (!tools.getEquation().getText().equals("")) {
                    main.drawRoot(tools.getEquation().getText());
                }
            });

            tools.getButtonGetPostFix().addActionListener((ActionEvent e) -> {
                if (!tools.getEquation().getText().equals("")) {
                    main.showPostFix(tools.getEquation().getText());
                }
            });

            tools.getButtonGetInfix().addActionListener((ActionEvent e) -> {
                if (!tools.getEquation().getText().equals("")) {
                    main.showInfix(tools.getEquation().getText());
                }
            });

            frame.setMinimumSize(new Dimension(900, 600));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(main, BorderLayout.CENTER);
            frame.add(tools, BorderLayout.NORTH);
            frame.setVisible(true);
            frame.pack();
            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    main.setBuffer(frame.getWidth(), frame.getHeight());
                    main.drawRoot(tools.getEquation().getText());
                }
            });
        });
    }
}
