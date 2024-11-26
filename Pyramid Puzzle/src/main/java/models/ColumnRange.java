package models;

public class ColumnRange {
    private int min;
    private int max;

    public ColumnRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
