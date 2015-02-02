package Physics2D;

/**
 * Фабрика геометрических фигур.
 */
public abstract class FigureFactory implements IFigure {

    /**
     * Получить окружность. Используется как ограничивающая фигура.
     *
     * @param x Координата <i>x</i> центра окружности.
     * @param y Координата <i>y</i> центра окружности.
     * @param r Радиус окружности.
     * @return Окружность
     */
    public static Circle getCircle(float x, float y, float r) {
        return new Circle(x, y, r);
    }

    /**
     * Получить прямоугольник. Используется как ограничивающая фигура.
     *
     * @param x Координата <i>x</i> левого верхнего угла прямоугольника.
     * @param y Координата <i>y</i> левого верхнего угла прямоугольника.
     * @param w Ширина прямоугольника.
     * @param h Высота прямоугольника.
     * @return Прямоугольник
     */
    public static Polygon getRectangle(float x, float y, float w, float h) {
        Polygon rect = new Polygon(4);
        rect.addVertex(new Vector2D(x, y));
        rect.addVertex(new Vector2D(x, y + h));
        rect.addVertex(new Vector2D(x + w, y + h));
        rect.addVertex(new Vector2D(x + w, y));

        return rect;
    }

    /**
     * Получить треугольник. Используется как ограничивающая фигура.
     * В качестве параметров выступают координаты вершин треугольника.
     * @return Треугольник
     */
    public static Polygon getTriangle(float x1, float y1, float x2, float y2, float x3, float y3) {
        Polygon triangle = new Polygon(3);
        triangle.addVertex(new Vector2D(x1, y1));
        triangle.addVertex(new Vector2D(x2, y2));
        triangle.addVertex(new Vector2D(x3, y3));

        return triangle;
    }

    /**
     * Получить многоугольник с необходимым количеством вершин. Используется как ограничивающая фигура.
     * Для дополнительной информации по работе с многоугольниками, в т.ч. добавлении вершин
     * см. {@link Polygon}
     * @param maxVerticesCount Необходимое количество вершин.
     * @return Многоугольник
     * @see Polygon
     */
    public static Polygon getpolygon(int maxVerticesCount) {
        return new Polygon(maxVerticesCount);
    }
}
