package main.java.Rithmomachia;

public class Pos {
    int row;
    int col;

    public Pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "[" + row + "," + col + "]";
    }




}