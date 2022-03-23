import java.util.Stack;

interface RendererBridge {
    void renderCircle(float radiusX, float radiusY);

    void renderRect(float sideX, float sideY);
}

class VectorRendererBridge implements RendererBridge {
    @Override
    public void renderCircle(float radiusX, float radiusY) {

        System.out.println("Drawing a *vector* circle of radius X " + radiusX + " and radius Y " + radiusY);

    }

    @Override
    public void renderRect(float sideX, float sideY) {

        System.out.println("Drawing a *vector* rectangle of side X " + sideX + " and side Y " + sideY);

    }
}

class RasterRendererBridge implements RendererBridge {
    @Override
    public void renderCircle(float radiusX, float radiusY) {

        System.out.println("Drawing a *raster* circle of radius X " + radiusX + " and radius Y " + radiusY);

    }

    @Override
    public void renderRect(float sideX, float sideY) {

        System.out.println("Drawing a *raster* rectangle of side X " + sideX + " and side Y " + sideY);

    }
}

class CircleMemento {
    public float radiusX, radiusY;

    public CircleMemento(float radiusX, float radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }
}

abstract class Shape {
    protected RendererBridge renderer;

    public Shape(RendererBridge renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();

    public abstract void resize(float scaleX, float scaleY);
}

class Circle extends Shape {
    public float radiusX, radiusY;

    public Circle(RendererBridge renderer) {
        super(renderer);
    }

    public Circle(RendererBridge renderer, float radius) {
        super(renderer);
        this.radiusX = radius;
        this.radiusY = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(radiusX, radiusY);
    }

    @Override
    public void resize(float scaleX, float scaleY) {
        radiusX *= scaleX;
        radiusY *= scaleY;
    }

    public CircleMemento resizeMemento(float scaleX, float scaleY) {
        CircleMemento old = new CircleMemento(radiusX, radiusY);
        resize(scaleX, scaleY);
        return old;
    }

    public void restoreMemento(CircleMemento c) {
        this.radiusX = c.radiusX;
        this.radiusY = c.radiusY;
    }
}

class Rectangle extends Shape {
    public float sideX, sideY;

    public Rectangle(RendererBridge renderer) {
        super(renderer);
    }

    public Rectangle(RendererBridge renderer, float sideX, float sideY) {
        super(renderer);
        this.sideX = sideX;
        this.sideY = sideY;
    }

    @Override
    public void draw() {
        renderer.renderRect(sideX, sideY);
    }

    @Override
    public void resize(float scaleX, float scaleY) {
        sideX *= scaleX;
        sideY *= scaleY;
    }
}

interface Command {
    void call();

    void undo();
}

class ResizeShapeCommand implements Command {
    private Shape shape;

    public enum Action {
        WIDTHRESIZING, HEIGHTRESIZING, BOTHRESIZING
    }

    private Action action;
    private float scale;

    @Override
    public void call() {
        switch (action) {
            case WIDTHRESIZING:
                shape.resize(scale, 1);
                break;
            case HEIGHTRESIZING:
                shape.resize(1, scale);
                break;
            case BOTHRESIZING:
                shape.resize(scale, scale);
                break;
        }
    }

    @Override
    public void undo() {
        switch (action) {
            case WIDTHRESIZING:
                shape.resize(1 / scale, 1);
                break;
            case HEIGHTRESIZING:
                shape.resize(1, 1 / scale);
                break;
            case BOTHRESIZING:
                shape.resize(1 / scale, 1 / scale);
                break;
        }
    }

    public ResizeShapeCommand(Shape shape, Action action, float scale) {
        this.shape = shape;
        this.action = action;
        this.scale = scale;
    }
}

public class Lab7Demo_MEMENTO {
    public static void main(String[] args) {

        // ==MEMENTO==
        System.out.println("==MEMENTO==");
        Circle circle = new Circle(new RasterRendererBridge(), 5);

        CircleMemento c1 = circle.resizeMemento(10, 20);
        CircleMemento c2 = circle.resizeMemento(20, 20);
        Stack<CircleMemento> mementos = new Stack<>();
        mementos.push(c1);
        mementos.push(circle.resizeMemento(20, 20));
        mementos.push(circle.resizeMemento(30, 30));

        circle.draw();

        circle.restoreMemento(mementos.pop());
        circle.draw();
      
    }
}