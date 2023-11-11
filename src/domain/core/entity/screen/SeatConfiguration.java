package domain.core.entity.screen;

public class SeatConfiguration {
    private int rows;
    private int columns;

    public SeatConfiguration(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
