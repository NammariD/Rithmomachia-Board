package test.Rithmomachia;

import org.junit.Assert;
import org.junit.Test;

import main.java.Rithmomachia.Board;
import main.java.Rithmomachia.Piece;
import main.java.Rithmomachia.Pos;

import java.util.Set;

public class EruptionTest {

    private String toString(Set<Pos> pp) {
        StringBuilder sb = new StringBuilder();
        for (Pos p : pp) {
            sb.append(p);
        }
        return sb.toString();
    }

    @Test
    public void testSameShape1() {
        String[] s = {
                "--- BT5 --- WT15"
        };
        Board board = new Board(1,4,s);
        Piece piece = board.getPiece(0,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,3]", capture);
    }

    @Test
    public void testSameShape2() {
        String[] s = {
                "--- BT5 --- --- WT20"
        };
        Board board = new Board(1,5,s);
        Piece piece = board.getPiece(0,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,4]", capture);
    }

    @Test
    public void testSameShape3() {
        String[] s = {
                "--- BT5 --- --- WT20"
        };
        Board board = new Board(1,5,s);
        Piece piece = board.getPiece(0,4);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,1]", capture);
    }

    @Test
    public void testSameShape4() {
        String[] s = {
                "---",
                "BT5",
                "---",
                "WT15",
        };
        Board board = new Board(4,1,s);
        Piece piece = board.getPiece(1,0);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[3,0]", capture);
    }

    @Test
    public void testSameShape5() {
        String[] s = {
                "---",
                "BT5",
                "---",
                "WT15",
        };
        Board board = new Board(4,1,s);
        Piece piece = board.getPiece(3,0);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[1,0]", capture);
    }

    @Test
    public void testSameShape6() {
        String[] s = {
                "BT5 --- --- ---",
                "--- --- --- ---",
                "--- --- --- ---",
                "--- --- --- WT20",
        };
        Board board = new Board(4,4,s);
        Piece piece = board.getPiece(0,0);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[3,3]", capture);
    }

    @Test
    public void testSameShape7() {
        String[] s = {
                "BT5 --- --- ---",
                "--- --- --- ---",
                "--- --- --- ---",
                "--- --- --- WT20",
        };
        Board board = new Board(4,4,s);
        Piece piece = board.getPiece(3,3);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,0]", capture);
    }

    @Test
    public void testSameShape8() {
        String[] s = {
                "--- --- --- BT5",
                "--- --- --- ---",
                "--- --- --- ---",
                "WT20 --- --- ---",
        };
        Board board = new Board(4,4,s);
        Piece piece = board.getPiece(0,3);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[3,0]", capture);
    }

    @Test
    public void testSameShape9() {
        String[] s = {
                "--- --- --- BT5",
                "--- --- --- ---",
                "--- --- --- ---",
                "WT20 --- --- ---",
        };
        Board board = new Board(4,4,s);
        Piece piece = board.getPiece(3,0);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,3]", capture);
    }

    @Test
    public void testDifferentShape1() {
        String[] s = {
                "--- BT5 --- WC15"
        };
        Board board = new Board(1,4,s);
        Piece piece = board.getPiece(0,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,3]", capture);
    }

    @Test
    public void testDifferentShape2() {
        String[] s = {
                "--- BT5 --- WS15"
        };
        Board board = new Board(1,4,s);
        Piece piece = board.getPiece(0,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        String capture = toString(possible);
        Assert.assertEquals("[0,3]", capture);
    }

    @Test
    public void testSameColor1() {
        String[] s = {
                "--- BT5 --- BT15"
        };
        Board board = new Board(1,4,s);
        Piece piece = board.getPiece(0,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        Assert.assertEquals(possible.size(), 0);
    }

    @Test
    public void testSameColor2() {
        String[] s = {
                "--- BT5 --- BT15"
        };
        Board board = new Board(1,4,s);
        Piece piece = board.getPiece(0,3);
        Set<Pos> possible = piece.eruptionCapture(board);
        Assert.assertEquals(possible.size(), 0);
    }

    @Test
    public void testMultiple1(){
        String[] s = {
                "BT4 BT4 BT4",
                "BT4 WT2 BT4",
                "BT4 BT4 BT4"
        };
        Board board = new Board(3,3,s);
        Piece piece = board.getPiece(1,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        Assert.assertEquals(possible.size(), 8);
    }

    @Test
    public void testMultiple2(){
        String[] s = {
                "BT4 WT4 BT4",
                "BT4 WT2 BT4",
                "BT4 BT4 BT4"
        };
        Board board = new Board(3,3,s);
        Piece piece = board.getPiece(1,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        Assert.assertEquals(possible.size(), 7);
    }

    @Test
    public void testMultiple3(){
        String[] s = {
                "BT6 BT4 BT1",
                "WT4 WT2 BS4",
                "BT4 WT1 BT4"
        };
        Board board = new Board(3,3,s);
        Piece piece = board.getPiece(1,1);
        Set<Pos> possible = piece.eruptionCapture(board);
        Assert.assertEquals(possible.size(), 5);
    }

    @Test
    public void testMultiple4(){
        String[] s = {
                "--- --- --- --- ---",
                "WT8 --- --- WS8 ---",
                "--- --- WT8 WT6 ---",
                "--- --- --- --- WS4",
                "WC8 --- --- BT2 WT1"
        };
        Board board = new Board(5,5,s);
        Piece piece = board.getPiece(4,3);
        Set<Pos> possible = piece.eruptionCapture(board);
        Assert.assertEquals(possible.size(), 5);
    }
}
