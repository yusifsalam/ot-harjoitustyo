package gameof2048;

import gameof2048.domain.Board;
import gameof2048.domain.Game;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void boardIsGeneratedCorrectly() {
        Board board = new Board(new Random());
        assertEquals(2, board.getCellValueAt(4,4));
    }
}