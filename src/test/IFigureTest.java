package test;

import Physics2D.FigureFactory;
import Physics2D.IFigure;
import Physics2D.Polygon;
import Physics2D.Vector2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class IFigureTest {

    @Test
    public void testMove() throws Exception {
//        fail("IFigureTest.testMove()");
    }

    @Test
    public void testCheckCollision() throws Exception {
        IFigure circle1, circle2, rect1, rect2, triangle1, triangle2;

        System.out.println(IFigure.class + ",  method check(Circle, Circle)");
        // Пересечение
        circle1 = FigureFactory.getCircle(2, 2, 1);
        circle2 = FigureFactory.getCircle(4, 2, 1);
        assertTrue(circle1.checkCollision(circle2));

        // Окружность вписанная в окружность
        circle1 = FigureFactory.getCircle(5, 5, 3);
        circle2 = FigureFactory.getCircle(6, 6, 1);
        assertTrue(circle1.checkCollision(circle2));

        // Отсутствие пересечения
        circle1 = FigureFactory.getCircle(2, 2, 0.9f);
        circle2 = FigureFactory.getCircle(4, 2, 1);
        assertFalse(circle1.checkCollision(circle2));

        System.out.println(IFigure.class + ",  method check(Rectangle, Rectangle)");
        // Пересечение по двум сторонам
        rect1 = FigureFactory.getRectangle(0, 0, 2, 2);
        rect2 = FigureFactory.getRectangle(1, 1, 2, 2);
        assertTrue(rect1.checkCollision(rect2));

        // Пересечение по одной стороне
        rect1 = FigureFactory.getRectangle(0, 0, 2, 2);
        rect2 = FigureFactory.getRectangle(-1, -1, 2, 2);
        assertTrue(rect1.checkCollision(rect2));

        // Отсутствие пересечения
        rect1 = FigureFactory.getRectangle(0, 0, 4, 8);
        rect2 = FigureFactory.getRectangle(1, 1, 1, 2);
        assertTrue(rect1.checkCollision(rect2));

        System.out.println(IFigure.class + ",  method check(Circle, Rectangle)");
        // Пересечение круга с двумя сторонами прямоугольника.
        circle1 = FigureFactory.getCircle(2, 2, 1);
        rect1 = FigureFactory.getRectangle(0, 0, 1.7f, 1.7f);
        assertTrue(circle1.checkCollision(rect1));

        // Отсутствие пересечения
        circle1 = FigureFactory.getCircle(2, 2, 1);
        rect1 = FigureFactory.getRectangle(0, 0, 1, 1);
        assertFalse(circle1.checkCollision(rect1));

        // Окружность вписанная в прямоугольник
        circle1 = FigureFactory.getCircle(5, 5, 2);
        rect1 = FigureFactory.getRectangle(2, 2, 6, 6);
        assertTrue(circle1.checkCollision(rect1));




        // TODO: Исправь всё
        System.out.println(IFigure.class + ",  method check(Polygon, Polygon)");
        Polygon p1 = new Polygon(4);
        p1.addVertex(new Vector2D(1, 1));
        p1.addVertex(new Vector2D(1, 4));
        p1.addVertex(new Vector2D(4, 4));
        p1.addVertex(new Vector2D(4, 1));

        Polygon p2 = new Polygon(4);
        p2.addVertex(new Vector2D(3, 3));
        p2.addVertex(new Vector2D(3, 6));
        p2.addVertex(new Vector2D(6, 6));
        p2.addVertex(new Vector2D(6, 3));

        assertTrue(p1.checkCollision(p2));

        //--------------
        p1 = new Polygon(4);
        p1.addVertex(new Vector2D(1, 1));
        p1.addVertex(new Vector2D(1, 4));
        p1.addVertex(new Vector2D(4, 4));
        p1.addVertex(new Vector2D(4, 1));

        p2 = new Polygon(4);
        p2.addVertex(new Vector2D(5, 5));
        p2.addVertex(new Vector2D(5, 6));
        p2.addVertex(new Vector2D(6, 6));
        p2.addVertex(new Vector2D(6, 5));

        assertFalse(p1.checkCollision(p2));

        //--------------
        p1 = new Polygon(3);
        p1.addVertex(new Vector2D(1, 1));
        p1.addVertex(new Vector2D(1, 4));
        p1.addVertex(new Vector2D(4, 1));

        p2 = new Polygon(3);
        p2.addVertex(new Vector2D(2, 2));
        p2.addVertex(new Vector2D(2, 5));
        p2.addVertex(new Vector2D(5, 2));

        assertTrue(p1.checkCollision(p2));

        //--------------
        p1 = new Polygon(3);
        p1.addVertex(new Vector2D(1, 1));
        p1.addVertex(new Vector2D(1, 4));
        p1.addVertex(new Vector2D(4, 1));

        p2 = new Polygon(3);
        p2.addVertex(new Vector2D(3, 3));
        p2.addVertex(new Vector2D(3, 6));
        p2.addVertex(new Vector2D(6, 3));
        System.out.println(p1.checkCollision(p2));

        assertFalse(p1.checkCollision(p2));

    }
}