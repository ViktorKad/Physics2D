package Physics2D;

public interface IFigure {
    public void move(IVector2D v2d);

    // TODO: масштабирование производить относительно центра фигуры
    //    public void scale(float scaleTo);

    // TODO: метод rotate(angle) ?

    public boolean checkCollision(IFigure figure);

    public IVector2D getCollisionVector(IFigure figure);
}
