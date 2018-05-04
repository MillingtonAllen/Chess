/**
 * Represents a pawn chess piece
 * 
 * @author Owner Allen Millington
 */
public class Pawn extends ChessPiece {
	
	public Pawn(int player) {
		super(player);
		setPoints(1);
	}
	
	public int points = 3;
	
	public int[][] getMoves(int x, int y, int[][] boardLayout) {
		
		// 0 = not possible
		// 1 = Standard move
		// 2 = attack
		int[][] moves = moveForward(x, y, boardLayout);
		
		return moves;
	}
	
	public int[][] moveForward(int x, int y, int[][] boardLayout) {
		
		int[][] moves = new int[8][8]; // all positions initialized to 0
		int player = boardLayout[x][y]; // easier to remember
		
		if (player == 2 && x < 7) {
			
			// Can crash program with out of bounds, hence separate if
			if (boardLayout[x + 1][y] == 0) {
				
				// able to move forward 1
				moves[x + 1][y] = 1;
				
				// able to move forward 2
				if (x == 1 && boardLayout[x + 2][y] == 0)
					moves[x + 2][y] = 1;
			}
			
			// attack right
			if (y + 1 <= 7)
				if (boardLayout[x + 1][y + 1] == 1)
					moves[x + 1][y + 1] = 2;
				
			// attack left
			if (y - 1 >= 0)
				if (boardLayout[x + 1][y - 1] == 1)
					moves[x + 1][y - 1] = 2;
				
		}
		
		if (player == 1 && x > 0) {
			// out of bounds potential, hence separate if statement
			if (boardLayout[x - 1][y] == 0) {
				// able to move forward 1
				moves[x - 1][y] = 1;
				
				if (x == 6 && boardLayout[x - 2][y] == 0)
					moves[x - 2][y] = 1;
				
			}
			// attack right
			if (y + 1 <= 7)
				if (boardLayout[x - 1][y + 1] == 2)
					moves[x - 1][y + 1] = 2;
				
			// attack left
			if (y - 1 >= 0)
				if (boardLayout[x - 1][y - 1] == 2)
					moves[x - 1][y - 1] = 2;
		}
		return moves;
	}
	
	public String toString() {
		return ("Player " + getPlayer() + "'s Pawn ");
	}
	
	public String getType() {
		return "p";
	}
	
}
