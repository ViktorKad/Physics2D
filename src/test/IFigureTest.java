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
    public void testCheckCollision() throws Exception {
        Circle circle1, circle2;
        Polygon polygon1, polygon2;
        float result;

        System.out.println(Polygon.class + " and " + Circle.class + ",  method check(Circle, Circle)");
        // Пересечение
        circle1 = FigureFactory.getCircle(2, 2, 1);
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


        System.out.println(Polygon.class + " and " + Circle.class + ",  method check(Rectangle, Rectangle)");
        // Пересечение по двум сторонам
        polygon1 = FigureFactory.getRectangle(0, 0, 2, 2);
        polygon2 = FigureFactory.getRectangle(1, 1, 2, 2);
        assertTrue(polygon1.checkCollision(polygon2));
        assertTrue(polygon2.checkCollision(polygon1));

        // Пересечение по одной стороне
        polygon1 = FigureFactory.getRectangle(0, 0, 2, 2);
        polygon2 = FigureFactory.getRectangle(-1, -1, 2, 2);
        assertTrue(polygon1.checkCollision(polygon2));
        assertTrue(polygon2.checkCollision(polygon1));

        // Отсутствие пересечения
        polygon1 = FigureFactory.getRectangle(1, 1, 10, 1);
        polygon2 = FigureFactory.getRectangle(1, 3, 10, 1);
        assertFalse(polygon1.checkCollision(polygon2));
        assertFalse(polygon2.checkCollision(polygon1));

        System.out.println(Polygon.class + " and " + Circle.class + ",  method check(Triangle, Triangle)");
        // Пересечение треугольников
        polygon1 = FigureFactory.getTriangle(1,1, 1,4, 4,1);
        polygon2 = FigureFactory.getTriangle(2,2, 2,5, 5,2);
        assertTrue(polygon1.checkCollision(polygon2));
        assertTrue(polygon2.checkCollision(polygon1));

        // Отсутствие пересечения треугольников
        polygon1 = FigureFactory.getTriangle(1,1, 1,4, 4,1);
        polygon2 = FigureFactory.getTriangle(3,3, 3,6, 6,3);
        assertFalse(polygon1.checkCollision(polygon2));
        assertFalse(polygon2.checkCollision(polygon1));

        System.out.println(Polygon.class + " and " + Circle.class + ",  method check(Rectangle, Triangle)");
        // Пересечение треугольника и прямоугольника
        polygon1 = FigureFactory.getTriangle(5,5, 5,10, 10,5);
        polygon2 = FigureFactory.getRectangle(7, 7, 8, 2);
        assertTrue(polygon1.checkCollision(polygon2));
        assertTrue(polygon2.checkCollision(polygon1));

        System.out.println(Polygon.class + " and " + Circle.class + ",  method check(Circle, Rectangle)");
        // Пересечение круга с двумя сторонами прямоугольника.
        circle1 = FigureFactory.getCircle(2, 2, 1);
        polygon1 = FigureFactory.getRectangle(0, 0, 1.7f, 1.7f);
        assertTrue(circle1.checkCollision(polygon1));
        assertTrue(polygon1.checkCollision(circle1));

        // Пересечение круга с одной из сторон треугольника.
        circle1 = FigureFactory.getCircle(4, 2, 1);
        polygon1 = FigureFactory.getTriangle(0,0, 0,3, 6,0);
        assertTrue(circle1.checkCollision(polygon1));
        assertTrue(polygon1.checkCollision(circle1));

        // Отсутствие пересечения с прямоугольником
        circle1 = FigureFactory.getCircle(2, 2, 0.9f);
        polygon1 = FigureFactory.getRectangle(0, 0, 1, 1);
        assertFalse(circle1.checkCollision(polygon1));
        assertFalse(polygon1.checkCollision(circle1));

        circle1 = FigureFactory.getCircle(8, 8, 3);
        polygon1 = FigureFactory.getRectangle(10.9f, 10.9f, 6, 6);
        assertFalse(circle1.checkCollision(polygon1));
        assertFalse(polygon1.checkCollision(circle1));

        // Отсутствие пересечения с треугольником.
        circle1 = FigureFactory.getCircle(3, 3, 1);
        polygon1 = FigureFactory.getTriangle(0,0, 0,3, 6,0);
        assertFalse(circle1.checkCollision(polygon1));
        assertFalse(polygon1.checkCollision(circle1));

        // Окружность вписанная в прямоугольник
        circle1 = FigureFactory.getCircle(5, 5, 2);
        polygon1 = FigureFactory.getRectangle(2, 2, 6, 6);
        assertTrue(circle1.checkCollision(polygon1));
        assertTrue(polygon1.checkCollision(circle1));


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


//        System.out.println("|||||||||||||||||||||||||||||||||||||||||");
//        circle1 = FigureFactory.getCircle(2, 2, 2);
//        circle2 = FigureFactory.getCircle(4, 4, 2);
//        System.out.println(circle1.getCollisionVector(circle2));
//        System.out.println(circle2.getCollisionVector(circle1));

    }
}