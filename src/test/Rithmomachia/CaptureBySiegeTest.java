package test.Rithmomachia;



import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import main.java.Rithmomachia.Board;
import main.java.Rithmomachia.Piece;
import main.java.Rithmomachia.Pos;

import org.junit.Assert;

public class CaptureBySiegeTest {

    private String toString(Set<Pos> pp) {
        StringBuilder sb = new StringBuilder();
        for (Pos p : pp) {
            sb.append(p);  // Append each Pos object (which calls Pos.toString())
        }
        return sb.toString();
    }

    private void printResults(Set<Pos> actual, Set<Pos> expected) {
        System.out.println("Expected: " + toString(expected) + " | Actual: " + toString(actual));
    }

    @Test
    public void testEdgeCase() {
        String[] s = {
                "--- --- ---",
                "--- BC1 ---",
                "WC1 --- ---"
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> actual = p.captureBySiege(b);

        Set<Pos> expected = new HashSet<>(); // No capture

        printResults(actual, expected);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testCaptureWithFullBlock() {
        String[] s = {
                "BC1 BC1 BC1",
                "BC1 WC1 BC1",
                "BC1 BC1 BC1"
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> actual = p.captureBySiege(b);

        Set<Pos> expected = new HashSet<>();
        expected.add(new Pos(1, 1));

        printResults(actual, expected);
        Assert.assertEquals(toString(expected), toString(actual));
    }

    @Test
    public void testOrthogonalCapture() {
        String[] s = {
                "--- BT5 --- ---",
                "BT5 WT5 BT5 ---",
                "--- BT5 --- ---"
        };
        Board b = new Board(3, 4, s);
        b.printBoard();

        Piece p = b.getPiece(1, 1);
        Set<Pos> actual = p.captureBySiege(b);

        Set<Pos> expected = new HashSet<>();
        expected.add(new Pos(1, 1));

        printResults(actual, expected);
        Assert.assertEquals(toString(expected), toString(actual));
    }

    @Test
    public void testDiagonalCapture() {
        String[] s = {
                "WC1 --- WC1",
                "--- BC1 ---",
                "WC1 --- WC1"
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> actual = p.captureBySiege(b);

        Set<Pos> expected = new HashSet<>();
        expected.add(new Pos(1, 1));

        printResults(actual, expected);
        Assert.assertEquals(toString(expected), toString(actual));
    }
    @Test
    public void testNotCaptured() {
        String[] s = {
                "--- WT5 --- BT5"
        };
        Board b = new Board(1, 4, s);
        b.printBoard();
        Piece p = b.getPiece(0, 1);
        Set<Pos> actual = p.captureBySiege(b);

        Set<Pos> expected = new HashSet<>(); // No capture

        printResults(actual, expected);
        Assert.assertEquals(expected, actual);
    }
}
