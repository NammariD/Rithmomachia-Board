package main.java.Rithmomachia;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Board {
    private int rows; //Number of Rows
    private int cols; //Number of Columns
    private int blackEnemyTerritoryStartColumn;
    private int whiteEnemyTerritoryStartColumn;
    private Piece[][] pieces;
    private VictoryManager victoryManager;

    public Board(int numRows, int numCols, String[] str) {
        initBoard(numRows,numCols);
        //System.out.println("Start [0, 0]: " + pieces[0][0]);
        //System.out.println("After 'pieces' are added [0, 0]: " + pieces[0][0]);
        this.whiteEnemyTerritoryStartColumn = 1;
        this.blackEnemyTerritoryStartColumn = numCols-2;
        for (int i = 0; i < str.length; i++) {
            String[] t = str[i].split(" +");
            for (int j = 0; j < t.length; j++) {
                //System.out.println("Placing piece at [" + i + "][" + j + "]: " + t[j]);
                pieces[i][j] = fromString(t[j], i, j);
                //System.out.println("Piece at [0, 0]: " + pieces[0][0]);

            }
        }
        victoryManager = new VictoryManager(this, null, 0, 0, 0);
    }


    // A proper board must be presented with White on left side and Black on right side
    public Board(int numRows, int numCols, String[] str, Victory victory, int bodies, int digits, int values) {
        initBoard(numRows,numCols);
        //System.out.println("Start [0, 0]: " + pieces[0][0]);
        //System.out.println("After 'pieces' are added [0, 0]: " + pieces[0][0]);
        for (int i = 0; i < str.length; i++) {
            String[] t = str[i].split(" +");
            for (int j = 0; j < t.length; j++) {
                //System.out.println("Placing piece at [" + i + "][" + j + "]: " + t[j]);
                pieces[i][j] = fromString(t[j], i, j);
                //System.out.println("Piece at [0, 0]: " + pieces[0][0]);

            }
        }
        victoryManager = new VictoryManager(this, victory, bodies, digits, values);
    }

    private Piece fromString(String s, int row, int col) {
        if (s.charAt(0) == '-') {
            return null;
        }
        Color c = Color.W;
        switch(s.charAt(0)) {
            case 'B' :
                c = Color.B;
                if (col <= this.blackEnemyTerritoryStartColumn){
                    this.blackEnemyTerritoryStartColumn = col -1;
                }
                break;
            case 'W' :
                c = Color.W;
                if (row >= this.whiteEnemyTerritoryStartColumn){
                    this.whiteEnemyTerritoryStartColumn = col+1;
                }
                break;
            default:
                throw new AssertionError();
        }
        int value = Integer.valueOf(s.substring(2));
        // Need to build Pyramid too. Need to update enemy Territory start.
        switch (s.charAt(1)) {
            case 'C':
                return new Circle(c, value, row, col);
            case 'S':
                return new Square(c, value, row, col);
            case 'T':
                return new Triangle(c, value, row, col);
            default:
                throw new AssertionError();
        }
    }



    private void initBoard(int numRows, int numCols) {
        rows = numRows;
        cols = numCols;
        this.blackEnemyTerritoryStartColumn = 1;
        this.whiteEnemyTerritoryStartColumn = numCols-2;
        pieces = new Piece[rows][cols];
    }

    public void printBoard() {
        // Print the numbers at the top for the columns
        System.out.print("    ");
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%9d", i);
        }
        System.out.println();

        // Horizontal separator line for dynamic column size
        System.out.print("    ");
        for (int i = 0; i < cols; i++) {
            System.out.print("----------");
        }
        System.out.println();

        // Letters on the sides for the rows
        char letter = 'a';
        for (int i = 0; i < rows; i++) {
            System.out.printf(" %c |", letter);  // Print the letter for the row
            for (int j = 0; j < cols; j++) {
                if (pieces[i][j] == null) {
                    System.out.print("         |");  // Empty space if no piece
                } else {
                    System.out.printf("%-8s|", pieces[i][j]);  // Piece name
                }
            }
            System.out.printf(" %c", letter);  // Print the letter at the end of the row
            letter++;
            System.out.println();

            // Horizontal separator line for dynamic column size
            System.out.print("    ");
            for (int k = 0; k < cols; k++) {
                System.out.print("----------");
            }
            System.out.println();
        }

        // Print the column numbers again at the bottom
        System.out.print("    ");
        for (int i = 1; i <= cols; i++) {
            System.out.printf("%9d", i);
        }
        System.out.println();
    }


   /* static void startGame() {
        // Initializes black pieces
        new Circle(Color.B, 3, 5, 2);
        new Circle(Color.B, 5, 5, 3);
        new Circle(Color.B, 7, 5, 4);
        new Circle(Color.B, 9, 5, 5);
        new Circle(Color.B, 9, 4, 2);
        new Circle(Color.B, 25, 4, 3);
        new Circle(Color.B, 49, 4, 4);
        new Circle(Color.B, 81, 4, 5);

        new Triangle(Color.B, 16, 4, 0);
        new Triangle(Color.B, 12, 4, 1);
        new Triangle(Color.B, 90, 4, 6);
        new Triangle(Color.B, 100, 4, 7);
        new Triangle(Color.B, 36, 3, 2);
        new Triangle(Color.B, 30, 3, 3);
        new Triangle(Color.B, 56, 3, 4);
        new Triangle(Color.B, 64, 3, 5);

        new Square(Color.B, 49, 2, 0);
        new Square(Color.B, 28, 3, 0);
        new Square(Color.B, 121, 2, 1);
        new Square(Color.B, 66, 3, 1);
        new Square(Color.B, 225, 2, 6);
        new Square(Color.B, 120, 3, 6);
        new Square(Color.B, 361, 2, 7);

        // Initializes white pieces
        new Circle(Color.W, 8, 10, 2);
        new Circle(Color.W, 6, 10, 3);
        new Circle(Color.W, 4, 10, 4);
        new Circle(Color.W, 2, 10, 5);
        new Circle(Color.W, 64, 11, 2);
        new Circle(Color.W, 36, 11, 3);
        new Circle(Color.W, 16, 11, 4);
        new Circle(Color.W, 4, 11, 5);

        new Triangle(Color.W, 81, 11, 0);
        new Triangle(Color.W, 72, 11, 1);
        new Triangle(Color.W, 6, 11, 6);
        new Triangle(Color.W, 9, 11, 7);
        new Triangle(Color.W, 49, 12, 2);
        new Triangle(Color.W, 42, 12, 3);
        new Triangle(Color.W, 20, 12, 4);
        new Triangle(Color.W, 25, 12, 5);

        new Square(Color.W, 153, 12, 0);
        new Square(Color.W, 289, 13, 0);
        new Square(Color.W, 169, 13, 1);
        new Square(Color.W, 45, 12, 6);
        new Square(Color.W, 81, 13, 6);
        new Square(Color.W, 15, 12, 7);
        new Square(Color.W, 25, 13, 7);
    }*/

    public void capture(Piece piece) {
        victoryManager.capture(piece);
    }

    public boolean isValidPos(int row, int col) {
        //System.out.print("isValidPos called: ");
        return row >= 0 && row < rows && col >= 0 && col < cols;

    }

    // Gets all pieces of the specified color on the board
    public Set<Piece> getPiecesOfColor(Color color){
        Set<Piece> pieces = new HashSet<>();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (this.pieces[i][j] != null && this.pieces[i][j].getColor() == color){
                    pieces.add(this.pieces[i][j]);
                }
            }
        }
        return pieces;
    }

    // Returns all pieces on the board as sets mapped to their color
    public Map<Color, Set<Piece>> getAllPieces(){
        Map<Color, Set<Piece>> pieces = new HashMap<>();
        pieces.put(Color.B, new HashSet<>());
        pieces.put(Color.W, new HashSet<>());
        pieces.get(Color.B).addAll(this.getPiecesOfColor(Color.B));
        pieces.get(Color.W).addAll(this.getPiecesOfColor(Color.W));
        return pieces;
    }

    public Set<Piece> findClosestNeighbors(int row, int col){
        Set<Piece> neighbors = new HashSet<>();
        if (findClosestRight(row, col)!=null)
            neighbors.add(findClosestRight(row, col));
        if (findClosestLeft(row, col)!=null)
            neighbors.add(findClosestLeft(row, col));
        if (findClosestUp(row, col)!=null)
            neighbors.add(findClosestUp(row, col));
        if (findClosestDown(row, col)!=null)
            neighbors.add(findClosestDown(row, col));
        if (findClosestUpRight(row, col)!=null)
            neighbors.add(findClosestUpRight(row, col));
        if (findClosestUpLeft(row, col)!=null)
            neighbors.add(findClosestUpLeft(row, col));
        if (findClosestDownRight(row, col)!=null)
            neighbors.add(findClosestDownRight(row, col));
        if (findClosestDownLeft(row, col)!=null)
            neighbors.add(findClosestDownLeft(row, col));
        return neighbors;
    }

    public Piece findClosestRight(int row, int col) {
        for (int i = col+1; i<this.cols; i++){
            if (!this.isEmpty(row, i)) {
                return this.getPiece(row, i);
            }
        }
        return null;
    }

    public Piece findClosestLeft(int row, int col) {
        for (int i = col -1; i>=0; i--){
            if (!this.isEmpty(row, i)) {
                return this.getPiece(row, i);
            }
        }
        return null;
    }

    public Piece findClosestUp(int row, int col) {
        for (int i = row-1; i>=0; i--){
            if (!this.isEmpty(i, col)) {
                return this.getPiece(i, col);
            }
        }
        return null;
    }

    public Piece findClosestDown(int row, int col) {
        for (int i = row+1; i<this.rows; i++){
            if (!this.isEmpty(i, col)) {
                return this.getPiece(i, col);
            }
        }
        return null;
    }

    public Piece findClosestUpRight(int row, int col) {
        for (int i = row-1, j = col+1; i>=0 && j<this.cols; i--, j++){
            if (!this.isEmpty(i, j)) {
                return this.getPiece(i, j);
            }
        }
        return null;
    }

    public Piece findClosestDownRight(int row, int col) {
        for (int i = row+1, j = col+1; i<this.rows && j<this.cols; i++, j++){
            if (!this.isEmpty(i, j)) {
                return this.getPiece(i, j);
            }
        }
        return null;
    }

    public Piece findClosestUpLeft(int row, int col) {
        for (int i = row-1, j = col-1; i>=0 && j>=0; i--, j--){
            if (!this.isEmpty(i, j)) {
                return this.getPiece(i, j);
            }
        }
        return null;
    }

    public Piece findClosestDownLeft(int row, int col) {
        for (int i = row+1, j = col-1; i<this.rows && j>=0; i++, j--){
            if (!this.isEmpty(i, j)) {
                return this.getPiece(i, j);
            }
        }
        return null;
    }

    public boolean isEmpty(int row, int col) {
        if(!isValidPos(row,col)) {
            return false;
        }

        return pieces[row][col] == null;
    }

    // Helper method to check if the path is clear
    public boolean pathIsClear(int row1, int col1, int row2, int col2, Board board) {
        // Determine the direction of movement
        int dRow = Integer.signum(row2 - row1); // Direction along x-axis
        int dCol = Integer.signum(col2 - col1); // Direction along y-axis

        // Loop over each square between (row1, col1) and (row2, col2)
        for (int i = 1; i < Math.max(Math.abs(row2 - row1), Math.abs(col2 - col1)); i++) {
            int nextRow = row1 + i * dRow; // The x-coordinate of the next square along the path
            int nextCol = col1 + i * dCol; // The y-coordinSate of the next square along the path

            // If any square along the path is not empty, the path is blocked
            if (!board.isEmpty(nextRow, nextCol)) {
                return false; // Path is blocked by another piece
            }
        }

        // Check if the destination square (row2, col2) is empty
        // If it's occupied, the piece cannot move there
        if (!board.isEmpty(row2, col2)) {
            return false; // Target square is occupied
        }

        // If all squares are empty and the target square is not occupied, the path is clear
        return true;
    }

    public boolean capturepathIsClear(int rowStart, int colStart, int rowEnd, int colEnd, Board board) {
        // Determine the direction of movement
        int dRow = Integer.signum(rowEnd - rowStart); // Direction along rows
        int dCol = Integer.signum(colEnd - colStart); // Direction along cols

        // Loop over each square between (x1, y1) and (x2, col2)
        for (int i = 1; i < Math.max(Math.abs(rowEnd - rowStart), Math.abs(colEnd - colStart)); i++) {
            int nextRow = rowStart + i * dRow; // The row-coordinate of the next square along the path
            int nextCol = colStart + i * dCol; // The col-coordinate of the next square along the path

            // If any square along the path is not empty, the path is blocked
            if (!board.isEmpty(nextRow, nextCol)) {
                return false; // Path is blocked by another piece
            }
        }


        // If all squares are empty and the target square is not occupied, the path is clear
        return true;
    }

    public boolean contains(int row, int col, int value) {
        //System.out.print("Contains function called");
        // Check if the position (x, y) contains a piece with the specific value.
        Piece piece = getPiece(row, col); // Retrieves the piece at position (x, y)
        if (piece != null && piece.getValue() == value) {
            return true; // Return true if the piece has the same value
        }
        return false;
    }

    //Check if a piece is the same as another piece
    public boolean checkColor(int row, int col, Color c) {
        Piece piece = getPiece(row,col);
        if (piece.getColor() == c) {
            return false;
        }
        return true;
    }

    // This returns the number of squares between two pieces INCLUSIVE.
    // For example, if "---" represents a blank square, P1 --- --- P2 returns 4.
    // Maybe need to consider if pieces are the same piece?
    public int distanceBetween(Piece piece1, Piece piece2) {
        // Always minimum of 2 squares
        int totalSquares = 2;
        // Determine how many rows of separation
        int numRows = Math.abs(piece1.getRow() - piece2.getRow());
        // Determine how many columns of separation
        int numCols = Math.abs(piece1.getCol() - piece2.getCol());
        if (numRows == 0)
            // If pieces are in same column, use the column separation
            totalSquares = numCols + 1;
        else
            // Otherwise, pieces are either horizontal or diagonal. May use row separator in each case
            totalSquares = numRows + 1;
        return totalSquares;
    }

    public void setPiece(int row, int col, Piece piece) {
        if (piece != null) {
            piece.setRow(row);
            piece.setCol(col);
        }
        pieces[row][col] = piece;
    }

    // Need to handle Pyramid because it is multiple pieces. How to do this? Calls to this must run multiple times if encounter a pyramid?
    public Piece getPiece(int row, int col) {
        return pieces[row][col];
    }

    // This generates all tuples
    // Note: OUTPUT MUST BE LIST TO MAINTAIN ORDER!
    // Just iterate through all tuples, each time one is generated, run check
    // I'm pretty sure this doesn't produce any non-tuples...like it won't try to check any list less than 3?
    // Have to test later.
    public Set<List<Piece>> getTuplesForColor(Color color) {
        // Generate new set to return. Set contains lists of three pieces. Must return lists as order matters.
        Set<List<Piece>> tuples = new HashSet<>();
        // Grab every piece of the color we are checking from the board.
        Set<Piece> piecesToCheck = this.getPiecesOfColor(color);
        // Pick a piece to be the first anchor
        for (Piece firstAnchor : piecesToCheck) {
            // Find all of the first anchor's neighbors.
            Set<Piece> firstAnchorNeighbors = findClosestNeighbors(firstAnchor.getRow(), firstAnchor.getCol());
            // Pick a piece to be the second anchor.
            for (Piece secondAnchor : firstAnchorNeighbors) {
                // Find all of the second anchor's neighbors.
                Set<Piece> secondAnchorNeighbors = findClosestNeighbors(secondAnchor.getRow(), secondAnchor.getCol());
                // Iterate over each of the third anchors
                for (Piece thirdAnchor : secondAnchorNeighbors) {
                    // Add the candidate to a new list.
                    List<Piece> tupleCandidate = new ArrayList<>();
                    tupleCandidate.add(firstAnchor);
                    tupleCandidate.add(secondAnchor);
                    tupleCandidate.add(thirdAnchor);
                    // If that candidate is viable, add it to our set.
                    if (isViableTuple(tupleCandidate)) {
                        tuples.add(tupleCandidate);
                    }
                } // continue iterating through remaining second anchors
            } // continue iterating through first anchors
        }
        return tuples;
    }

    // Check if candidate is viable.
    private boolean isViableTuple(List<Piece> tupleCandidate){
        return (isCorrectPosition(tupleCandidate)
                && isCorrectOrder(tupleCandidate)
                && isCorrectProportion(tupleCandidate));
    }

    // Check if tuple is in enemy territory. Note: this automatically extends to quadruples.
    private boolean isCorrectPosition(List<Piece> piecesToCheck) {
        // The beginning of each enemy territory is determined when the board is built.
        int enemyStartColumn = 0;
        // initiate our variables
        boolean isCorrect = true;
        // A tuple always starts with the current color's piece, so we grab and switch that here
        switch(piecesToCheck.get(0).getColor()){
            case W:
                enemyStartColumn = this.whiteEnemyTerritoryStartColumn;
                // Ensure each piece in the candidate is inside the enemy territory. Break loop early if not.
                for (Piece piece : piecesToCheck) {
                    if (piece.getCol() < enemyStartColumn){
                        isCorrect = false;
                        break;
                    }
                }
                break;
            case B:
                // Same exact thing if we're starting with black
                enemyStartColumn = this.blackEnemyTerritoryStartColumn;
                for (Piece piece : piecesToCheck) {
                    if (piece.getCol() > enemyStartColumn){
                        isCorrect = false;
                        break;
                    }
                }
                break;
            default:
                return false;
        }
        return isCorrect;
    }

    // This will calculate if the tuple is correctly ordered, ie ascending or descending. Extends to quadruples.
    private boolean isCorrectOrder(List<Piece> piecesToCheck) {
        // Each tuple will be pre-vetted for order, so a quad can be checked with the last three values.
        // These ternary statements determine that.
        int a = piecesToCheck.size() == 3 ? piecesToCheck.get(0).getValue() : piecesToCheck.get(1).getValue();
        int b = piecesToCheck.size() == 3 ? piecesToCheck.get(1).getValue() : piecesToCheck.get(2).getValue();
        int c = piecesToCheck.size() == 3 ? piecesToCheck.get(2).getValue() : piecesToCheck.get(3).getValue();
        // Only true if values are strictly increasing or strictly decreasing.
        return ((a < b && b < c)
                || (a > b && b > c));
    }

    // Calculate whether the tuple is correctly proportioned. Extends to quadruples.
    private boolean isCorrectProportion(List<Piece> piecesToCheck) {
        // Calculate distance between 3 pieces. Since tuples are pre-vetted, can expand to quadruples by checking the final 3 pieces.
        int distance1 = piecesToCheck.size() == 3 ? distanceBetween(piecesToCheck.get(0), piecesToCheck.get(1)) : distanceBetween(piecesToCheck.get(1), piecesToCheck.get(2));
        int distance2 = piecesToCheck.size() == 3 ? distanceBetween(piecesToCheck.get(1), piecesToCheck.get(2)) : distanceBetween(piecesToCheck.get(2), piecesToCheck.get(3));
        return distance1 == distance2;
    }

    public Set<List<Piece>> getQuadruplesForColor(Color color) {
        Set<List<Piece>> quadruples = new HashSet<>();
        Set<List<Piece>> tuples = this.getTuplesForColor(color);
        Map<List<Piece>, List<Directions>> tupleSearchMap = new HashMap<>();
        // Do more stuff here.
        for (List<Piece> tuple : tuples) {
            List<Directions> directionsToSearch = new ArrayList<>();
            int row1 = tuple.get(0).getRow();
            int col1 = tuple.get(0).getCol();
            int row2 = tuple.get(1).getRow();
            int col2 = tuple.get(1).getCol();
            int row3 = tuple.get(2).getRow();
            int col3 = tuple.get(2).getCol();
            // Orthogonal or diagonal straight line
            if ((row1 == row2 && row2 == row3)
                    || (col1 == col2 && col2 == col3)
                    || (row1 < row2 && row2 < row3 && col1 < col2 && col2 < col3)
                    || (row1 > row2 && row2 > row3 && col1 > col2 && col2 > col3)
                    || (row1 < row2 && row2 < row3 && col1 > col2 && col2 > col3)
                    || (row1 > row2 && row2 > row3 && col1 < col2 && col2 < col3))
            {
                directionsToSearch.add(Directions.UP);
                directionsToSearch.add(Directions.DOWN);
                directionsToSearch.add(Directions.LEFT);
                directionsToSearch.add(Directions.RIGHT);
                directionsToSearch.add(Directions.UPLEFT);
                directionsToSearch.add(Directions.UPRIGHT);
                directionsToSearch.add(Directions.DOWNLEFT);
                directionsToSearch.add(Directions.DOWNRIGHT);
            // Piece two is directly above piece 1
            } else if (col1 == col2 && row1 > row2) {
                // Piece 3 is left/right of Piece 1
                if (row3 == row1) {
                    // Determine which way angle is pointing.
                    directionsToSearch.add(col3 < col2 ? Directions.DOWNLEFT : Directions.DOWNRIGHT);
                // Piece 3 is left/right of piece2
                } else if (row3 == row2) {
                    directionsToSearch.add(Directions.DOWN);
                    directionsToSearch.add(col3 < col2 ? Directions.LEFT : Directions.RIGHT);
                // piece 3 is diagonally up from piece 2
                } else {
                    // Only need to search in one direction
                    directionsToSearch.add(col3 < col2 ? Directions.UPLEFT : Directions.UPRIGHT);
                }
            // piece 2 is diagonally up-left from piece 1
            } else if (col1 > col2 && row1 > row2) {
                // piece 3 is below piece 2
                if (col3 == col2 && row3 == row1) {
                    directionsToSearch.add(Directions.DOWN);
                // ^ shaped
                } else if (row3 == row1 && col3 < col2) {
                    directionsToSearch.add(Directions.DOWNLEFT);
                    directionsToSearch.add(Directions.DOWNRIGHT);
                // Piece 3 is either directly left or right of piece 2
                } else if (row3 == row2) {
                    directionsToSearch.add(col3 < col2 ? Directions.LEFT : Directions.RIGHT);
                // Piece 3 is above piece 2
                } else if (col3 == col2) {
                    directionsToSearch.add(Directions.UP);
                // < shaped
                } else {
                    directionsToSearch.add(Directions.UPRIGHT);
                    directionsToSearch.add(Directions.DOWNLEFT);
                }
            // Piece 2 is immediately left of Piece 1
            } else if(row2 == row1 && col2 < col1) {
                // Piece 3 is under or above Piece 1
                if (col3 == col1) {
                    directionsToSearch.add(row3 > row1 ? Directions.DOWNRIGHT : Directions.UPLEFT);
                    // Piece 3 is above or below piece 2
                } else if (col3 == col2) {
                    directionsToSearch.add(Directions.RIGHT);
                    directionsToSearch.add(row3 < row2 ? Directions.UP : Directions.DOWN);
                    // Piece 3 is diagonally up or down from piece 3
                } else if (col3 < col2) {
                    directionsToSearch.add(row3 < row2 ? Directions.UPLEFT : Directions.DOWNLEFT);
                }
            // Piece 2 is diagonally down left of piece 1
            } else if (row2 > row1 && col2 < col1) {
                // Piece 3 directly above or below piece 2
                if (col3 == col2) {
                    directionsToSearch.add(row3< row2 ? Directions.UP : Directions.DOWN);
                // v shaped
                } else if (col3 < col2 && row3 < row1) {
                    directionsToSearch.add(Directions.UPLEFT);
                    directionsToSearch.add(Directions.UPRIGHT);
                // Piece 3 is left or right of piece 2
                } else if (row3 == row2) {
                    directionsToSearch.add(col3 < col2 ? Directions.LEFT : Directions.RIGHT);
                // < shaped
                } else if (col3 == col1){
                    directionsToSearch.add(Directions.UPRIGHT);
                    directionsToSearch.add(Directions.DOWNRIGHT);
                }
                // piece 2 is directly below piece 1
            } else if (col2 == col1) {
                // Piece 3 is left/right of piece 1
                if (row3 == row1){
                    directionsToSearch.add(col3 < col1 ? Directions.UPRIGHT : Directions.UPLEFT);
                // Piece 3 is left/right of piece 2
                } else if (row3 == row2){
                    directionsToSearch.add(Directions.UP);
                    directionsToSearch.add(col3 < col2 ? Directions.LEFT : Directions.RIGHT);
                // Piece 3Diagonally down-left/right from piece 2
                } else {
                    directionsToSearch.add(col3 < col2 ? Directions.DOWNLEFT : Directions.DOWNRIGHT);
                }
            // piece 2 diagonally downright of piece 1
            } else if (row2 > row1) {
                // Piece 3 above or below piece 2
                if (col3 == col2) {
                    directionsToSearch.add(row3 < row2 ? Directions.UP: Directions.DOWN);
                // v shaped
                } else if (row3 == row1) {
                    directionsToSearch.add(Directions.UPLEFT);
                    directionsToSearch.add(Directions.UPRIGHT);
                // piece 3 left or right of piece 2
                } else if (row3 == row2) {
                    directionsToSearch.add(col3<col2 ? Directions.LEFT : Directions.RIGHT);
                // > shaped
                } else{
                    directionsToSearch.add(Directions.UPLEFT);
                    directionsToSearch.add(Directions.DOWNRIGHT);
                }
            // Piece 2 right of piece 1
            } else if (row2 == row1) {
                // Piece 3 above or below piece 1
                if (col3==col1){
                    directionsToSearch.add(row3 < row1 ? Directions.UPLEFT : Directions.DOWNRIGHT);
                // Piece 3 above or below piece 2
                }else if (col3 == col2){
                    directionsToSearch.add(Directions.LEFT);
                    directionsToSearch.add(row3 < row2 ? Directions.UP : Directions.DOWN);
                // obtuse angle
                }else{
                    directionsToSearch.add(row3 < row2 ? Directions.UPRIGHT : Directions.DOWNRIGHT);
                }
            // Piece 2 up-right of piece 1
            } else {
                // Piece 3 is left or right of piece 2
                if (row3 == row2) {
                    directionsToSearch.add(col3<col2 ? Directions.LEFT : Directions.RIGHT);
                // > shaped
                } else if (col3 == col1){
                    directionsToSearch.add(Directions.UPLEFT);
                    directionsToSearch.add(Directions.DOWNLEFT);
                // piece 3 above or below piece 2
                } else if (col3 == col2) {
                    directionsToSearch.add(row3 < row2 ? Directions.UP : Directions.DOWN);
                // ^ shaped
                } else{
                    directionsToSearch.add(Directions.DOWNLEFT);
                    directionsToSearch.add(Directions.DOWNRIGHT);
                }
            }
            tupleSearchMap.put(tuple, directionsToSearch);
        }
        quadruples = this.generateQuadruples(tupleSearchMap);
        return quadruples;
    }

    // Ok, so, this takes the map that maps viable tuples to end directions to search and returns viable quadruple candidates
    private Set<List<Piece>> generateQuadruples(Map<List<Piece>, List<Directions>> tuples) {
        Set<List<Piece>> quadruples = new HashSet<>();
        // Loop through all the keys
        for (List<Piece> pieces : tuples.keySet()) {
            // We want to look starting from the end piece
            Piece endPiece = pieces.get(2);
            // We shoot rays in the appropriate directions for each end piece.
            // If we find a neighbor, we make and test a quadruple, then add it to the viable candidate list if it is viable
            for (Directions directions : tuples.get(pieces)) {
                List<Piece> quadrupleCandidate = new ArrayList<>();
                quadrupleCandidate.addAll(pieces);
                switch (directions) {
                    case UPLEFT:
                        if (findClosestUpLeft(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestUpLeft(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case UPRIGHT:
                        if (findClosestUpRight(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestUpRight(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case DOWNLEFT:
                        if (findClosestDownLeft(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestDownLeft(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case DOWNRIGHT:
                        if (findClosestDownRight(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestDownRight(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case LEFT:
                        if (findClosestLeft(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestLeft(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case RIGHT:
                        if (findClosestRight(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestRight(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case UP:
                        if (findClosestUp(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestUp(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    case DOWN:
                        if (findClosestDown(endPiece.getRow(), endPiece.getCol()) != null) {
                            quadrupleCandidate.add(findClosestDown(endPiece.getRow(), endPiece.getCol()));
                        }
                        break;
                    default:
                        break;
                }
                if (quadrupleCandidate.size() == 4 && isViableTuple(quadrupleCandidate)) {
                    quadruples.add(quadrupleCandidate);
                }
            }
        }
        return quadruples;
    }
}