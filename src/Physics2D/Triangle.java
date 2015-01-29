package Physics2D;

class Triangle implements IFigure {
    private IVector2D node1 = new Vector2D();
    private IVector2D node2 = new Vector2D();
    private IVector2D node3 = new Vector2D();

    public Triangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        node1.set(x1, y1);
        node2.set(x2, y2);
        node3.set(x3, y3);
    }

    @Override
    public void move(Vector2D v2d) {
        node1.add(v2d);
        node2.add(v2d);
        node3.add(v2d);
    }

    @Override
    public boolean checkCollision(IFigure figure) {
        boolean result = false;

        if (figure instanceof Circle) {
            result = CollisionController.check(this, (Circle) figure);
        } else if (figure instanceof Rectangle) {
            result = CollisionController.check(this, (Rectangle) figure);
        } else if (figure instanceof Triangle) {
            result = CollisionController.check((Triangle) figure, this);
        }

        return result;
    }
}
