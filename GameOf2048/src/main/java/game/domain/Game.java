package game.domain;

import java.util.Scanner;

public class Game {
    private Board board;
    private User user;
    private Scanner input;

    public int getScore() {
        return score;
    }

    private int score;
    private boolean gameOver;

    public Game(Board board, User user, Scanner input) {
        this.board = board;
        this.user = user;
        this.input = input;
        this.score = board.calculateScore();
        this.gameOver = false;
    }

    public int playGame() {
        while (!this.gameOver) {
            String direction = input.nextLine();
            switch (direction) {
                case "w":
                    moveBoardUp();
                    break;
                case "a":
                    moveBoardLeft();
                    break;
                case "s":
                    moveBoardDown();
                    break;
                case "d":
                    moveBoardRight();
                    break;
                case "i quit":
                    gameOver = true;
                    break;
            }
            nextMove();
        }
        return score;
    }

    public void nextMove() {
        board.spawnCell();
        score = board.calculateScore();
        System.out.println(this);
    }

    public void moveBoardUp() {
        board.moveUp();
    }

    public void moveBoardDown() {
        board.moveDown();
    }

    public void moveBoardLeft() {
        board.moveLeft();
    }

    public void moveBoardRight() {
        board.moveRight();
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
