package Physics2D;

class Rectangle implements IFigure {
    private IVector2D leftLowerCorner = new Vector2D();
    private float w = 0;
    private float h = 0;

    public Rectangle(float x, float y, float w, float h) {
        this.leftLowerCorner.set(x, y);
        this.w = w;
        this.h = h;
    }

    @Override
    public void move(Vector2D v2d) {
        leftLowerCorner.add(v2d);
    }

    @Override
    public boolean checkCollision(IFigure figure) {
        boolean result = false;

        if (figure instanceof Circle) {
            result = CollisionController.check(this, (Circle) figure);
        } else if (figure instanceof Rectangle) {
            result = CollisionController.check((Rectangle) figure, this);
        } else if (figure instanceof Triangle) {
            result = CollisionController.check((Triangle) figure, this);
        }

        return result;
    }

    public IVector2D getLeftLowerCorner() {
        return leftLowerCorner;
    }

    public float getW() {
        return w;
    }

    public float getH() {
        return h;
    }
}
