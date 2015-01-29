package Physics2D;

abstract class CollisionController {
    public static IVector2D tmp = new Vector2D();

    public static boolean check(Circle c1, Circle c2) {
        float distance = c1.getCenter().distanceSquared(c2.getCenter());

        return distance <= (c1.getR() + c2.getR()) * (c1.getR() + c2.getR());
    }

    public static boolean check(Rectangle r1, Rectangle r2) {
        float r1x = r1.getLeftLowerCorner().getX();
        float r1y = r1.getLeftLowerCorner().getY();
        float r2x = r2.getLeftLowerCorner().getX();
        float r2y = r2.getLeftLowerCorner().getY();

        boolean result = false;

        if (
                r1x < r2x + r2.getW()
                && r1x + r1.getW() > r2x
                && r1y < r2y + r2.getH()
                && r1y + r1.getH() > r2y
                ) {
            result = true;
        }

        return result;
    }

    public static boolean check(Rectangle r, Circle c) {

        float rextClosestX = c.getCenter().getX();
        float rextClosestY = c.getCenter().getY();

        if (c.getCenter().getX() < r.getLeftLowerCorner().getX()) {
            rextClosestX = r.getLeftLowerCorner().getX();
        } else if (c.getCenter().getX() > r.getLeftLowerCorner().getX() + r.getW()) {
            rextClosestX = r.getLeftLowerCorner().getX() + r.getW();
        }

        if (c.getCenter().getY() < r.getLeftLowerCorner().getY()) {
            rextClosestY = r.getLeftLowerCorner().getY();
        } else if (c.getCenter().getY() > r.getLeftLowerCorner().getY() + r.getH()) {
            rextClosestY = r.getLeftLowerCorner().getY() + r.getH();
        }

        return c.getCenter().distanceSquared(rextClosestX, rextClosestY) < c.getR() * c.getR();
    }

    public static boolean check(Triangle t1, Triangle t2) {
        //TODO: Реализовать
        return false;
    }

    public static boolean check(Triangle t, Circle c) {
        //TODO: Реализовать
        return false;
    }

    public static boolean check(Triangle t, Rectangle r) {
        //TODO: Реализовать
        return false;
    }

    public static boolean check(Polygon p1, Polygon p2) {
        IVector2D axis, vertex;
        float min1, max1, min2, max2, tmp;

        if (p1.getRealVerticesCount() < 3 || p2.getRealVerticesCount() < 3) {
            return false;
        }


        // TODO: Подумать и убрать дублирование кода
        // Может быть поместить все вершины в один массив и по нему проходить?

        // Проверяем по осям проекций первого многоугольника
        for (int vertexId = 0; vertexId < p1.getRealVerticesCount(); vertexId++) {
            // TODO: % p1.getRealVerticesCount() убери это. подумай как правильно сделать. Может стоит убрать это в получение вершины
            axis = getProjectionAxis(p1.getVertex(vertexId), p1.getVertex((vertexId + 1) % p1.getRealVerticesCount()));

            min1 = getPointProjection(p1.getVertex(0), axis);
            max1 = min1;
            // Нахождение проекции первого многоугольника
            for (int i = 1; i < p1.getRealVerticesCount(); i++) {
                tmp = getPointProjection(p1.getVertex(i), axis);
                if (min1 > tmp) {
                    min1 = tmp;
                }
                if (max1 < tmp) {
                    max1 = tmp;
                }
            }

            min2 = getPointProjection(p2.getVertex(0), axis);
            max2 = min2;
            // Нахождение проекции второго многоугольника
            for (int i = 1; i < p2.getRealVerticesCount(); i++) {
                tmp = getPointProjection(p2.getVertex(i), axis);
                if (min2 > tmp) {
                    min2 = tmp;
                }
                if (max2 < tmp) {
                    max2 = tmp;
                }
            }

            if (!isOverlap(min1, max1, min2, max2)) {
                return false;
            }

        }

        // TODO: Убрать дублирование кода

        // Проверяем по осям проекций второго многоугольника
        for (int vertexId = 0; vertexId < p2.getRealVerticesCount(); vertexId++) {
            axis = getProjectionAxis(p2.getVertex(vertexId), p2.getVertex((vertexId + 1) % p2.getRealVerticesCount()));

            // Нахождение проекции первого многоугольника
            min1 = getPointProjection(p1.getVertex(0), axis);
            max1 = min1;
            for (int i = 1; i < p1.getRealVerticesCount(); i++) {
                tmp = getPointProjection(p1.getVertex(i), axis);
                if (min1 > tmp) {
                    min1 = tmp;
                }
                if (max1 < tmp) {
                    max1 = tmp;
                }
            }

            // Нахождение проекции второго многоугольника
            min2 = getPointProjection(p2.getVertex(0), axis);
            max2 = min2;
            for (int i = 1; i < p2.getRealVerticesCount(); i++) {
                tmp = getPointProjection(p2.getVertex(i), axis);
                if (min2 > tmp) {
                    min2 = tmp;
                }
                if (max2 < tmp) {
                    max2 = tmp;
                }
            }

            if (!isOverlap(min1, max1, min2, max2)) {
                return false;
            }

        }

        return true;
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
