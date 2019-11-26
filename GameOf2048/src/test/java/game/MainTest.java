package game;

import game.domain.Board;
import game.domain.Game;
import game.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainTest {

    Game game;

    @Before
    public void setUp() {
        Board board = new Board(new Random(8));
        game = new Game(board, new User("Unknown"), new Scanner(""));
    }

    @Test
    public void gameIsGeneratedCorrectly() {
        assertEquals(2, game.getBoard().getCellValueAt(0));
        assertEquals(4, game.getBoard().getCellValueAt(11));
        assertEquals("Unknown", game.getUser().getUsername());
    }

    @Test
    public void movingDownWorksCorrectly() {
        game.moveBoardDown();
        assertEquals(2, game.getBoard().getCellValueAt(4));
        assertEquals(4, game.getBoard().getCellValueAt(15));
    }

    @Test
    public void movingUpWorksCorrectly() {
        game.moveBoardUp();
        assertEquals(2, game.getBoard().getCellValueAt(0));
        assertEquals(4, game.getBoard().getCellValueAt(7));
    }


    @Test
    public void movingRightWorksCorrectly() {
        game.moveBoardRight();
        assertEquals(2, game.getBoard().getCellValueAt(1));
        assertEquals(4, game.getBoard().getCellValueAt(11));
    }


    @Test
    public void movingLeftWorksCorrectly() {
        game.moveBoardLeft();
        assertEquals(2, game.getBoard().getCellValueAt(0));
        assertEquals(4, game.getBoard().getCellValueAt(10));
    }

    @Test
    public void userCanChangeName() {
        game.getUser().setUsername("tester");
        assertEquals("tester", game.getUser().getUsername());
    }
}