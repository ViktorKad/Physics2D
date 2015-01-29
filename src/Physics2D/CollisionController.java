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
        //TODO: Реализовать
        return false;
    }
    
}
