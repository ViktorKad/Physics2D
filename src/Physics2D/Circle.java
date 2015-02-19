package Physics2D;

public class Circle implements IFigure {
    private IVector2D center = new Vector2D();
    private float r = 0;
    private float d = 0;

    public Circle(float x, float y, float r) {
        this.center.set(x, y);
        this.r = r;
        this.d = 2 * r;
    }

    @Override
    public void move(IVector2D v2d) {
        center.add(v2d);
    }

    @Override
    public boolean checkCollision(IFigure figure) {
        boolean result = false;

        if (figure instanceof Circle) {
            result = CollisionController.check(this, (Circle) figure);
        } else {
            result = CollisionController.check((Polygon) figure, this);
        }

        return result;
    }

    @Override
    public IVector2D getCollisionVector(IFigure figure) {
        IVector2D result = null;

        if (figure instanceof Circle) {
            result = CollisionController.getCollisionVector(this, (Circle) figure);
        } else {
            result = CollisionController.getCollisionVector((Polygon) figure, this);
        }


        return result;
    }

    public IVector2D getCenter() {
        return center;
    }

    public float getR() {
        return r;
    }

    public float getD() {
        return d;
    }
}
