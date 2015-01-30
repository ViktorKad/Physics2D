package Physics2D;

import java.util.ArrayList;

class Polygon implements IFigure {
    ArrayList<IVector2D> vertices = new ArrayList<>();
    int maxVerticesCount = 0;

    public Polygon(int maxVerticesCount) {
        this.maxVerticesCount = maxVerticesCount;
    }

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

        result = CollisionController.check((Polygon) figure, this);

        return result;
    }
}
