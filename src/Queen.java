/**
 * represents a queen chess piece
 * 
 * @author Owner Allen Millington
 */
public class Queen extends ChessPiece {
	
	public Queen(int player) {
		super(player);
		setPoints(10);
	}
	
	@Override
	public int[][] getMoves(int x, int y, int[][] boardLayout) {
		
		int outputMoves[][] = new int[8][8];
		
		// Really convent that I was able to reuse the methods by moving to Chess Piece
		// Class
		
		int diagonal[][] = moveDiagonal(x, y, boardLayout);
		int caridnal[][] = moveCardinal(x, y, boardLayout);
		
		// Take the best of both to see where it can move
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				
				if (diagonal[i][j] == 1) {
					outputMoves[i][j] = 1;
				}
				else if (diagonal[i][j] == 2) {
					outputMoves[i][j] = 2;
				}
				else if (caridnal[i][j] == 1) {
					outputMoves[i][j] = 1;
				}
				else if (caridnal[i][j] == 2) {
					outputMoves[i][j] = 2;
				}
				
			}
		}
		return outputMoves;
	}
	
	public String toString() {
		return ("Player " + getPlayer() + "'s Queen ");
	}
	
	public String getType() {
		return "q";
	}
	
}
