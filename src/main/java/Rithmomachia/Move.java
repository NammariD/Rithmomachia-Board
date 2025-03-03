package main.java.Rithmomachia;

public class Move {
    private int startX, startY;
    private int endX, endY;

    public Move(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public String toString() {
        return String.format("Move from (%d, %d) to (%d, %d)", startX, startY, endX, endY);
    }

}