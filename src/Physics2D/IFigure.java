package Physics2D;

interface IFigure {
    public void move(Vector2D v2d);

    // TODO: масштабирование производить относительно центра фигуры
    //    public void scale(float scaleTo);

    // TODO: метод rotate(angle) ?

    public boolean checkCollision(IFigure figure);
}
