package game.domain;

import java.util.*;

/**
 * This class is used for controlling the board logic
 */
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

    /**
     *
     * @param i index of the cell (0 to 15 for a 4x4 board)
     * @return numerical value of the cell
     */
    public int getCellValueAt(int i) {
        return cells.get(i).getValue();
    }

    public void setCellValueAt(int i, int value) {
        cells.get(i).setValue(value);
    }

    /**
     * Checks if the board cell is vacant (a cell is vacant if its value is 0)
     * @param i index of the cell
     * @return True or False
     */
    public boolean isCellVacant(int i) {
        return getCellValueAt(i) == 0;
    }

    /**
     * This method initializes the starting random 4x4 board
     */
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

    /**
     * spawns a new cell to a vacant position on the board
     */
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

    /**
     * calculated the score of the board - total sum of all cell values
     * @return calculated score
     */
    public int calculateScore() {
        return cells.stream().mapToInt(c -> c.getValue()).sum();
    }

    public boolean boardIsFull() {
        return cells.stream().allMatch(c -> c.getValue() > 0);
    }

    public int randomizeCellValue() {
        return r.nextDouble() >= 0.9 ? 4 : 2;
    }

    /**
     * checks if board can be moved up
     * @return True or False
     */
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

    /**
     * shifts the board cells up if possible.
     */
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
