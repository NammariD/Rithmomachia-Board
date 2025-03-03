package test.Rithmomachia;

import static org.junit.Assert.assertEquals;

//import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import main.java.Rithmomachia.Board;
import main.java.Rithmomachia.Piece;
import main.java.Rithmomachia.Pos;

public class EncounterTest {

    private String toString(Set<Pos> pp) {
        StringBuilder sb = new StringBuilder();
        for (Pos p : pp) {
            sb.append(p);
        }
        return sb.toString();
    }

    
    //Demo Tests
    @Test
    public void testC1() {
        String[] s = {
            "--- WT5 --- BT5"
        };
        Board b = new Board(1, 4, s);
        b.printBoard();
        Piece p = b.getPiece(0, 1);
        Set<Pos> pp = p.encounterCapture(b);
        String t = toString(pp);
        assertEquals("[0,3]", t);
        
        
    }

    @Test
    public void testC2() {
        String[] s = {
            "--- BT5 --- WT5"
        };
        Board b = new Board(1, 4, s);
        b.printBoard();
        Piece p = b.getPiece(0, 3);
        //Board.contains(0,3,5);
        Set<Pos> pp = p.encounterCapture(b);
        String t = toString(pp);
        assertEquals("[0,1]", t);
    }

    @Test
    public void testC3() {
        String[] s = {
            "---",
            "WT5",
            "---",
            "BT5"
        };
        Board b = new Board(4, 1, s);
        b.printBoard();
        Piece p = b.getPiece(1, 0);
        Set<Pos> pp = p.encounterCapture(b);
        String t = toString(pp);
        assertEquals("[3,0]", t);
    }

    @Test
    public void testC4() {
        String[] s = {
            "---",
            "BT5",
            "---",
            "WT5"
        };
        Board b = new Board(4, 1, s);
        b.printBoard();
        Piece p = b.getPiece(3, 0);
        Set<Pos> pp = p.encounterCapture(b);
        String t = toString(pp);
        assertEquals("[1,0]", t);
    }

    @Test
    public void testNC1() {
        String[] s = {
            "--- WT5 WC2 BT5"
        };
        Board b = new Board(1, 4, s);
        b.printBoard();
        Piece p = b.getPiece(0, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }

    @Test
    public void testNC2() {
        String[] s = {
            "--- BT5 BC2 WT5"
        };
        Board b = new Board(1, 4, s);
        b.printBoard();
        Piece p = b.getPiece(0, 3);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }

    @Test
    public void testNC3() {
        String[] s = {
            "---",
            "WT5",
            "BC2",
            "BT5"
        };
        Board b = new Board(4, 1, s);
        b.printBoard();
        Piece p = b.getPiece(1, 0);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }

    @Test
    public void testNC4() {
        String[] s = {
            "---",
            "BT5",
            "BC2",
            "WT5"
        };
        Board b = new Board(4, 1, s);
        b.printBoard();
        Piece p = b.getPiece(3, 0);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    
    //Circle Tests
    @Test
    public void testCT1() {
        String[] s = {
            "BC1 BC1 BC1",
            "BC1 WC1 BC1",
            "BC1 BC1 BC1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT2() {
        String[] s = {
            "BT1 BT1 BT1",
            "BT1 WC1 BT1",
            "BT1 BT1 BT1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT3() {
        String[] s = {
            "BS1 BS1 BS1",
            "BS1 WC1 BS1",
            "BS1 BS1 BS1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT4() {
        String[] s = {
            "BS2 BS2 BS2",
            "BS2 WC1 BS2",
            "BS2 BS2 BS2"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT5() {
        String[] s = {
            "WS1 WS1 WS1",
            "WS1 WC1 WS1",
            "WS1 WS1 WS1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT6() {
        String[] s = {
            "WC1 WC1 WC1",
            "WC1 BC1 WC1",
            "WC1 WC1 WC1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT7() {
        String[] s = {
            "WT1 WT1 WT1",
            "WT1 BC1 WT1",
            "WT1 WT1 WT1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT8() {
        String[] s = {
            "WT1 WT1 WT1",
            "WT1 BC1 WT1",
            "WT1 WT1 WT1"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    
    @Test
    public void testCT9() {
        String[] s = {
            "WT2 WT2 WT2",
            "WT2 BC1 WT2",
            "WT2 WT2 WT2"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT10() {
        String[] s = {
            "BT2 BT2 BT2",
            "BT2 BC1 BT2",
            "BT2 BT2 BT2"
            
        };
        Board b = new Board(3, 3, s);
        b.printBoard();
        Piece p = b.getPiece(1, 1);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT11() {
        String[] s = {
        	"WT1 WT1 WT1 WT1 WT1",
            "WT1 WT2 WT2 WT2 WT1",
            "WT1 WT2 BC1 WT2 WT1",
            "WT1 WT2 WT2 WT2 WT1",
            "WT1 WT1 WT1 WT1 WT1"
            
        };
        Board b = new Board(5, 5, s);
        b.printBoard();
        Piece p = b.getPiece(2, 2);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT12() {
        String[] s = {
        	"--- --- --- --- ---",
            "--- --- --- --- ---",
            "--- --- BC1 --- ---",
            "--- --- --- --- ---",
            "--- --- --- --- ---"
            
        };
        Board b = new Board(5, 5, s);
        b.printBoard();
        Piece p = b.getPiece(2, 2);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT13() {
        String[] s = {
        	"WC1 WC1 WC1 WC1 WC1",
            "WC1 --- --- --- WC1",
            "WC1 --- BC1 --- WC1",
            "WC1 --- --- --- WC1",
            "WC1 WC1 WC1 WC1 WC1"
            
        };
        Board b = new Board(5, 5, s);
        b.printBoard();
        Piece p = b.getPiece(2, 2);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT14() {
        String[] s = {
        		"--- --- --- --- ---",
                "--- WC1 WC1 WC1 ---",
                "--- WC1 BC1 WC1 ---",
                "--- WC1 WC1 WC1 ---",
                "--- --- --- --- ---"
            
        };
        Board b = new Board(5, 5, s);
        b.printBoard();
        Piece p = b.getPiece(2, 2);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT15() {
        String[] s = {
        		"WC1"
            
        };
        Board b = new Board(1, 1, s);
        b.printBoard();
        Piece p = b.getPiece(0, 0);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(0, pp.size());
    }
    @Test
    public void testCT16() {
        String[] s = {
        		"WC1 BC1",
        		"BC1 BC1"
            
        };
        Board b = new Board(2, 2, s);
        b.printBoard();
        Piece p = b.getPiece(0, 0);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(3, pp.size());
    }
    @Test
    public void testCT17() {
        String[] s = {
        		"BC1 BC1 BC1 BC1 BC1 ",
        		"BC1 --- --- --- BC1 ",
        		"BC1 --- WT1 --- BC1 ",
        		"BC1 --- --- --- BC1 ",
        		"BC1 BC1 BC1 BC1 BC1"
        };
        Board b = new Board(5, 5, s);
        b.printBoard();
        Piece p = b.getPiece(2, 2);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    @Test
    public void testCT18() {
        String[] s = {
        		"BC1 BC1 BC1 BC1 BC1 BC1 BC1",
        		"BC1 --- --- --- --- --- BC1",
        		"BC1 --- --- --- --- --- BC1",
        		"BC1 --- --- WS1 --- --- BC1",
        		"BC1 --- --- --- --- --- BC1",
        		"BC1 --- --- --- --- --- BC1",
        		"BC1 BC1 BC1 BC1 BC1 BC1 BC1",
        		
        };
        Board b = new Board(7, 7, s);
        b.printBoard();
        Piece p = b.getPiece(3, 3);
        Set<Pos> pp = p.encounterCapture(b);
        assertEquals(8, pp.size());
    }
    
    
    
   
    
    
    
    
    
    

    public static void main(String[] args) {
        // Uncomment if you want to allow user-defined board initialization
        /*
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of rows: ");
            int numRows = scanner.nextInt();
            System.out.print("Enter number of columns: ");
            int numCols = scanner.nextInt();
            Board.initBoard(numRows, numCols);
        }
        */
    }
}
