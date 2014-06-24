package controller;

import view.PentagoGui;
import model.GameBoard;

import java.util.LinkedList;

public class Controller {

    private GameBoard  board;
    private PentagoGui view;

    public Controller(GameBoard board, PentagoGui view) {
        this.board = board;
        this.view = view;
    }

    private boolean or(LinkedList<Boolean> bools) {
        boolean res = false;
        for (Boolean b : bools) {
            res = res || b.booleanValue();
            if (res)
                break;
        }
        return res;
    }

    private boolean and(LinkedList<Boolean> bools) {
        boolean res = true;
        for (Boolean b : bools) {
            res = res && b.booleanValue();
            if (!res)
                break;
        }
        return res;
    }

    private boolean wonCollection(int player, LinkedList<Position> mandatory, LinkedList<Position> optional) {
        LinkedList<Boolean> manBool = new LinkedList<Boolean>(), optBool = new LinkedList<Boolean>();
        for (Position pos : mandatory)
            manBool.add(new Boolean(board.getValue(pos.getX(), pos.getY()) == player));
        for (Position pos : optional)
            optBool.add(new Boolean(board.getValue(pos.getX(), pos.getY()) == player));
        return and(manBool) && (optBool.isEmpty() || or(optBool));
    }

    private LinkedList<Position> mandatory(PositionGenerator pg) {
        LinkedList<Position> list = new LinkedList<Position>();
        for (int i = 1; i < GameBoard.DIMENSION - 1; i++) {
            list.add(pg.makePosition(i));
        }
        return list;
    }

    private LinkedList<Position> lineMandatory(final int line) {
        PositionGenerator pg = new PositionGenerator() {
            public Position makePosition(int i) {
                return new Position(line, i);
            }
        };
        return mandatory(pg);
    }

    private LinkedList<Position> lineOptional(int line) {
        LinkedList<Position> list = new LinkedList<Position>();
        list.add(new Position(line, 0));
        list.add(new Position(line, GameBoard.DIMENSION - 1));
        return list;
    }

    private LinkedList<Position> columnMandatory(final int column) {
        PositionGenerator pg = new PositionGenerator() {
            public Position makePosition(int i) {
                return new Position(i, column);
            }
        };
        return mandatory(pg);
    }

    private LinkedList<Position> columnOptional(int column) {
        LinkedList<Position> list = new LinkedList<Position>();
        list.add(new Position(0, column));
        list.add(new Position(GameBoard.DIMENSION - 1, column));
        return list;
    }

    private LinkedList<Position> diagonalDown(final boolean lower) {
        PositionGenerator pg = new PositionGenerator() {
            public Position makePosition(int i) {
                return new Position(lower ? i + 1 : i, lower ? i : i + 1);
            }
        };
        return mandatory(pg);
    }

    private LinkedList<Position> diagonalUp(final boolean lower) {
        PositionGenerator pg = new PositionGenerator() {
            public Position makePosition(int i) {
                return new Position(lower ? i + 1 : i, lower ? GameBoard.DIMENSION - 1 - i : GameBoard.DIMENSION - i - 2);
            }
        };
        return mandatory(pg);
    }

    private LinkedList<Position> diagonalDownMandatory() {
        PositionGenerator pg = new PositionGenerator() {
            public Position makePosition(int i) {
                return new Position(i, i);
            }
        };
        return mandatory(pg);
    }

    private LinkedList<Position> diagonalDownOptional() {
        LinkedList<Position> list = new LinkedList<Position>();
        list.add(new Position(0, 0));
        list.add(new Position(GameBoard.DIMENSION - 1, GameBoard.DIMENSION - 1));
        return list;
    }

    private LinkedList<Position> diagonalUpMandatory() {
        PositionGenerator pg = new PositionGenerator() {
            public Position makePosition(int i) {
                return new Position(i, GameBoard.DIMENSION - 1 - i);
            }
        };
        return mandatory(pg);
    }

    private LinkedList<Position> diagonalUpOptional() {
        LinkedList<Position> list = new LinkedList<Position>();
        list.add(new Position(GameBoard.DIMENSION - 1, 0));
        list.add(new Position(0, GameBoard.DIMENSION - 1));
        return list;
    }

    public boolean wonTheGame(int player) {
        LinkedList<Boolean> tests = new LinkedList<Boolean>();
        LinkedList<Position> empty = new LinkedList<Position>();
        for (int i = 0; i < GameBoard.DIMENSION; i++) {
            tests.add(new Boolean(wonCollection(player, lineMandatory(i),
                    lineOptional(i))));
            tests.add(new Boolean(wonCollection(player, columnMandatory(i),
                    columnOptional(i))));
        }

        tests.add(new Boolean(wonCollection(player, diagonalDownMandatory(),
                diagonalDownOptional())));
        tests.add(new Boolean(wonCollection(player, diagonalUpMandatory(),
                diagonalUpOptional())));

        for (boolean b = false; !b; b = !b) {
            tests.add(new Boolean(wonCollection(player, diagonalDown(b), empty)));
            tests.add(new Boolean(wonCollection(player, diagonalUp(b), empty)));
        }

        return or(tests);
    }

    // private boolean wonCollection(int player, Pair<Integer, Integer>[] poss)
    // {
    // boolean won = true;
    // int size = poss.length;
    // Pair<Integer, Integer> pair;
    // for (int j = 1; j < size - 1; j++) {
    // pair = poss[j];
    // won = won
    // && board.getValue(pair.getFirst().intValue(), pair
    // .getSecond().intValue()) == player;
    // }
    //
    // return won
    // && (board.getValue(poss[0].getFirst().intValue(), poss[0]
    // .getSecond().intValue()) == player || board.getValue(
    // poss[size - 1].getFirst().intValue(), poss[size - 1]
    // .getSecond().intValue()) == player);
    // }

    // private boolean lineWinner(int p, int l){
    // Pair<Integer, Integer>[] poss = new Pair<Integer, Integer>[6];
    // }
    //
    // private boolean wonTheLine(int player, int line) {
    // boolean won = true;
    // for (int j = 1; j < 5; j++) {
    // won = won && board.getValue(line, j) == player;
    // }
    // return won
    // && ((board.getValue(line, 0) == player || board.getValue(line,
    // 5) == player));
    // }
    //
    // private boolean wonTheColumn(int player, int col) {
    // boolean won = true;
    // for (int j = 1; j < 5; j++) {
    // won = won && board.getValue(j, col) == player;
    // }
    // return won
    // && ((board.getValue(0, col) == player || board.getValue(5, col) ==
    // player));
    // }

    // private int[][] transposeMatrix(int[][] boardMatrix){
    // int rows = boardMatrix[0].length, cols = boardMatrix.length;
    // int[][] transposedMatrix = new int[rows][cols];
    // for(int r = 0; r < rows; r++){
    // for(int c = 0; c < rows; c++){
    // transposedMatrix[r][c] = boardMatrix[c][r];
    // }
    // }
    // return transposedMatrix;
    // }

    // private boolean wonTheGame(int player) {
    // int[][] boardMatrix = board.getBoardMatrix();
    // for (int i = 0; i < 6; i++) {
    // if (wonTheLine(player, i) || wonTheColumn(player, i))
    // return true;
    // }
    //
    // return false;
    // }

}
