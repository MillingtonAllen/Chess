/**
 * Represents moves for chess pieces
 * 
 * @author Owner Allen Millington
 */

public class PieceMove implements Comparable<PieceMove> {
	
	int pieceX, pieceY, moveX, moveY; // Original piece coordinates and desired move coordinates
	int points = 0; // point value of piece
	
	/**
	 * Constructor
	 * 
	 * @param pieceX
	 *            Piece's current X coordinate
	 * @param pieceY
	 *            Piece's current Y coordinate
	 * @param moveX
	 *            Desired X position
	 * @param moveY
	 *            Desired Y position
	 * @param points
	 *            point value of the piece
	 */
	public PieceMove(int pieceX, int pieceY, int moveX, int moveY, int points) {
		this.pieceX = pieceX;
		this.pieceY = pieceY;
		this.moveX = moveX;
		this.moveY = moveY;
		this.points = points;
	}
	
	public boolean equals(PieceMove incoming) {
		
		// If the piece coordinates and the point coordinates are equal
		if (this.pieceX == incoming.pieceX && this.pieceY == incoming.pieceY && this.moveX == incoming.moveX
				&& this.moveY == incoming.moveY)
			return true;
		
		return false;
		
	}
	
	/**
	 * Get X coordinate
	 * 
	 * @return X coordinate
	 */
	public int getPieceX() {
		return pieceX;
	}
	
	/**
	 * Get Y coordinate
	 * 
	 * @return Y coordinate
	 */
	
	public int getPieceY() {
		return pieceY;
	}
	
	/**
	 * Get X desired move coordinate
	 * 
	 * @return X desired move coordinate
	 */
	
	public int getMoveX() {
		return moveX;
	}
	
	/**
	 * Get Y desired move coordinate
	 * 
	 * @return Y desired move coordinate
	 */
	public int getMoveY() {
		return moveY;
	}
	
	/**
	 * The points of the piece
	 * 
	 * @return points of chess piece
	 */
	@SuppressWarnings("unused")
	public int getPoints() {
		return points;
	}
	
	/**
	 * Compare points of one piece to another piece
	 */
	@Override
	public int compareTo(PieceMove otherMove) {
		
		if (points > (otherMove.points)) {
			return 1;
		}
		else if (points < otherMove.points) {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Prints information about the chess piece
	 */
	public void print() {
		String output = "";
		
		output += "\nPiece Type: " + ChessGUI.boardGame.getPiece(pieceX, pieceY).getType();
		output += "\nOriginal Coordinates: " + pieceX + ", " + pieceY;
		output += "\nMove To Coordinates: " + moveX + ", " + moveY + "\n";
		
		System.out.println(output);
	}
	
}
