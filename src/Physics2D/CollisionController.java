package Physics2D;

abstract class CollisionController {
    public static IVector2D tmp = new Vector2D();

    public static boolean check(Circle c1, Circle c2) {
        float distance = c1.getCenter().distanceSquared(c2.getCenter());

        return distance <= (c1.getR() + c2.getR()) * (c1.getR() + c2.getR());
    }

    public static boolean check(Polygon p1, Circle p2) {
        // TODO: реализовать
        return false;
    }

    public static boolean check(Polygon p1, Polygon p2) {
        if (p1.getRealVerticesCount() < 3 || p2.getRealVerticesCount() < 3) {
            // Данный метод не ищет пересечения с точками и отрезками.
            return false;
        }

        // Возвращаем результат, как проверку пересечения первого многоугольника со вторым
        // и второго с первым. Ваш КЭП.
        return checkPolygons(p1, p1, p2) && checkPolygons(p2, p1, p2);
    }


    /**
     * Проверяет пересечение многоугольников по осям проекций.
     * @param axisSource Многоугольник по которому ищутся оси проекций.
     * @param p1 Первый проецируемый многоугольник.
     * @param p2 Второй проецируемый многоугольник.
     * @return Возвращает true в случае пересечения многоугольников по всем осям
     * и false в случае, если хотя бы по одной оси они не пересекаются.
     */
    private static boolean checkPolygons(Polygon axisSource, Polygon p1, Polygon p2) {
        IVector2D axis;
        Segment projection1,projection2;

        for (int vertexId = 0; vertexId < axisSource.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(axisSource.getVertex(vertexId), axisSource.getVertex(vertexId + 1));

            projection1 = getProjection(p1, axis);
            projection2 = getProjection(p2, axis);

            if (!isOverlap(projection1, projection2)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Получить крайние знаяения проекции.
     * @param polygon Проецирцемая фигура.
     * @param axis Ось проекции.
     * @return Отрезок, являющийся проекцией фигуры на ось.
     */
    private static Segment getProjection(Polygon polygon, IVector2D axis) {
        float min, max, tmp;

        min = getPointProjection(polygon.getVertex(0), axis);
        max = min;

        // Нахождение проекции многоугольника (polygon) на ось (axis)
        for (int i = 1; i < polygon.getRealVerticesCount(); i++) {
            tmp = getPointProjection(polygon.getVertex(i), axis);
            if (min > tmp) {
                min = tmp;
            }
            if (max < tmp) {
                max = tmp;
            }
        }

        return new Segment(min, max);
    }

    /**
     * Получить ось проекции для стороны многоугольника.
     * @param a <i>a</i> и <i>b</i> - крайние точки стороны многоугольника.
     * @param b <i>a</i> и <i>b</i> - крайние точки стороны многоугольника.
     * @return Единичный вектор, являющийся осью проекции для переданной стороны.
     */
    private static IVector2D getProjectionAxis(IVector2D a, IVector2D b) {
        IVector2D axis = new Vector2D();

        axis.setX(b.getX() - a.getX());
        axis.setY(b.getY() - a.getY());

        axis.rotate(Math.PI / 2);

        return axis.getNorm();
    }

    /**
     * Возвращает значение проекции точки на ось.
     * @param point Точка для которой ищется проекция.
     * @param axis Ось проекции. Для корректной работы должна быть представлена единичным вектором.
     * @return Позиция точки на оси проекции.
     */
    private static float getPointProjection(IVector2D point, IVector2D axis) {
        return point.getX() * axis.getX() + point.getY() * axis.getY();
    }

    /**
     * Проверяет пересечение двух отрезков.
     * @param s1 Первый отрезок.
     * @param s2 Второй отрезок.
     * @return <code>true</code> в случае пересечения и <code>false</code> в обратном случае.
     */
    private static boolean isOverlap(Segment s1, Segment s2) {
        boolean result = false;

        if ((s1.getA() >= s2.getA() && s1.getA() <= s2.getB())
                || (s1.getB() >= s2.getA() && s1.getB() <= s2.getB())
                || (s1.getA() <= s2.getA() && s1.getB() >= s2.getB())) {
            result = true;
        }

        return result;
    }


}
