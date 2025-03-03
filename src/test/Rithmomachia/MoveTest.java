package test.Rithmomachia;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;

import main.java.Rithmomachia.Board;
import main.java.Rithmomachia.Move;
import main.java.Rithmomachia.Piece;

public class MoveTest {
	
    //Demo Tests
    @Test
    public void test1() {
        String[] s = {
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- WC1 --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        };
        Board b = new Board(7, 7, s);
        System.out.print("Test 1\n");
        b.printBoard();
        Piece p = b.getPiece(3, 3);
        Set<Move> pp = p.findMoves(b);
        assertEquals(8, pp.size());
    }
    
    @Test
    public void test2() {
        String[] s = {
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- WT1 --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        };
        Board b = new Board(7, 7, s);
        System.out.print("Test 2\n");
        b.printBoard();
        Piece p = b.getPiece(3, 3);
        Set<Move> pp = p.findMoves(b);
        assertEquals(12, pp.size());
    }
    
    @Test
    public void test3() {
        String[] s = {
        		
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- WS1 --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        		"--- --- --- --- --- --- ---",
        };
        Board b = new Board(7, 7, s);
        System.out.print("Test 3\n");
        b.printBoard();
        Piece p = b.getPiece(3, 3);
        Set<Move> pp = p.findMoves(b);
        assertEquals(8, pp.size());
    }
    
    
    
    
}
