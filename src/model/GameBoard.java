package model;

/**
 * @author sep
 * @author nda
 */

public class GameBoard {

  public static final int SEGMENT_SIZE = 3, SEGMENTS = 2, 
                          DIMENSION = SEGMENT_SIZE * SEGMENTS;
  
	private Segment[][] board = new Segment[SEGMENTS][SEGMENTS];
	
	public static final int TOP = 0, BOTTOM = 1, LEFT = 0, RIGHT = 1;
	
	public GameBoard(){
		for (Segment[] segs : board){
			for (int i = 0; i < segs.length; i++) {
				segs[i] = new Segment();
			}
		}
	}
	
	public GameBoard(Segment[][] board) throws WrongBoardSizeException{
		if(board.length != SEGMENTS || board[TOP].length != SEGMENTS || board[BOTTOM].length != SEGMENTS)
			throw new WrongBoardSizeException("Illegal board size!");
		this.board = board;
	}
	
	public void setValue(int x, int y, int color){
		this.board[x/SEGMENT_SIZE][y/SEGMENT_SIZE].setFieldValue(x%SEGMENT_SIZE, y%SEGMENT_SIZE, color);
	}
	
	public int getValue(int x, int y){
		return this.board[x/SEGMENT_SIZE][y/SEGMENT_SIZE].getFieldValue(x%SEGMENT_SIZE, y%SEGMENT_SIZE);
	}
	
	public void rotateSegment(int x, int y, boolean counterClockwise){
		this.board[x][y].rotate(counterClockwise);
	}
	
	public int[][] getBoardMatrix(){
		int[][] boardMatrix = new int[DIMENSION][DIMENSION];
		for (int i=0; i<DIMENSION; i++){
			for(int j=0; j<DIMENSION ;j++)
				boardMatrix[i][j] = getValue(i,j);
		}
		return boardMatrix;
	}

	private class Segment {

		public static final int WHITE = 1, BLACK = 2, UNSET = 0;

		private int[][] segment = new int[SEGMENT_SIZE][SEGMENT_SIZE];

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
			int[][] newSeg = new int[SEGMENT_SIZE][SEGMENT_SIZE];

			// dir is the rotation direction, false means clockwise, true
			// counterclockwise
			if (dir) {
				for (int i = 0; i < SEGMENT_SIZE; i++) {
					for (int j = 0; j < SEGMENT_SIZE; j++) {
						newSeg[i][j] = segment[SEGMENT_SIZE - 1 - j][i];
					}
				}
			} else {
				for (int i = 0; i < SEGMENT_SIZE; i++) {
					for (int j = 0; j < SEGMENT_SIZE; j++) {
						newSeg[i][j] = segment[j][SEGMENT_SIZE - 1 - i];
					}
				}
			}
			segment = newSeg;
		}
	}
}
