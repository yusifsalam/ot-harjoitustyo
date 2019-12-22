package game.domain;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class is used to handle the game.
 */
public class Game {
    private Board board;
    private User user;
    private Scanner input;
    private int score;
    private boolean gameOver;

    public int getScore() {
        return score;
    }

    /**
     * checks if the game has ended
     * @return True or False
     */
    public boolean isGameOver() {
        return gameOver;
    }


    public Game(Board board, User user, Scanner input) {
        this.board = board;
        this.user = user;
        this.input = input;
        this.score = board.calculateScore();
        this.gameOver = false;
    }

    /**
     * This is a helper method for updating game status between turns
     * @return True if next move is possible, False if game is over.
     */
    public boolean nextMove() {
        if (gameWon() || !board.canMove()) {
            gameOver = true;
            return false;
        }

        board.spawnCell();
        score = board.calculateScore();
        gameWon();
        System.out.println(this);
        return true;
    }

    /**
     * checks if game has been won (the board has a cell whose value is 2048)
     * @return True or False
     */
    public boolean gameWon() {
        int maxVal  = board.getCells().stream().mapToInt(c -> c.getValue()).max().
                orElseThrow(NoSuchElementException::new);
        return maxVal >= 2048;
    }

    private void checkIfGameEnded() {
        if (!board.canMove()) {
            gameOver = true;
        }
    }

    public void moveBoardUp() {
        board.moveUp();
        checkIfGameEnded();
    }

    public void moveBoardDown() {
        board.moveDown();
        checkIfGameEnded();
    }

    public void moveBoardLeft() {
        board.moveLeft();
        checkIfGameEnded();
    }

    public void moveBoardRight() {
        board.moveRight();
        checkIfGameEnded();
    }

    public String toString() {
        String res = "Username: " + user.toString();
        res += "\n Score: " + score;
        res += "\n Board: \n" + board.toString();
        return res;
    }

    public Board getBoard() {
        return board;
    }

    public User getUser() {
        return user;
    }
}
