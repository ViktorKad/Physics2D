package Physics2D;

import java.util.ArrayList;

/**
 * Данный класс описывает выпуклый многоугольник с произвольным количеством вершин.
 * Используется как ограничивающая фигура.
 * Для получения многоугольников лучше использовать фабрику {@link FigureFactory}
 *
 * После создания многоугольника в него необходимо добавить вершины. Вершины необходимо добавлять
 * в той последовательности, в которой идут ребра многоугольника. Т.е. необходимо выбрать исходную вершину,
 * а потом последовательно добавлять новые вершины "по или против часовой стрелке". Последней добавляемой вершиной
 * должна быть вершина, предшествующая исходной.
 *
 * Пример создания треугольника:
 * <code>
 * Polygon triangle = new Polygon(3);
 * triangle.addVertex(new Vector2D(10,5));
 * triangle.addVertex(new Vector2D(5,5));
 * triangle.addVertex(new Vector2D(10,5));
 * </code>
 */
public class Polygon implements IFigure {
    ArrayList<IVector2D> vertices = new ArrayList<>();
    int maxVerticesCount = 0;

    /**
     * {@link FigureFactory}
     * @param maxVerticesCount Необходимое количество вершин.
     * @see FigureFactory
     */
    public Polygon(int maxVerticesCount) {
        this.maxVerticesCount = maxVerticesCount;
    }

    /**
     * Добавляет следующую вершину в многоугольник.
     * Многоугольник не может содержать больше вершин, чем было задано в конструкторе.
     * @param vertex Добавляемая вершина.
     * @return Если количество уже добавленных вершин достигло максимального значения,
     * то метод возвращает false и новая вершина не добавляется.
     */
    public boolean addVertex(IVector2D vertex) {
        boolean result = false;

        if (getRealVerticesCount() < getMaxVerticesCount()) {
            vertices.add(vertex);
        }

        return result;
    }

    public int getMaxVerticesCount() {
        return maxVerticesCount;
    }

    public int getRealVerticesCount() {
        return vertices.size();
    }

    /**
     * Получить вершину по переданному индексу.
     * @param id Индекс вершины. Начинается с ноля.
     * @return Вектор характеризующий нужную вершину.
     */
    public IVector2D getVertex(int id) {
        int verticesCount = getRealVerticesCount();

        if (id >= verticesCount) {
            id %= verticesCount;
        }

        return vertices.get(id);
    }

    @Override
    public void move(Vector2D v2d) {
        for (IVector2D vertex : vertices) {
            vertex.add(v2d);
        }
    }

    @Override
    public boolean checkCollision(IFigure figure) {
        boolean result = false;

        // TODO: думай как правильно разрулить проверку с кругом

        result = CollisionController.check(this, (Polygon) figure);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Polygon{");

        result.append("vertices_count:");
        result.append(getRealVerticesCount());

        for (IVector2D vertex : vertices) {
            result.append(", (");
            result.append(vertex.getX());
            result.append(", ");
            result.append(vertex.getY());
            result.append(")");
        }

        result.append("}");


        return result.toString();
    }
}
