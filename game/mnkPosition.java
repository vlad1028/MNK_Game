package game;

import java.util.Map;

public class mnkPosition implements Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.',
            Cell.$, '*',
            Cell.THIRD, '-',
            Cell.FORTH, '|'
    );

    private final mnkTable table;

    public mnkPosition(mnkTable table) {
        this.table = table;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < getRowsSize()
                && 0 <= move.getColumn() && move.getColumn() < getColsSize()
                && getCell(move.getRow(), move.getColumn()) == Cell.E;
    }

    @Override
    public Cell getCell(int r, int c) {
        return table.getCell(r, c);
    }

    @Override
    public int getColsSize() {
        return table.getCols();
    }

    @Override
    public int getRowsSize() {
        return table.getRows();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int r = 0; r < table.getRows(); r++) {
            sb.append(r);
        }
        for (int r = 0; r < getRowsSize(); r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < getColsSize(); c++) {
                sb.append(SYMBOLS.get(getCell(r, c)));
            }
        }
        return sb.toString();
    }
}
