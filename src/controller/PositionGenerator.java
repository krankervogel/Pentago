package controller;

/**@author nda*/

public interface PositionGenerator {
    
    public static final int LINE = 0, COLUMN = 1, DIAGONAL_DOWN = 2, DIAGONAL_UP = 2;

    public Position makePosition(int i);
}
