package models;

public class PyramidCell {

    private final int row;
    private final int col;
    private final String originalValue;
    private final Long actualValue;

    public PyramidCell(String val, int row, int col) {
        this.row = row;
        this.col = col;
        this.originalValue = val;
        this.actualValue = Long.parseLong(val, 16);
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getOriginalValue() { return originalValue; }
    public Long getActualValue() { return actualValue; }

    public String toString() {
        return "(" + row + "," + col + "," + originalValue + ", " + actualValue + ")";
    }

}
