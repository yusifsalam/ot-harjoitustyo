package game.domain;

import java.util.*;

public class Board {
    private Random r;
    private List<Cell> cells;
    private List<Integer> vacantIndices;

    public Board(Random r) {
        this.r = r;
        this.cells = new ArrayList<>();
        this.vacantIndices = new ArrayList<>();
        spawnStartingCells();
        System.out.println(this);
    }

    public int getCellValueAt(int i) {
        return cells.get(i).getValue();
    }

    public void setCellValueAt(int i, int value) {
        cells.get(i).setValue(value);
    }

    public boolean isCellVacant(int i) {
        return getCellValueAt(i) == 0;
    }

    public void spawnStartingCells() {
        int posOne = r.nextInt(16);
        int valOne = randomizeCellValue();
        int posTwo = r.nextInt(16);
        while (posTwo == posOne) posTwo = r.nextInt(16);
        int valTwo = randomizeCellValue();
        for (int i = 0; i < 16; i++) {
            if (i == posOne) cells.add(new Cell(valOne));
            else if (i == posTwo) cells.add(new Cell(valTwo));
            else {
                cells.add(new Cell());
                this.vacantIndices.add(i);
            }
        }
    }

    public void spawnCell() {
        if (vacantIndices.isEmpty()) return;
        int pos = vacantIndices.get(r.nextInt(vacantIndices.size()));
        cells.get(pos).setValue(randomizeCellValue());
    }

    public void updateVacantCells() {
        vacantIndices.clear();
        for (int i = 0; i < 16; i++) {
            if (isCellVacant(i)) vacantIndices.add(i);
        }
    }

    public int calculateScore() {
        return cells.stream().mapToInt(c -> c.getValue()).sum();
    }

    public boolean boardIsFull() {
        return cells.stream().allMatch(c -> c.getValue() > 0);
    }

    public int randomizeCellValue() {
        return r.nextDouble() >= 0.9 ? 4 : 2;
    }

    public boolean canMoveUp() {
        updateVacantCells();
        for (int i = 4; i < 16; i++) {
            if (getCellValueAt(i - 4) == getCellValueAt(i) || isCellVacant(i - 4)) return true;
        }
        return false;
    }

    public boolean canMoveDown() {
        updateVacantCells();
        for (int i = 0; i < 12; i++) {
            if (getCellValueAt(i) == getCellValueAt(i + 4) || isCellVacant(i + 4)) return true;
        }
        return false;
    }

    public boolean canMoveRight() {
        updateVacantCells();
        for (int i = 0; i < 16; i++) {
            if (i % 3 != 0) {
                if (getCellValueAt(i) == getCellValueAt(i + 1) || isCellVacant(i + 1)) return true;
            }
        }
        return false;
    }

    public boolean canMoveLeft() {
        updateVacantCells();
        for (int i = 1; i < 16; i++) {
            if (i % 4 != 0) {
                if (getCellValueAt(i - 1) == getCellValueAt(i) || isCellVacant(i - 1)) return true;
            }
        }
        return false;
    }

    public boolean canMove() {
        return (canMoveDown() || canMoveUp() || canMoveLeft() || canMoveRight());
    }

    public void moveUp() {
        if (canMoveUp()) {
            for (int i = 4; i < 16; i++) {
                if (!isCellVacant(i) && getCellValueAt(i - 4) == getCellValueAt(i)) {
                    cells.get(i - 4).setValue(getCellValueAt(i) + getCellValueAt(i - 4));
                    cells.get(i).setValue(0);
                } else if (isCellVacant(i - 4)) {
                    cells.get(i - 4).setValue(getCellValueAt(i) + getCellValueAt(i - 4));
                    cells.get(i).setValue(0);
                }

            }
        }
    }

    public void moveDown() {
        if (canMoveDown()) {
            for (int i = 11; i >= 0; i--) {
                if ((!isCellVacant(i) && getCellValueAt(i) == getCellValueAt(i + 4)) || isCellVacant(i + 4)) {
                    cells.get(i + 4).setValue(getCellValueAt(i) + getCellValueAt(i + 4));
                    cells.get(i).setValue(0);
                }
            }
        }
    }

    public void moveRight() {
        int[] order = {2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12};
        if (canMoveRight()) {
            for (int i: order) {
                if ((!isCellVacant(i) && getCellValueAt(i) == getCellValueAt(i + 1)) || isCellVacant(i + 1)) {
                    cells.get(i + 1).setValue(getCellValueAt(i) + getCellValueAt(i + 1));
                    cells.get(i).setValue(0);
                }
            }
        }
    }

    public void moveLeft() {
        int[] order = {1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15};
        if (canMoveLeft()) {
            for (int i : order) {
                if ((!isCellVacant(i) && getCellValueAt(i - 1) == getCellValueAt(i)) || isCellVacant(i - 1)) {
                    cells.get(i - 1).setValue(getCellValueAt(i - 1) + getCellValueAt(i));
                    cells.get(i).setValue(0);
                }
            }
        }
    }

    public List<Cell> getCells() {
        return cells;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            res += cell.toString() + "\t";
            if ((i + 1) % 4 == 0) {
                res += "\n";
            }
        }
        return res;
    }
}
