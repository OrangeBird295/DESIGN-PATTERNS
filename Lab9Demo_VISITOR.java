import java.util.ArrayList;

abstract class Expression {

    public abstract double eval();

    public abstract void accept(ExpressionVisitor visitor);
}

interface ExpressionVisitor {

    void visit(DoubleExpression e);

    void visit(BinaryExpression e);
}

class ExpressionCalculator implements ExpressionVisitor {

    private double result;

    @Override
    public void visit(DoubleExpression e) {

        result = e.value;
    }

    @Override
    public void visit(BinaryExpression e) {

        double left, right;
        e.left.accept(this);
        left = result;
        e.right.accept(this);
        right =result;

        switch(e.type){
            case ADDITION:
                result = left + right;
                break;
            case SUBTRACTION:
                result = left - right;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString(){
        return "" + result;    
    }

}

class ExpressionPrinter implements ExpressionVisitor {

    private StringBuilder sb = new StringBuilder();

    @Override
    public void visit(DoubleExpression e) {

        sb.append(e.value);
    }

    @Override
    public void visit(BinaryExpression e) {

        // (left+right)
        sb.append("(");

        e.left.accept(this);

        switch (e.type) {
            case ADDITION:
                sb.append("+");
                break;
            case SUBTRACTION:
                sb.append("-");
                break;
            default:
                break;
        }
        e.right.accept(this);
        sb.append(")");
    }

    @Override
    public String toString() {

        return sb.toString();
    }
}

// TERMINAL EXPRESSION
class DoubleExpression extends Expression {

    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public double eval() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {

        visitor.visit(this);
    }
}

// NON-TERMINAL EXPRESSION
class BinaryExpression extends Expression {

    public enum Type {
        ADDITION, SUBTRACTION
    }

    public Type type;

    public Expression left, right;

    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double eval() {

        switch (type) {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
            default:
                return 0;

        }
    }

    @Override
    public void accept(ExpressionVisitor visitor) {

        visitor.visit(this);
    }
}

class Token {

    public Type type;
    public String text;

    public enum Type {
        NUMBER, PLUS, MINUS, LPAREN, RPAREN
    }

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "\\" + text + "\\";
    }
}

class ArithmeticInterpreter {

    static ArrayList<Token> lex(String input) {

        ArrayList<Token> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

            switch (input.charAt(i)) {

                case '+':
                    result.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    result.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                    result.add(new Token(Token.Type.LPAREN, "("));
                    break;
                case ')':
                    result.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default:
                    if (Character.isDigit(input.charAt(i))) {

                        StringBuilder sb = new StringBuilder("" + input.charAt(i));

                        for (int j = i + 1; j < input.length(); j++) {

                            if (Character.isDigit(input.charAt(j))) {

                                sb.append(input.charAt(j));
                                i++;
                            } else
                                break;
                        }
                        result.add(new Token(Token.Type.NUMBER, sb.toString()));
                    }
                    break;
            }
        }
        return result;
    }

    static Expression parse(ArrayList<Token> tokens) {

        if (tokens.size() == 1 && tokens.get(0).type == Token.Type.NUMBER) {
            return new DoubleExpression(Double.parseDouble(tokens.get(0).text));
        }
        Expression left = null, right = null;
        BinaryExpression.Type type = null;

        Expression tempExpression = null;

        ArrayList<Token> preExpression, sufExpression;

        for (int i = tokens.size() - 1; i >= 0; i--) {

            Token token = tokens.get(i);

            switch (token.type) {

                case PLUS:
                    preExpression = new ArrayList<Token>(tokens.subList(0, i));
                    sufExpression = new ArrayList<Token>(tokens.subList(i + 1, tokens.size()));

                    left = parse(preExpression);
                    // yes : NO
                    right = tempExpression != null ? tempExpression : parse(sufExpression);

                    type = BinaryExpression.Type.ADDITION;

                    i = -1;
                    break;
                case MINUS:
                    preExpression = new ArrayList<Token>(tokens.subList(0, i));
                    sufExpression = new ArrayList<Token>(tokens.subList(i + 1, tokens.size()));

                    left = parse(preExpression);
                    right = tempExpression != null ? tempExpression : parse(sufExpression);

                    type = BinaryExpression.Type.SUBTRACTION;

                    i = -1;
                    break;
                case RPAREN:
                    ArrayList<Token> subexpression = new ArrayList<>();
                    int rParenCount = 0;

                    for (int j = i - 1; j >= 0; j--) {

                        if (tokens.get(j).type == Token.Type.RPAREN) {

                            subexpression.add(0, tokens.get(j));
                            rParenCount++;
                        } else if (tokens.get(j).type == Token.Type.LPAREN && rParenCount > 0) {

                            subexpression.add(0, tokens.get(j));
                            rParenCount--;
                        } else if (tokens.get(j).type == Token.Type.LPAREN && rParenCount == 0) {

                            i = j;
                            break;
                        } else
                            subexpression.add(0, tokens.get(j));
                    }
                    tempExpression = parse(subexpression);
                    break;
                default:
                    break;

            }
        }
        // parenthesis without operators trap-> 1+(2)
        if (tempExpression != null && type == null) {

            return tempExpression;
        }

        BinaryExpression result = new BinaryExpression(left, right);
        result.type = type;
        return result;
    }
}

class Lab9Demo_VISITOR {
    public static void main(String[] args) {

        String input1 = "1+2+3";
        String input2 = "13+4-(12+1)";

        // ======INTERPRETEA======

        // Lexing
        ArrayList<Token> tokens1 = ArithmeticInterpreter.lex(input1);

        for (Token token : tokens1) {

            System.out.print(token.toString() + "\t");
        }

        System.out.println();

        ArrayList<Token> tokens2 = ArithmeticInterpreter.lex(input2);

        for (Token token : tokens2) {

            System.out.print(token.toString() + "\t");
        }

        System.out.println();

        // parsing
        Expression parsed1 = ArithmeticInterpreter.parse(tokens1);
        System.out.println(parsed1.eval());
        Expression parsed2 = ArithmeticInterpreter.parse(tokens2);
        System.out.println(parsed2.eval());

        // ======VISITOR======
        ExpressionPrinter ep1 = new ExpressionPrinter();
        ep1.visit((BinaryExpression) parsed1);
        System.out.println(ep1);

        ExpressionPrinter ep2 = new ExpressionPrinter();
        ep2.visit((BinaryExpression) parsed2);
        System.out.println(ep2);

        ExpressionCalculator ec1 = new ExpressionCalculator();
        ec1.visit((BinaryExpression) parsed1);
        System.out.println(ec1);

        ExpressionCalculator ec2 = new ExpressionCalculator();
        ec2.visit((BinaryExpression) parsed2);
        System.out.println(ec2);
    }
}
