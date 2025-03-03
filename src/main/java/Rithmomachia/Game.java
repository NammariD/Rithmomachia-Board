package main.java.Rithmomachia;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        // Create a board and initialize it
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of rows: ");
            int numRows = scanner.nextInt();
            System.out.print("Enter number of columns: ");
            int numCols = scanner.nextInt();
            //Board.initBoard(numRows, numCols);
        }

        // Create and place Square pieces
        //Square square = new Square(Color.B, 1);
        //Circle circle = new Circle(Color.B, 1);
        //new Circle(Color.B, 1);

        //Board.setPiece(square.getX(), square.getY(), square);  // Place square on the board
        //Board.setPiece(circle.getX(), circle.getY(), circle);  // Place circle on the board

        // Print the board
        //Board.printBoard();

        // Call findMoves method
        /*Set<Move> moves = square.findMoves(square.getX(), square.getY(), new Board());

        // Print the moves for square1
        System.out.println("Possible Moves for Square at (4, 4):");
        for (Move move : moves) {
            System.out.println(move);
        }

        // Capture function for square1
        Set<Pos> captures = square.capture(square.getX(), square.getY(), new Board());

        // Print captured positions
        System.out.println("Can Capture at (4, 4):");
        for (Pos capture : captures) {
            System.out.println(capture);
        }*/
    }
}