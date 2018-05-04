/**
 * Represents a rook chess piece
 * 
 * @author Owner Allen Millington
 */
public class Rook extends ChessPiece {
	
	public Rook(int player) {
		super(player);
		setPoints(5);
	}
	
	@Override
	public int[][] getMoves(int x, int y, int[][] boardLayout) {
		
		return moveCardinal(x, y, boardLayout);
		
	}
	
	@Override
	public String toString() {
		return ("Player " + getPlayer() + "'s Rook ");
	}
	
	public String getType() {
		return "r";
	}
}
