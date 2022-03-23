import java.util.ArrayList;

interface TextComponent {

    public void setColour(String newColour);
    public void print(int depth);
    public void add(TextComponent child);
    public TextComponent getChild(int index);
    public void setCharacter(char aChar);
    public TextComponent getCharacter();
}

class TextComposite implements TextComponent {

    char character;
    ArrayList<TextComponent> group;

    public TextComposite() {
        group = new ArrayList<TextComponent>();
    }

    @Override
    public void print(int depth) {

        for (int i = 1; i <= depth; i++) {
            System.out.println(" ");
        }
        System.out.println("[" + depth + "] >> is a group");
        for (TextComponent child : group) {
            child.print(depth + 1);
        }
    }

    @Override
    public void setColour(String newColour) {

        for (TextComponent child : group) {
            child.setColour(newColour);
        }
    }

    @Override
    public void add(TextComponent child) {

        group.add(child);
    }

    @Override
    public TextComponent getChild(int index) {

        return group.getChild(index);
    }

    @Override
    public void setCharacter(char aChar) {

        this.character = aChar;
    }

}

class TextLeft implements TextComponent {

    char character;
    String colour;

    public TextLeft() {
        colour = "BLACK";
    }

    @Override
    public void print(int depth) {

        for (int i = 1; i <= depth; i++) {
            System.out.println(" ");
        }
        
        System.out.println("[" + depth + "]" + character + ": colour is " + colour);
    }

    @Override
    public void setColour(String newColour) {

        this.colour = newColour;
    }

    @Override
    public TextComponent getChild(int index) {

        return this.character;
    }

    @Override
    public void setCharacter(char aChar) {

        this.character = aChar;
    }

}

class Week4Demo_COMPOSITE {

    public static void main(String[] args) {

        TextComponent aPage = new TextComposite();
        TextComponent lineOne = new TextComposite();
        TextComponent charOne = new TextComposite();
        charOne.setCharacter('B');
        TextComponent charTwo = new TextComposite();
        charTwo.setCharacter('C');
        TextComponent charThree = new TextComposite();
        charThree.setCharacter('D');

        lineOne.add(charOne);
        lineOne.add(charTwo);
        lineOne.add(charThree);

        TextComponent charFour = new TextComposite();
        charFour.setCharacter('E');
        TextComponent charFive = new TextComposite();
        charFive.setCharacter('F');
        TextComponent groupOne = new TextComposite();
        groupOne.add(charFour);
        groupOne.add(charFive);

        lineOne.add(groupOne);

        aPage.add(lineOne);

        aPage.print(0);
        System.out.println();

        groupOne.setColour("RED");
        aPage.print(0);
        System.out.println();

        charThree.setColour("BLUE");
        aPage.print(0);
        System.out.println();

        aPage.setColour("GREEN");
        aPage.print(0);
        System.out.println();  
    }
}