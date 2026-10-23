
package com.devdyna.cakesticklib.api.primitive;

/**
 * 
 * Simple ticking process
 */
public class Ticker {

    protected int value;
    protected int max;
    protected int min;
    protected boolean flag = false;

    /**
     * @param min = 0
     */
    public Ticker(int max) {
        this(0, max);
    }

    public Ticker(int min, int max) {
        if (max <= min)
            throw new IllegalArgumentException("Ticker require max > min not the inverse!");

        this.min = min;
        this.max = max;
        this.value = min;
    }

    public static Ticker of(int max) {
        return new Ticker(max);
    }

    public static Ticker of(int min, int max) {
        return new Ticker(min, max);
    }

    public void invert() {
        setFlag(!flag);
    }

    public void setFlag(boolean inverted) {

        this.flag = inverted;

        if (inverted && value == min)
            value = max;
        else if (!inverted && value == max)
            value = min;

    }

    public boolean isInverted() {
        return flag;
    }

    public void setValue(int value) {
        this.value = limit(value);
    }

    public void setMin(int min) {
        if (min >= max)
            throw new IllegalArgumentException("Ticker require max > min not the inverse!");

        this.min = min;
        this.value = limit(value);
    }

    public void setMax(int max) {
        if (max <= min)
            throw new IllegalArgumentException("Ticker require max > min not the inverse!");

        this.max = max;
        this.value = limit(value);
    }

    private int limit(int value) {
        return Math.max(min, Math.min(max, value));
    }

    public int value() {
        return value;
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int diff() {
        return flag ? value - min : max - value;
    }

    /**
     * 0.0f -> 1.0f
     */
    public float percent() {
        return flag
                ? (max - (float) value) / ((float) max - min)
                : (value - (float) min) / ((float) max - min);
    }

    public boolean commit() {
        if (!flag) {
            if (value >= max) {
                value = min;
                return true;
            }

            value++;
            return false;
        }

        if (value <= min) {
            value = max;
            return true;
        }

        value--;
        return false;
    }

    public boolean every(int interval) {
        return interval > 0 && value % interval == 0;
    }

    public void reset() {
        value = flag ? max : min;
    }

    @Deprecated
    public void cancel() {
        reset();
    }

    @Deprecated
    public void changeDelay(int max) {
        setMax(max);
    }

    @Deprecated
    public int get() {
        return value();
    }
}
