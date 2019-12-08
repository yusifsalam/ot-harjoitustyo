package game;

import game.domain.Board;
import game.domain.Game;
import game.domain.User;
import org.junit.Before;
import org.junit.Ignore;
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
    public void gameCanBeWon() {
        game.getBoard().setCellValueAt(2, 1024);
        game.getBoard().setCellValueAt(3, 1024);
        System.out.println(game);
        assertFalse(game.isGameOver());
        game.moveBoardRight();
        System.out.println(game);
        assertTrue(game.gameWon());
    }

    @Test
    public void gameCanBeLost() {
        game.getBoard().setCellValueAt(0,4);
        game.getBoard().setCellValueAt(1,2);
        game.getBoard().setCellValueAt(2,4);
        game.getBoard().setCellValueAt(3,8);

        game.getBoard().setCellValueAt(4,2);
        game.getBoard().setCellValueAt(5,4);
        game.getBoard().setCellValueAt(6,8);
        game.getBoard().setCellValueAt(7,128);

        game.getBoard().setCellValueAt(8,4);
        game.getBoard().setCellValueAt(9,8);
        game.getBoard().setCellValueAt(10,32);
        game.getBoard().setCellValueAt(11,256);

        game.getBoard().setCellValueAt(12,8);
        game.getBoard().setCellValueAt(13,16);
        game.getBoard().setCellValueAt(14,2);
        game.getBoard().setCellValueAt(15,512);
        game.moveBoardRight();
        assertTrue(game.isGameOver());

    }

    @Test
    public void scoreIsCalculatedCorrectly() {
        game.getBoard().setCellValueAt(0,4);
        game.getBoard().setCellValueAt(1,2);
        game.getBoard().setCellValueAt(2,4);
        game.getBoard().setCellValueAt(3,8);

        game.getBoard().setCellValueAt(4,2);
        game.getBoard().setCellValueAt(5,4);
        game.getBoard().setCellValueAt(6,8);
        game.getBoard().setCellValueAt(7,128);

        game.getBoard().setCellValueAt(8,4);
        game.getBoard().setCellValueAt(9,8);
        game.getBoard().setCellValueAt(10,32);
        game.getBoard().setCellValueAt(11,256);

        game.getBoard().setCellValueAt(12,8);
        game.getBoard().setCellValueAt(13,16);
        game.getBoard().setCellValueAt(14,2);
        game.getBoard().setCellValueAt(15,512);
        game.moveBoardRight();
        assertEquals(998, game.getBoard().calculateScore());
    }

    @Test
    public void userCanChangeName() {
        game.getUser().setUsername("tester");
        assertEquals("tester", game.getUser().getUsername());
    }
}