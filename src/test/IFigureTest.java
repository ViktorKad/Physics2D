package test;

import Physics2D.FigureFactory;
import Physics2D.Circle;
import Physics2D.Polygon;
import org.junit.Test;

import static org.junit.Assert.*;

public class IFigureTest {

    @Test
    public void testMove() throws Exception {
        // TODO: реализовать
//        fail("IFigureTest.testMove()");
    }

    @Test
    public void testCheckCollision1() throws Exception {
        Circle circle1, circle2;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method checkCollision(Circle, Circle)");
        // Пересечение
        circle1 = FigureFactory.getCircle(2, 2, 1.001f);
        circle2 = FigureFactory.getCircle(4, 2, 1);
        assertTrue(circle1.checkCollision(circle2));
        assertTrue(circle2.checkCollision(circle1));

        // Окружность вписанная в окружность
        circle1 = FigureFactory.getCircle(5, 5, 3);
        circle2 = FigureFactory.getCircle(6, 6, 1);
        assertTrue(circle1.checkCollision(circle2));
        assertTrue(circle2.checkCollision(circle1));

        // Отсутствие пересечения
        circle1 = FigureFactory.getCircle(2, 2, 0.9f);
        circle2 = FigureFactory.getCircle(4, 2, 1);
        assertFalse(circle1.checkCollision(circle2));
        assertFalse(circle2.checkCollision(circle1));
    }

    @Test
    public void testCheckCollision2() throws Exception {
        Polygon rect1, rect2;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method checkCollision(Rectangle, Rectangle)");
        // Пересечение по двум сторонам
        rect1 = FigureFactory.getRectangle(0, 0, 2, 2);
        rect2 = FigureFactory.getRectangle(1, 1, 2, 2);
        assertTrue(rect1.checkCollision(rect2));
        assertTrue(rect2.checkCollision(rect1));

        // Пересечение по одной стороне
        rect1 = FigureFactory.getRectangle(0, 0, 2, 2);
        rect2 = FigureFactory.getRectangle(-1, -1, 2, 2);
        assertTrue(rect1.checkCollision(rect2));
        assertTrue(rect2.checkCollision(rect1));

        // Отсутствие пересечения
        rect1 = FigureFactory.getRectangle(1, 1, 10, 1);
        rect2 = FigureFactory.getRectangle(1, 3, 10, 1);
        assertFalse(rect1.checkCollision(rect2));
        assertFalse(rect2.checkCollision(rect1));
    }

    @Test
    public void testCheckCollision3() throws Exception {
        Polygon trian1, trian2;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method checkCollision(Triangle, Triangle)");
        // Пересечение треугольников
        trian1 = FigureFactory.getTriangle(1,1, 1,4, 4,1);
        trian2 = FigureFactory.getTriangle(2,2, 2,5, 5,2);
        assertTrue(trian1.checkCollision(trian2));
        assertTrue(trian2.checkCollision(trian1));

        // Отсутствие пересечения треугольников
        trian1 = FigureFactory.getTriangle(1,1, 1,4, 4,1);
        trian2 = FigureFactory.getTriangle(3,3, 3,6, 6,3);
        assertFalse(trian1.checkCollision(trian2));
        assertFalse(trian2.checkCollision(trian1));
    }

    @Test
    public void testCheckCollision4() throws Exception {
        Polygon trian, rect;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method checkCollision(Rectangle, Triangle)");
        // Пересечение треугольника и прямоугольника
        trian = FigureFactory.getTriangle(5, 5, 5, 10, 10, 5);
        rect = FigureFactory.getRectangle(7, 7, 8, 2);
        assertTrue(trian.checkCollision(rect));
        assertTrue(rect.checkCollision(trian));
    }

    @Test
    public void testCheckCollision5() throws Exception {
        Circle circle;
        Polygon polygon;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method checkCollision(Circle, Polygon)");
        // Пересечение круга с двумя сторонами прямоугольника.
        circle = FigureFactory.getCircle(2, 2, 1);
        polygon = FigureFactory.getRectangle(0, 0, 1.7f, 1.7f);
        assertTrue(circle.checkCollision(polygon));
        assertTrue(polygon.checkCollision(circle));

        // Пересечение круга с одной из сторон треугольника.
        circle = FigureFactory.getCircle(4, 2, 1);
        polygon = FigureFactory.getTriangle(0,0, 0,3, 6,0);
        assertTrue(circle.checkCollision(polygon));
        assertTrue(polygon.checkCollision(circle));

        // Отсутствие пересечения с прямоугольником
        circle = FigureFactory.getCircle(2, 2, 0.9f);
        polygon = FigureFactory.getRectangle(0, 0, 1, 1);
        assertFalse(circle.checkCollision(polygon));
        assertFalse(polygon.checkCollision(circle));

        circle = FigureFactory.getCircle(8, 8, 3);
        polygon = FigureFactory.getRectangle(10.9f, 10.9f, 6, 6);
        assertFalse(circle.checkCollision(polygon));
        assertFalse(polygon.checkCollision(circle));

        // Отсутствие пересечения с треугольником.
        circle = FigureFactory.getCircle(3, 3, 1);
        polygon = FigureFactory.getTriangle(0,0, 0,3, 6,0);
        assertFalse(circle.checkCollision(polygon));
        assertFalse(polygon.checkCollision(circle));

        // Окружность вписанная в прямоугольник
        circle = FigureFactory.getCircle(5, 5, 2);
        polygon = FigureFactory.getRectangle(2, 2, 6, 6);
        assertTrue(circle.checkCollision(polygon));
        assertTrue(polygon.checkCollision(circle));
    }

    @Test
    public void getCollisionVector1() throws Exception {
        Circle circle1, circle2;
        float result;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method getCollisionVector(Circle, Circle)");
        circle1 = FigureFactory.getCircle(5, 5, 2);
        circle2 = FigureFactory.getCircle(5, 5, 2);
        result = 0;
        assertTrue(circle1.getCollisionVector(circle2).length() == result);
        assertTrue(circle2.getCollisionVector(circle1).length() == result);

        circle1 = FigureFactory.getCircle(5, 5, 2);
        circle2 = FigureFactory.getCircle(7, 5, 2);
        result = 2;
        assertTrue(circle1.getCollisionVector(circle2).length() == result);
        assertTrue(circle2.getCollisionVector(circle1).length() == result);
        result = (float) Math.PI;
        assertTrue(circle1.getCollisionVector(circle2).getAngle() == result);
        result = 0;
        assertTrue(circle2.getCollisionVector(circle1).getAngle() == result);

        circle1 = FigureFactory.getCircle(5, 5, 2);
        circle2 = FigureFactory.getCircle(9.1f, 5, 2);
        assertNull(circle1.getCollisionVector(circle2));
        assertNull(circle2.getCollisionVector(circle1));
    }

    @Test
    public void getCollisionVector() throws Exception {
        Circle circle1, circle2;
        Polygon polygon1, polygon2;
        float result;














    }
}