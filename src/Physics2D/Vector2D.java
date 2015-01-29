package Physics2D;

public class Vector2D implements IVector2D {
    private float x = DEFAULT_X;
    private float y = DEFAULT_Y;

    /**
     * Создание вектора с значениями <i>x</i> и <i>y</i> по умолчанию.
     *
     * @see IVector2D#DEFAULT_X
     * @see IVector2D#DEFAULT_Y
     */
    public Vector2D() {
        this.x = DEFAULT_X;
        this.y = DEFAULT_Y;
    }
    /**
     * Создание нового вектора по переданным длинам <i>x</i> и <i>y</i>.
     *
     * @param x Длина вектора по оси x
     * @param y Длина вектора по оси y
     */
    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Создание вектора, как копии переданного вектора.
     *
     * @param v Вектор образец.
     */
    public Vector2D(IVector2D v) {
        this.setFromVector(v);
    }

    @Override
    public IVector2D copy() {
        return new Vector2D(x, y);
    }

    @Override
    public IVector2D add(float dX, float dY) {
        x += dX;
        y += dY;

        return this;
    }

    @Override
    public IVector2D add(IVector2D v2d) {
        x += v2d.getX();
        y += v2d.getY();

        return this;
    }

    @Override
    public IVector2D sub(float dX, float dY) {
        x -= dX;
        y -= dY;

        return this;
    }

    @Override
    public IVector2D sub(IVector2D v2d) {
        x -= v2d.getX();
        y -= v2d.getY();

        return this;
    }

    @Override
    public IVector2D mul(float scalar) {
        x *= scalar;
        y *= scalar;

        return this;
    }

    @Override
    public float lengthPower2() {
        return (x * x + y * y);
    }

    @Override
    public float length() {
        return (float) Math.sqrt(lengthPower2());
    }

    @Override
    public IVector2D getNorm() {
        IVector2D norm = this.copy();
        float normLen = norm.length();

        if (normLen != 0) {
            norm.setX(norm.getX() / normLen);
            norm.setY(norm.getY() / normLen);
        }

        return norm;
    }

    @Override
    public float getAngle() {
        double angle = Math.atan2(y, x);

        if (angle < 0) {
            angle += 2 * Math.PI;
        }

        return (float) angle;
    }

    @Override
    public IVector2D rotate(double angle) {
        double newX, newY, sin, cos;

        sin = StrictMath.sin(angle);
        cos = StrictMath.cos(angle);

        newX = (this.x * cos) - (this.y * sin);
        newY = (this.x * sin) + (this.y * cos);

        this.x = (float) newX;
        this.y = (float) newY;

        return this;
    }

    @Override
    public float distanceSquared(IVector2D v2d) {
        return distanceSquared(v2d.getX(), v2d.getY());
    }

    @Override
    public float distanceSquared(float x, float y) {
        float dX = this.x - x;
        float dY = this.y - y;

        return (dX * dX) + (dY * dY);
    }

    @Override
    public void setFromVector(IVector2D v) {
        x = v.getX();
        y = v.getY();
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void set(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Float.compare(vector2D.x, x) != 0) return false;
        if (Float.compare(vector2D.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Vector: {x: ");
        str.append(x);
        str.append(", y: ");
        str.append(y);
        str.append(", length: ");
        str.append(length());
        str.append(", angle: ");
        str.append(getAngle());
        str.append("}");

        return str.toString();
    }
}
