package Physics2D;

abstract class CollisionController {
    public static IVector2D tmp = new Vector2D();

    public static boolean check(Circle c1, Circle c2) {
        float distance = c1.getCenter().distanceSquared(c2.getCenter());

        return distance <= (c1.getR() + c2.getR()) * (c1.getR() + c2.getR());
    }

    public static boolean check(Polygon plg, Circle crl) {
        IVector2D axis, nearestVertex;
        float lastDistance;
        Segment projection1, projection2;


        // Ближайшая к центру окружности вершина многоугольника
        nearestVertex = plg.getVertex(3);
        // Расстояние до последней найденой ближайшей вершины.
        lastDistance = crl.getCenter().distanceSquared(nearestVertex);

        // Проход по осям найденным от сторон прямоугольника
        for (int vertexId = 0; vertexId < plg.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(plg.getVertex(vertexId), plg.getVertex(vertexId + 1));

            projection1 = getProjection(plg, axis);
            projection2 = getProjection(crl, axis);

            if (!isOverlap(projection1, projection2)) {
                return false;
            }

            // Поиск вершины, ближайшей к центру окружности
            if (crl.getCenter().distanceSquared(plg.getVertex(vertexId)) < lastDistance) {
                nearestVertex = plg.getVertex(vertexId);
                lastDistance = crl.getCenter().distanceSquared(nearestVertex);
            }
            // /Поиск вершины, ближайшей к центру окружности
        }

        // Проверка проекций на ось проходящую через центр окружности и ближайшую к ней вершину прямоугольника
        axis = new Vector2D();
        axis.setX(crl.getCenter().getX() - nearestVertex.getX());
        axis.setY(crl.getCenter().getY() - nearestVertex.getY());
        axis = axis.getNorm();

        projection1 = getProjection(plg, axis);
        projection2 = getProjection(crl, axis);

        if (!isOverlap(projection1, projection2)) {
            return false;
        }

        return true;
    }

    public static boolean check(Polygon p1, Polygon p2) {
        if (p1.getRealVerticesCount() < 3 || p2.getRealVerticesCount() < 3) {
            // Данный метод не ищет пересечения с точками и отрезками.
            return false;
        }

        // Возвращаем результат, как проверку пересечения первого многоугольника со вторым
        // и второго с первым. Ваш КЭП.
        return checkProjections(p1, p1, p2) && checkProjections(p2, p1, p2);
    }

    public static IVector2D getCollisionVector(Circle c1, Circle c2) {
        // TODO: Сделать более быструю реализацию
        float len = c1.getCenter().distanceSquared(c2.getCenter());
        len = (c1.getR() + c2.getR()) - (float) Math.sqrt(len);
        IVector2D result = new Vector2D();

        result.setFromVector(c1.getCenter());
        result.sub(c2.getCenter());
        result = result.getNorm().mul(len);

        // TODO: Возвращать null, если не пересекаются

        return result;
    }

    public static IVector2D getCollisionVector(Polygon plg, Circle crl) {
        IVector2D minOverlapAxis = null;

        IVector2D axis, nearestVertex;
        float lastDistance;
        float minOverlap = 0;
        float tmpOverlap;
        Segment projection1, projection2;


        // Ближайшая к центру окружности вершина многоугольника
        nearestVertex = plg.getVertex(3);
        // Расстояние до последней найденой ближайшей вершины.
        lastDistance = crl.getCenter().distanceSquared(nearestVertex);

        // Проход по осям найденным от сторон прямоугольника
        for (int vertexId = 0; vertexId < plg.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(plg.getVertex(vertexId), plg.getVertex(vertexId + 1));

            projection1 = getProjection(plg, axis);
            projection2 = getProjection(crl, axis);

            tmpOverlap = overlapLength(projection1, projection2);
            if (vertexId == 0 || tmpOverlap < minOverlap) {
                minOverlap = tmpOverlap;
                minOverlapAxis = axis;
            }

            // Поиск вершины, ближайшей к центру окружности
            if (crl.getCenter().distanceSquared(plg.getVertex(vertexId)) < lastDistance) {
                nearestVertex = plg.getVertex(vertexId);
                lastDistance = crl.getCenter().distanceSquared(nearestVertex);
            }
            // /Поиск вершины, ближайшей к центру окружности
        }

        // Проверка проекций на ось проходящую через центр окружности и ближайшую к ней вершину прямоугольника
        axis = new Vector2D();
        axis.setX(crl.getCenter().getX() - nearestVertex.getX());
        axis.setY(crl.getCenter().getY() - nearestVertex.getY());
        axis = axis.getNorm();

        projection1 = getProjection(plg, axis);
        projection2 = getProjection(crl, axis);

        tmpOverlap = overlapLength(projection1, projection2);
        if (tmpOverlap < minOverlap) {
            minOverlap = tmpOverlap;
            minOverlapAxis = axis;
        }

        minOverlapAxis.mul(minOverlap);
        return minOverlapAxis;
    }

    public static IVector2D getCollisionVector(Polygon p1, Polygon p2) {
        IVector2D minOverlapAxis = null;
        float minOverlap = 0;
        float tmpOverlap;

        IVector2D axis;
        Segment projection1, projection2;

        for (int vertexId = 0; vertexId < p1.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(p1.getVertex(vertexId), p1.getVertex(vertexId + 1));

            projection1 = getProjection(p1, axis);
            projection2 = getProjection(p2, axis);

            tmpOverlap = overlapLength(projection1, projection2);
            if (vertexId == 0 || tmpOverlap < minOverlap) {
                minOverlap = tmpOverlap;
                minOverlapAxis = axis;
            }
        }

        minOverlapAxis.mul(minOverlap);
        return minOverlapAxis;
    }


    /**
     * Проверяет пересечение многоугольников по осям проекций.
     * @param axisSource Многоугольник по которому ищутся оси проекций.
     * @param p1 Первый проецируемый многоугольник.
     * @param p2 Второй проецируемый многоугольник.
     * @return Возвращает true в случае пересечения многоугольников по всем осям
     * и false в случае, если хотя бы по одной оси они не пересекаются.
     */
    private static boolean checkProjections(Polygon axisSource, Polygon p1, Polygon p2) {
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
     * Получить проекцию многоугольника на ось.
     * @param polygon Проецирцемый многоугольник.
     * @param axis Ось проекции.
     * @return Отрезок, являющийся проекцией многоугольника на ось.
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
     * Получить проекцию окружности на ось.
     * @param circle Проецирцемая окружность.
     * @param axis Ось проекции.
     * @return Отрезок, являющийся проекцией окружности на ось.
     */
    private static Segment getProjection(Circle circle, IVector2D axis) {
        float min, max, tmp;

        tmp = getPointProjection(circle.getCenter(), axis);
        min = tmp - circle.getR();
        max = tmp + circle.getR();

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
     * Возвращает длину пересечения отрезков.
     * В случае отсутствия пересечения возвращает отрицательное число,
     * равное по модулю расстоянию между отрезками.
     * @param s1 Первый отрезок.
     * @param s2 Второй отрезок.
     * @return Длина пересечения отрезков.
     */
    private static float overlapLength(Segment s1, Segment s2) {
        float cen1, cen2, overlapLen;

        cen1 = s1.getCenter();
        cen2 = s2.getCenter();

        overlapLen = (s1.length() / 2 + s2.length() / 2);

        if (cen2 > cen1) {
            overlapLen -= cen2 - cen1;
        } else {
            overlapLen -= cen1 - cen2;
        }

        return overlapLen;
    }

    /**
     * Проверяет пересечение двух отрезков.
     * @param s1 Первый отрезок.
     * @param s2 Второй отрезок.
     * @return <code>true</code> в случае пересечения и <code>false</code> в обратном случае.
     */
    private static boolean isOverlap(Segment s1, Segment s2) {
        return overlapLength(s1, s2) >= 0;
    }

}
