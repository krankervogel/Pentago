package controller;

import view.PentagoGui;
import model.GameBoard;

public class Controller {

	private GameBoard board;
	private PentagoGui view;
	
	
	public Controller(GameBoard board, PentagoGui view){
		this.board = board;
		this.view = view;
	}
	
	private boolean wonCollection(int player, Pair<Integer, Integer>[] poss){
		boolean won = true;
		int size = poss.length;
		Pair<Integer, Integer> pair;
		for(int j = 1; j < size - 1; j++){
			pair = poss[j];
			won = won && board.getValue(pair.getFirst().intValue(), pair.getSecond().intValue()) == player;
		}
		
		return won && (board.getValue(poss[0].getFirst().intValue(), poss[0].getSecond().intValue()) == player
				    || board.getValue(poss[size - 1].getFirst().intValue(), poss[size - 1].getSecond().intValue()) == player);
	}
	
	private boolean lineWinner(int p, int l){
		Pair<Integer, Integer>[] poss = new Pair<Integer, Integer>[6];
	}
	
	private boolean wonTheLine(int player, int line){
		boolean won = true;
		for(int j=1; j<5; j++){
			won = won && board.getValue(line,j) == player;
		}
		return won && ((board.getValue(line,0)==player || board.getValue(line,5)==player));
	}
	
	private boolean wonTheColumn(int player, int col){
		boolean won = true;
		for(int j=1; j<5; j++){
			won = won && board.getValue(j,col) == player;
		}
		return won && ((board.getValue(0,col)==player || board.getValue(5,col)==player));
	}
	
//	private int[][] transposeMatrix(int[][] boardMatrix){
//		int rows = boardMatrix[0].length, cols = boardMatrix.length;
//		int[][] transposedMatrix = new int[rows][cols];
//		for(int r = 0; r < rows; r++){
//			for(int c = 0; c < rows; c++){
//				transposedMatrix[r][c] = boardMatrix[c][r];
//			}
//		}
//		return transposedMatrix;
//	}

	
	private boolean wonTheGame(int player){
		int[][] boardMatrix = board.getBoardMatrix();
		for(int i=0; i<6; i++){
			if (wonTheLine(player,i) || wonTheColumn(player,i))
				return true;
		}
		
		return false;
	}

}
