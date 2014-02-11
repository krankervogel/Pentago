package model;

/**
 * @author sep
 * @author nda
 */

public class GameBoard {

	private class Segment {
		
		public static final int WHITE = 1, BLACK = 2, UNSET = 0;

		private int[][] segment = new int[3][3];

		public Segment() {
		}

		public Segment(int[][] segment) {
			this.segment = segment;
		}
		
		public int getFieldValue(int x, int y){
			return this.segment[x][y];
		}
		public void setFieldValue(int x, int y, int val){
			this.segment[x][y] = val;
		}
		private void rotate(boolean dir){
			int[][] newSeg = new int[3][3];
			
			// dir is the rotation direction, false means clockwise, true counterclockwise
			if (dir) {
				for (int i = 0;i<3; i++){
					for (int j = 0;j<3;j++){
						newSeg[i][j] =  segment [2-j][i];
					}
				}
			}
			else{
				for (int i = 0;i<3; i++){
					for (int j = 0;j<3;j++){
						newSeg[i][j] =  segment [j][2-i];
						}
					}
				}
			segment = newSeg;
		}
		public void rotateLeft(){
			rotate(true);
		}
		public void rotateRight(){
			rotate(false);
		}
				
				
		

	}
}
