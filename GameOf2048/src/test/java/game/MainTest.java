package game;

import game.domain.Board;
import game.domain.Game;
import game.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MainTest {

    Game game;

    @Before
    public void setUp() {
        Board board = new Board(new Random());
        game = new Game(board, new User("Unknown"));
    }

    @Test
    public void gameIsGeneratedCorrectly() {
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            sum += game.getBoard().getCellValueAt(i);
        }
        assertEquals(2, game.getBoard().getCellValueAt(4,4));
        assertEquals(0, sum);
        assertEquals("Unknown", game.getUser().getUsername());
    }

    @Test
    public void userCanChangeName() {
        game.getUser().setUsername("tester");
        assertEquals("tester", game.getUser().getUsername());
    }
}