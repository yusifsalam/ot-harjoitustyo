package game.domain;

import java.util.Random;

public class Board {
    private Random r;
    private Cell[] cells;

    public Board(Random r) {
        this.r = r;
        this.cells = new Cell[16];
        for (int i = 0; i < 16; i++) {
            this.cells[i] = new Cell();
        }
        this.cells[15].setValue(2);
    }

    public int getCellValueAt(int x, int y) {
        int i = x * y - 1;
        return cells[i].getValue();
    }

    public int getCellValueAt(int i) {
        return cells[i].getValue();
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
            res += cell.toString() + "\t";
            if ((i + 1) % 4 == 0) {
                res += "\n";
            }
        }
        return res;
    }
}
