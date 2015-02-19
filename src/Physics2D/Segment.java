package Physics2D;

/**
 * Класс описывающий отрезок в одномерном пространстве.
 * Отрезок имеет по одной координате начала и конца, и длину.
 */
class Segment {
    private static float DEFAULT_A = 0;
    private static float DEFAULT_B = 0;
    private float a, b;

    /**
     * Данный конструктор в поле <i>a</i> всегда добавляет меньшее
     * , а в поле <i>b</i> большее из чисел.
     * @param a
     * @param b
     */
    public Segment(float a, float b) {
        if (b > a) {
            this.a = a;
            this.b = b;
        } else {
            this.a = b;
            this.b = a;
        }
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

    public float getCenter() {
        return b - length() / 2;
    }

    public float length() {
        return b - a;
    }
}
