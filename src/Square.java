import java.util.HashSet;
import java.util.Set;

public class Square extends Piece{
	public Square(Color color, int value, int startX, int startY) {
		super(color, value, startX, startY);
	}
	
	 @Override
	    public String toString() {
	        return String.format("%S%d", color, value);
	    }
	 
	 @Override
	 public Set<Move> findMoves(int x, int y, Board board) {
	     Set<Move> mm = new HashSet<>();
	     int distance = 3;  // Move 3 squares

	     // Horizontal and vertical moves (distance squares)
	     if (Board.isValidPos(x + distance, y) && Board.pathIsClear(x, y, x + distance, y, board)) {
	         mm.add(new Move(x, y, x + distance, y));
	     }
	     if (Board.isValidPos(x - distance, y) && Board.pathIsClear(x, y, x - distance, y, board)) {
	         mm.add(new Move(x, y, x - distance, y));
	     }
	     if (Board.isValidPos(x, y + distance) && Board.pathIsClear(x, y, x, y + distance, board)) {
	         mm.add(new Move(x, y, x, y + distance));
	     }
	     if (Board.isValidPos(x, y - distance) && Board.pathIsClear(x, y, x, y - distance, board)) {
	         mm.add(new Move(x, y, x, y - distance));
	     }

	     // Diagonal moves (distance squares)
	     if (Board.isValidPos(x + distance, y + distance) && Board.pathIsClear(x, y, x + distance, y + distance, board)) {
	         mm.add(new Move(x, y, x + distance, y + distance));
	     }
	     if (Board.isValidPos(x + distance, y - distance) && Board.pathIsClear(x, y, x + distance, y - distance, board)) {
	         mm.add(new Move(x, y, x + distance, y - distance));
	     }
	     if (Board.isValidPos(x - distance, y + distance) && Board.pathIsClear(x, y, x - distance, y + distance, board)) {
	         mm.add(new Move(x, y, x - distance, y + distance));
	     }
	     if (Board.isValidPos(x - distance, y - distance) && Board.pathIsClear(x, y, x - distance, y - distance, board)) {
	         mm.add(new Move(x, y, x - distance, y - distance));
	     }

	     return mm;
	 }
	 
	 @Override
	    public Set<Pos> capture(int x, int y, Board board) {
	        Set<Pos> pp = new HashSet<>();
	        int distance = 3;
	        if (Board.isValidPos(x + distance, y)
	                && board.contains(x + distance, y, value)) {
	            pp.add(new Pos(x + distance, y));
	        }
	        if (Board.isValidPos(x - distance, y)
	                && board.contains(x - distance, y, value)) {
	            pp.add(new Pos(x - distance, y));
	        }
	        if (Board.isValidPos(x, y + distance)
	                && board.contains(x, y + distance, value)) {
	            pp.add(new Pos(x, y + distance));
	        }
	        if (Board.isValidPos(x, y - distance)
	                && board.contains(x, y - distance, value)) {
	            pp.add(new Pos(x, y - distance));
	        }
	        return pp;
	    }


	 



}
 