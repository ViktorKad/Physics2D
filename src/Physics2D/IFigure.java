package Physics2D;

public interface IFigure {
    public void move(Vector2D v2d);

    // TODO: Подумать над реализацией метода масштабирования для треугольников
//    public void scale(float scaleTo);

    public boolean checkCollision(IFigure figure);
}
