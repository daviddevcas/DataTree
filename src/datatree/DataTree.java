package datatree;

import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DataTree {

    private Node root;

    public DataTree() {
        this.root = null;
    }

    public DataTree(Node root) {
        this.root = root;
    }

    public boolean empty() {
        return root == null;
    }

    public void setRoot(Node node) {
        this.root = node;
    }

    public Node getRoot() {
        return root;
    }

    public void testExpression(String expression) {
        this.root = testPostFixTokens(convertInfixToPostFixTokens(correctExpression(expression)));
    }

    public void showPostFix(String expression) {
        try {
            JLabel label = new JLabel();
            label.setText(getExpressionNoToken(convertInfixToPostFixTokens(correctExpression(expression))));
            label.setFont(Main.getFontLabelPanel());
            JOptionPane.showMessageDialog(null, label, "Posfija", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showInfix(String expression) {
        try {
            JLabel label = new JLabel();
            label.setText(convertPostFixToInfixTokens(expression));
            label.setFont(Main.getFontLabelPanel());
            JOptionPane.showMessageDialog(null, label, "Infija", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Node testPostFixTokens(String postFixed) {
        try {
            Stack<Node> stack = new Stack<>();
            StringTokenizer tokenizer = new StringTokenizer(postFixed, ",");
            String tokens[] = new String[tokenizer.countTokens()];
            int i = 0;

            while (tokenizer.hasMoreTokens()) {
                tokens[i] = tokenizer.nextToken();
                i++;
            }

            for (int j = 0; j < tokens.length; j++) {
                if (!isOperator(tokens[j])) {
                    stack.push(new Node(tokens[j]));
                } else {
                    Node node = new Node(tokens[j]);
                    node.setRightNode(stack.pop());
                    node.setLeftNode(stack.pop());
                    stack.push(node);
                }
            }
            return stack.lastElement();
        } catch (Exception e) {
            System.out.println(this.toString() + ":" + e);
        }

        return null;
    }

    private String getExpressionNoToken(String postfix) {
        String notoken = "";

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (c != ',') {
                notoken += c;
            } else {
                notoken += " ";
            }
        }

        return notoken;
    }

    private String correctExpression(String expression) {
        String correct = "";

        for (int i = 0; i < expression.length() - 1; i++) {
            char cpre = expression.charAt(i);
            char cpos = expression.charAt(i + 1);

            if (!isOperator(String.valueOf(cpre)) && cpos == '(') {
                correct += cpre + "*";
            } else if (cpre == ')' && !isOperator(String.valueOf(cpos))) {
                correct += cpre + "*";
            } else if (cpre == ')' && cpos == '(') {
                correct += cpre + "*";
            } else {
                correct += cpre;
            }
        }

        if (expression.length() > 0) {
            correct += expression.charAt(expression.length() - 1);
        }

        return correct;
    }

    private String convertInfixToPostFixTokens(String infix) {
        String postfix = "", aux = "";

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (isOperator(String.valueOf(c))) {
                aux += "," + c + ",";
            } else {
                aux += c;
            }
        }

        aux += ",";

        StringTokenizer tokenizer = new StringTokenizer(aux, ",");
        String tokens[] = new String[tokenizer.countTokens()], expression[] = new String[tokenizer.countTokens()];
        int i = 0;

        while (tokenizer.hasMoreTokens()) {
            tokens[i++] = tokenizer.nextToken();
        }

        i = 0;

        Stack<String> stack = new Stack();

        for (String token : tokens) {
            if (isOperator(token)) {
                if (stack.empty()) {
                    stack.push(token);
                } else {
                    if (!String.valueOf(token).equals(")")) {
                        if (priorityExpression(token) > priorityStack(stack.lastElement())) {
                            stack.push(token);
                        } else if (priorityExpression(token) == priorityStack(stack.lastElement())) {
                            expression[i++] = stack.pop();
                            stack.push(token);
                        } else {
                            while (!stack.empty()) {
                                expression[i++] = stack.pop();
                            }
                            stack.push(token);
                        }
                    } else {
                        while (!String.valueOf(stack.lastElement()).equals("(")) {
                            expression[i++] = stack.pop();
                        }
                        stack.pop();
                    }
                }
            } else {
                expression[i++] = token;
            }
        }

        while (!stack.empty()) {
            expression[i++] = stack.pop();
        }

        for (String exp : expression) {
            if (!String.valueOf(exp).equals("null")) {
                postfix += exp + ",";
            }
        }

        return postfix;
    }

    private String convertPostFixToInfixTokens(String postfix) {
        String infix = "";

        StringTokenizer tokenizer = new StringTokenizer(postfix, ",");
        String tokens[] = new String[tokenizer.countTokens()];
        int i = 0;

        while (tokenizer.hasMoreTokens()) {
            tokens[i++] = tokenizer.nextToken();
        }

        Stack<String> stack = new Stack();

        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(token);
            } else {
                String right = stack.pop();
                String left = stack.pop();
                stack.push("(" + left + token + right + ")");
            }
        }

        for (i = 0; i < stack.lastElement().length(); i++) {
            char c = stack.lastElement().charAt(i);
            if (i != 0 && i != stack.lastElement().length() - 1) {
                infix += c;
            }
        }

        return infix;
    }

    private boolean isOperator(String c) {
        return ("+".equals(c) || "-".equals(c) || "/".equals(c) || "*".equals(c) || "(".equals(c) || ")".equals(c) || "^".equals(c));
    }

    private int priorityExpression(String c) {
        switch (c) {
            case "(":
                return 5;
            case "^":
                return 4;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    private int priorityStack(String c) {
        switch (c) {
            case "(":
                return 0;
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }
}
