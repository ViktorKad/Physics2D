package Physics2D;

/**
 * Интерфейс описывающий геометрическую модель вектора.
 * Используется для эмуляции скорости, направления и т.д.
 */
public interface IVector2D {
    static final float DEFAULT_X = 0;
    static final float DEFAULT_Y = 0;

    /**
     * Возвращает копию данного вектора.
     *
     * @return Копия вектора (новый экземпляр соответствующего класса).
     */
    public IVector2D copy();

    /**
     * Увеличение компонент x:y данного вектора на скаляры.
     *
     * @param dX Изменение по <i>x</i>
     * @param dX Изменение по <i>y</i>
     * @return Данный вектор <code>this</code>.
     * @see IVector2D#add(IVector2D)
     */
    public IVector2D add(float dX, float dY);

    /**
     * Складывает данный вектор с другим вектором.
     *
     * @param v2d Прибавляемый вектор.
     * @return Данный вектор <code>this</code>.
     * @see IVector2D#add(float, float)
     */
    public IVector2D add(IVector2D v2d);

    /**
     * Уменьшение компонент x:y данного вектора на скаляры.
     *
     * @param dX Изменение по <i>x</i>
     * @param dY Изменение по <i>y</i>
     * @return Данный вектор <code>this</code>.
     * @see IVector2D#sub(IVector2D)
     */
    public IVector2D sub(float dX, float dY);

    /**
     * Вычитает из данного вектора другой вектор.
     *
     * @param v2d Вычитаемый вектор.
     * @return Данный вектор <code>this</code>.
     * @see IVector2D#sub(float, float)
     */
    public IVector2D sub(IVector2D v2d);

    /**
     * Умножение данного вектора на скаляр.
     *
     * @param scalar Чило, на которое производится умноженеие.
     * @return Данный вектор <code>this</code>.
     */
    public IVector2D mul(float scalar);

    /**
     * Получить квадрат длины данного вектора. Вычисляется быстрее чем просто
     * длина вектора. Этот метод удобен, например для определения столкновений.
     *
     * @return Квадрат длины данного вектора.
     * @see IVector2D#length()
     */
    public float lengthPower2();

    /**
     * Получить длину вектора. Вычисляется медленнее чем {@link IVector2D#lengthPower2()}.
     *
     * @return Длина данного вектора.
     * @see IVector2D#lengthPower2()
     */
    public float length();

    /**
     * Получить нормализованный вектор (Вектор единичной длины).
     *
     * @return Вектор единичной длины. Этот вектор является новым экземпляром {@link IVector2D}.
     */
    public IVector2D getNorm();

    /**
     * Возвращает угол в радианах между данным вектором и осью. <i>X</i>
     *
     * @return Угол в радианах между данным вектором и осью. <i>X</i>.
     */
    public float getAngle();

    /**
     * Поворот данного вектора на угол.
     *
     * @param angle Угол поворота в радианах.
     * @return Данный вектор пояле поворота <code>this</code>.
     */
    public IVector2D rotate(float angle);

    /**
     * Возвращает квадрат расстояния до переданного вектора.
     * @param v2d Вектор до которого просчитывается расстояние.
     * @return Квадрат расстояния до переданного вектора.
     *
     * @see IVector2D#distanceSquared(float, float)
     */
    public float distanceSquared(IVector2D v2d);

    /**
     * Возвращает квадрат расстояния от вектора до точки.
     * @param x Координата <i>x</i> точки до которой рассчитывается расстояние.
     * @param y Координата <i>y</i> точки до которой рассчитывается расстояние.
     * @return Квадрат расстояния до переданного вектора.
     *
     * @see IVector2D#distanceSquared(IVector2D)
     */
    public float distanceSquared(float x, float y);

    /**
     * Установить внутреннее состояние как у переданного вектора.
     *
     * @param v Вектор с необходимым состоянием
     */
    public void setFromVector(IVector2D v);

    /**
     * Получить координату <i>x</i> данного ветора.
     * @return void
     */
    public float getX();

    /**
     * Установить новое значение координаты <i>x</i> данного ветора.
     * @param x Новое значение координаты <i>x</i>
     */
    public void setX(float x);

    /**
     * Получить координату <i>y</i> данного ветора.
     * @return void
     */
    public float getY();

    /**
     * Установить новое значение координаты <i>y</i> данного ветора.
     * @param y Новое значение координаты <i>y</i>
     */
    public void setY(float y);

    /**
     * Установить новое значение координат <i>x</i>, <i>y</i> данного ветора.
     * @param x Новое значение координаты <i>x</i>
     * @param y Новое значение координаты <i>y</i>
     */
    public void set(float x, float y);

}
