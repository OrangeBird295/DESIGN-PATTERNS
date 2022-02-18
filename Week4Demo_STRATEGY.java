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

    ArrayList<TextComponent> group;
    private AlignmentStrategy alignment;

    public TextComposite(AlignmentStrategy alignment) {

        group = new ArrayList<TextComponent>();
        this.alignment = alignment;
    }

    @Override
    public void print(int depth) {

        for (int i = 1; i <= depth; i++) {

            System.out.println(" ");
        }
        System.out.println(alignment.adjustAlignment() + "[" + depth + "] >> is a group");
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

}

class TextLeft implements TextComponent {

    char character;
    String colour;
    private AlignmentStrategy alignment;

    public TextLeft(AlignmentStrategy alignment) {

        colour = "BLACK";
        this.alignment = alignment;
    }

    @Override
    public void print(int depth) {

        for (int i = 1; i <= depth; i++) {
            System.out.println(" ");
        }

        System.out.println(alignment.adjustAlignment() + "[" + depth + "]" + character + ": colour is " + colour);
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

class AlignmentStrategy {

    String alignment = " ";
    String adjustAlignment() {

        return alignment;
    }
}

class LeftAlignmentStrategy extends AlignmentStrategy {

    String alignment = "<<<";

    @Override
    String adjustAlignment() {

        return alignment;
    }
}

class CenterAlignmentStrategy extends AlignmentStrategy {

    String alignment = "###";

    @Override
    String adjustAlignment() {

        return alignment;
    }
}

class RightAlignmentStrategy extends AlignmentStrategy {

    String alignment = ">>>";

    @Override
    String adjustAlignment() {

        return alignment;
    }
}

class JustifyAlignmentStrategy extends AlignmentStrategy {

    String alignment = "<#>";

    @Override
    String adjustAlignment() {

        return alignment;
    }
}

class Week4Demo_STRATEGY {

    public static void main(String[] args) {

        TextComponent aPage = new TextComposite(new LeftAlignmentStrategy());
        TextComponent lineOne = new TextComposite(new LeftAlignmentStrategy());
        TextComponent charOne = new TextComposite(new LeftAlignmentStrategy());
        charOne.setCharacter('B');
        TextComponent charTwo = new TextComposite(new RightAlignmentStrategy());
        charTwo.setCharacter('C');
        TextComponent charThree = new TextComposite(new RightAlignmentStrategy());
        charThree.setCharacter('D');

        lineOne.add(charOne);
        lineOne.add(charTwo);
        lineOne.add(charThree);

        TextComponent charFour = new TextComposite(new JustifyAlignmentStrategy());
        charFour.setCharacter('E');
        TextComponent charFive = new TextComposite(new CenterAlignmentStrategy());
        charFive.setCharacter('F');
        TextComponent groupOne = new TextComposite(new CenterAlignmentStrategy());
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