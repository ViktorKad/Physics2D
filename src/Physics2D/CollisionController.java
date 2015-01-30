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
        IVector2D axis;
        float[] minMax1, minMax2;

        if (p1.getRealVerticesCount() < 3 || p2.getRealVerticesCount() < 3) {
            // Данный метод не ищет пересечения с точками и отрезками.
            return false;
        }

        // Проверяем по осям проекций первого многоугольника
        for (int vertexId = 0; vertexId < p1.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(p1.getVertex(vertexId), p1.getVertex(vertexId + 1));

            minMax1 = getMinMax(p1, axis);
            minMax2 = getMinMax(p2, axis);

            if (!isOverlap(minMax1[0], minMax1[1], minMax2[0], minMax2[1])) {
                return false;
            }
        }

        // TODO: убрать дублирование

        // Проверяем по осям проекций второго многоугольника
        for (int vertexId = 0; vertexId < p2.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(p2.getVertex(vertexId), p2.getVertex(vertexId + 1));

            minMax1 = getMinMax(p1, axis);
            minMax2 = getMinMax(p2, axis);

            if (!isOverlap(minMax1[0], minMax1[1], minMax2[0], minMax2[1])) {
                return false;
            }
        }

        return true;
    }


    /**
     * Получить крайние знаяения проекции.
     * @param polygon Проецирцемая фигура.
     * @param axis Ось проекции.
     * @return Массив, первое значение в котором начало проекции, а второе её конец.
     */
    private static float[] getMinMax(Polygon polygon, IVector2D axis) {
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

        float[] result = {min, max};
        return result;
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
     * Проверяет пересечение двух проекций.
     * @param min1 Минимальная позиция первой проекции.
     * @param max1 Максимальная позиция первой проекции.
     * @param min2 Минимальная позиция второй проекции.
     * @param max2 Максимальная позиция второй проекции.
     * @return <code>true</code> в случае пересечения и <code>false</code> в обратном случае.
     */
    private static boolean isOverlap(float min1, float max1, float min2, float max2) {
        boolean result = false;

        if ((min1 >= min2 && min1 <= max2) || (max1 >= min2 && max1 <= max2)) {
            result = true;
        }

        return result;
    }

}
