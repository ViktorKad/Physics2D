package test;

import Physics2D.IVector2D;
import Physics2D.Vector2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2DTest {

    @Test
    public void testCopy() throws Exception {
        System.out.println(Vector2D.class + ",  method copy()");
        float x = -11.6f;
        float y = 41.69f;
        IVector2D v1, v2;

        v1 = new Vector2D(x, y);
        v2 =  v1.copy();
        assertEquals(v1, v2);
        assertNotSame(v1, v2);

        v1 = new Vector2D();
        v2 =  v1.copy();
        assertEquals(v1, v2);
        assertNotSame(v1, v2);
    }

    @Test
    public void testAddScalar() throws Exception {
        System.out.println(Vector2D.class + ",  method add(float)");
        float x1 = 1.5f;
        float y1 = 7.744f;
        float x2 = -9;
        float y2 = 6.9f;
        IVector2D v1 = new Vector2D(x1, y1);

        assertSame(v1, v1.add(x2, y2));
        assertTrue(v1.getX() == x1 + x2);
        assertTrue(v1.getY() == y1 + y2);
    }

    @Test
    public void testAddVector() throws Exception {
        System.out.println(Vector2D.class + ",  method add(IVector2D)");
        IVector2D v1, v2, result;
        float x1 = 1.5f;
        float y1 = 7.744f;
        float x2 = -9;
        float y2 = 6.9f;

        v1 = new Vector2D(x1, y1);
        v2 = new Vector2D(x2, y2);
        result = new Vector2D(x1 + x2, y1 + y2);

        assertSame(v1, v1.add(v2));
        assertEquals(v1, result);
    }

    @Test
    public void testSubScalar() throws Exception {
        System.out.println(Vector2D.class + ",  method sub(float)");
        float x1 = -2.34f;
        float y1 = 7.744f;
        float x2 = 7;
        float y2 = 4.8f;
        IVector2D v = new Vector2D(x1, y1);

        assertSame(v, v.sub(x2, y2));
        assertTrue(v.getX() == x1 - x2);
        assertTrue(v.getY() == y1 - y2);
    }

    @Test
    public void testSubVector() throws Exception {
        System.out.println(Vector2D.class + ",  method sub(IVector2D)");
        IVector2D v1, v2, result;
        float x1 = -2.34f;
        float y1 = 7.744f;
        float x2 = 7;
        float y2 = 4.8f;

        v1 = new Vector2D(x1, y1);
        v2 = new Vector2D(x2, y2);
        result = new Vector2D(x1 - x2, y1 - y2);

        assertSame(v1, v1.sub(v2));
        assertEquals(v1, result);
    }

    @Test
    public void testMul() throws Exception {
        System.out.println(Vector2D.class + ",  method mul(float)");
        float x = -2.34f;
        float y = 7.744f;
        float scalar = 8.14f;
        IVector2D v = new Vector2D(x, y);

        assertSame(v, v.mul(scalar));
        assertTrue(v.getX() == x * scalar);
        assertTrue(v.getY() == y * scalar);
    }

    @Test
    public void testLengthPower2() throws Exception {
        System.out.println(Vector2D.class + ",  method lengthPower2()");
        float x = -2.0f;
        float y = 7f;
        float result = 53f;
        IVector2D v = new Vector2D(x, y);

        assertTrue(v.lengthPower2() == result);
    }

    @Test
    public void testLength() throws Exception {
        System.out.println(Vector2D.class + ",  method length()");
        IVector2D v;
        float x, y;
        x = 8f;
        y = 4.0f;
        float result = (float) 8.94427190999;

        v = new Vector2D(x, y);

        assertTrue(v.length() == result);
    }

    @Test
    public void testGetNorm() throws Exception {
        System.out.println(Vector2D.class + ",  method getNorm()");
        IVector2D v, vNorm;

        v = new Vector2D(4.0f, 17.2f);
        vNorm = v.getNorm();

        assertTrue(vNorm.length() > 0.999 && vNorm.length() < 1.001);
        assertTrue(v.getAngle() == vNorm.getAngle());
        assertNotSame(v, vNorm);

        v = new Vector2D(0f, 10f);
        vNorm = v.getNorm();

        assertTrue(vNorm.length() == 1);
        assertTrue(v.getAngle() == vNorm.getAngle());
        assertNotSame(v, vNorm);
        assertTrue(vNorm.getX() == 0);
        assertTrue(vNorm.getY() == 1);
    }

    @Test
    public void testGetAngle() throws Exception {
        System.out.println(Vector2D.class + ",  method getAngle()");
        IVector2D v;
        float x, y, result;

        y = x = 3f;
        result = (float) Math.PI / 4;
        v = new Vector2D(x, y);
        assertTrue(v.getAngle() == result);

        y = 3f;
        x = 0f;
        result = (float) (Math.PI / 2);
        v = new Vector2D(x, y);
        assertTrue(v.getAngle() == result);

        y = 0f;
        x = -2f;
        result = (float) Math.PI;
        v = new Vector2D(x, y);
        assertTrue(v.getAngle() == result);

        y = -4f;
        x = 0f;
        result = (float) (3 * Math.PI / 2);
        v = new Vector2D(x, y);
        assertTrue(v.getAngle() == result);
    }

    @Test
    public void testRotate() throws Exception {
        System.out.println(Vector2D.class + ",  method rotate()");
        float x, y, result;
        IVector2D v;

        x = y = 2f;
        v = new Vector2D(x, y);
        result = (float) (Math.PI / 2);

        assertSame(v.rotate(Math.PI / 4), v);
        assertTrue(v.getAngle() == result);

        x = 0;
        y = 1;
        v = new Vector2D(x, y);
        result = (float) (Math.PI);

        assertSame(v.rotate(Math.PI / 2), v);
        assertTrue(v.getAngle() == result);
        assertTrue(v.getX() == -1);
        assertTrue(Math.floor(v.getY()) == 0);
    }

    @Test
    public void testDdistanceSquared() throws Exception {
        System.out.println(Vector2D.class + ",  method distanceSquared(IVector2D)");
        IVector2D v1, v2;
        float x, y, result;

        v1 = new Vector2D(2, 2);
        v2 = new Vector2D(4, 4);
        result = 8;
        assertTrue(v1.distanceSquared(v2) == result);

        v1 = new Vector2D(2, 0);
        v2 = new Vector2D(4, 0);
        result = 4;
        assertTrue(v1.distanceSquared(v2) == result);

        v1 = new Vector2D(2, 0);
        x = 4;
        y = 0;
        result = 4;
        assertTrue(v1.distanceSquared(x, y) == result);
    }

    @Test
    public void testSetFromVector() throws Exception {
        System.out.println(Vector2D.class + ",  method setFromVector()");

    }

    @Test
    public void testGetX() throws Exception {
        System.out.println(Vector2D.class + ",  method getX()");

    }

    @Test
    public void testSetX() throws Exception {
        System.out.println(Vector2D.class + ",  method setX(float)");

    }

    @Test
    public void testGetY() throws Exception {
        System.out.println(Vector2D.class + ",  method getY()");

    }

    @Test
    public void testSetY() throws Exception {
        System.out.println(Vector2D.class + ",  method setY(float)");

    }
}