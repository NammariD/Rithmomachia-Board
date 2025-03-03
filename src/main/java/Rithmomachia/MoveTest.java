package main.java.Rithmomachia;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Set;



public class MoveTest {

    private String toString(Set<Pos> pp) {
        StringBuilder sb = new StringBuilder();
        for (Pos p : pp) {
            sb.append(p);
        }
        return sb.toString();
    }

    @Test
    public void Test1() {
        String[] s = {
                "--- --- --- --- ---",
                "--- --- --- --- ---",
                "--- --- WC1 --- ---",
                "--- --- --- --- ---",
                "--- --- --- --- ---",
        };
        Board b = new Board(5, 5, s);
        Piece p = b.getPiece(3,3);
        Set<Move> moves = p.findMoves(b);
        assertEquals(8, moves.size());
    }
}