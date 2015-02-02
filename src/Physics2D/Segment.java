package Physics2D;

/**
 * Класс описывающий отрезок в одномерном пространстве.
 * Отрезок имеет по одной координате начала и конца, и длину.
 */
class Segment {
    private static float DEFAULT_A = 0;
    private static float DEFAULT_B = 0;
    private float a, b;

    public Segment(float a, float b) {
        this.a = a;
        this.b = b;
    }

    public Segment() {
        this(DEFAULT_A, DEFAULT_B);
    }

    public float getA() {
        return a;
    }

    public float getB() {
        return b;
    }

    public float length() {
        return Math.abs(b - a);
    }
}
