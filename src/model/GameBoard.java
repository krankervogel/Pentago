package model;

/**
 * @author sep
 * @author nda
 */

public class GameBoard {

	private Segment[][] board = new Segment[2][2];
	
	public static final int TOP = 0, BOTTOM = 1, LEFT = 0, RIGHT = 1;
	
	public GameBoard(){
		for (Segment[] segs : board){
			for (int i = 0; i < segs.length; i++) {
				segs[i] = new Segment();
			}
		}
	}
	
	public GameBoard(Segment[][] board) throws WrongBoardSizeException{
		if(board.length != 2 || board[0].length != 2|| board[1].length != 2)
			throw new WrongBoardSizeException("Illegal board size!");
		this.board = board;
	}
	
	public void setValue(int x, int y, int color){
		this.board[x/3][y/3].setFieldValue(x%3, y%3, color);
	}
	
	public int getValue(int x, int y){
		return this.board[x/3][y/3].getFieldValue(x%3, y%3);
	}
	
	public void rotateSegment(int x, int y, boolean counterClockwise){
		this.board[x][y].rotate(counterClockwise);
	}
	
	public int[][] getBoardMatrix(){
		int[][] boardMatrix = new int[6][6];
		for (int i=0; i<6; i++){
			for(int j=0; j<6 ;j++)
				boardMatrix[i][j] = getValue(i,j);
		}
		return boardMatrix;
	}

	private class Segment {

		public static final int WHITE = 1, BLACK = 2, UNSET = 0;

		private int[][] segment = new int[3][3];

		public Segment() {
		}

		public Segment(int[][] segment) {
			this.segment = segment;
		}

		public int getFieldValue(int x, int y) {
			return this.segment[x][y];
		}

		public void setFieldValue(int x, int y, int val) {
			this.segment[x][y] = val;
		}

		private void rotate(boolean dir) {
			int[][] newSeg = new int[3][3];

			// dir is the rotation direction, false means clockwise, true
			// counterclockwise
			if (dir) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						newSeg[i][j] = segment[2 - j][i];
					}
				}
			} else {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						newSeg[i][j] = segment[j][2 - i];
					}
				}
			}
			segment = newSeg;
		}
	}
}
