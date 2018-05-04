/**
 * Represents a bishop chess piece
 * 
 * @author Owner: Allen Millington
 */
public class Bishop extends ChessPiece {
	
	public Bishop(int player) {
		super(player);
		setPoints(4);
	}
	
	@Override
	public int[][] getMoves(int origX, int origY, int[][] boardLayout) {
		
		return moveDiagonal(origX, origY, boardLayout);
		
	}
	
	public String toString() {
		return ("Player " + getPlayer() + "'s Bishop ");
	}
	
	public String getType() {
		return "b";
	}
	
}
