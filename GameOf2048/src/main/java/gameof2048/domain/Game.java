package gameof2048.domain;

public class Game {
    private Board board;
    private User user;
    private int score;
    private boolean gameOver;

    public Game(Board board, User user) {
        this.board = board;
        this.user = user;
        this.score = 0;
        this.gameOver = false;
    }

    public String toString() {
        String res = "Username: " + user.toString();
        res += "\n Score: "+ score;
        res += "\n Board: \n" + board.toString();
        return res;
    }
}
