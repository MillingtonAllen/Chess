/**
 * Represents a knight chess piece
 * 
 * @author Owner Allen Millington
 */

public class Knight extends ChessPiece {
	
	public Knight(int player) {
		super(player);
		setPoints(3);
	}
	
	@Override
	public int[][] getMoves(int x, int y, int[][] boardLayout) {
		
		int moves[][] = new int[8][8];
		
		/*
		 * format
		 * check x boundary
		 * check y boundary
		 * ensure not same owner for original piece & target
		 * if (boardLayout[x][y] != boardLayout[x + 2][y + 1])
		 * if space is free (==0), set move
		 * else, set to attack
		 */
		
		// Checks each of 8 potential moves
		if (x + 2 <= 7) {
			if (y + 1 <= 7) {
				// checking if same owner
				if (boardLayout[x][y] != boardLayout[x + 2][y + 1]) {
					if (boardLayout[x + 2][y + 1] == 0)
						moves[x + 2][y + 1] = 1;
					else
						moves[x + 2][y + 1] = 2;
				}
			}
			if (y - 1 >= 0) {
				// checking if same owner
				if (boardLayout[x][y] != boardLayout[x + 2][y - 1]) {
					if (boardLayout[x + 2][y - 1] == 0)
						moves[x + 2][y - 1] = 1;
					else
						moves[x + 2][y - 1] = 2;
				}
			}
		}
		
		if (x + 1 <= 7) {
			if (y + 2 <= 7) {
				if (boardLayout[x][y] != boardLayout[x + 1][y + 2]) {
					if (boardLayout[x + 1][y + 2] == 0)
						moves[x + 1][y + 2] = 1;
					else
						moves[x + 1][y + 2] = 2;
				}
				
			}
			if (y - 2 >= 0) {
				if (boardLayout[x][y] != boardLayout[x + 1][y - 2]) {
					if (boardLayout[x + 1][y - 2] == 0)
						moves[x + 1][y - 2] = 1;
					else
						moves[x + 1][y - 2] = 2;
					
				}
				
			}
			
		}
		
		if (x - 1 >= 0) {
			if (y - 2 >= 0) {
				if (boardLayout[x][y] != boardLayout[x - 1][y - 2]) {
					if (boardLayout[x - 1][y - 2] == 0)
						moves[x - 1][y - 2] = 1;
					else
						moves[x - 1][y - 2] = 2;
					
				}
				
			}
			if (y + 2 <= 7) {
				if (boardLayout[x][y] != boardLayout[x - 1][y + 2]) {
					if (boardLayout[x - 1][y + 2] == 0)
						moves[x - 1][y + 2] = 1;
					else
						moves[x - 1][y + 2] = 2;
				}
				
			}
		}
		if (x - 2 >= 0) {
			if (y - 1 >= 0) {
				// checking if same owner
				if (boardLayout[x][y] != boardLayout[x - 2][y - 1]) {
					if (boardLayout[x - 2][y - 1] == 0)
						moves[x - 2][y - 1] = 1;
					else
						moves[x - 2][y - 1] = 2;
				}
			}
			if (y + 1 <= 7) {
				// checking if same owner
				if (boardLayout[x][y] != boardLayout[x - 2][y + 1]) {
					if (boardLayout[x - 2][y + 1] == 0)
						moves[x - 2][y + 1] = 1;
					else
						moves[x - 2][y + 1] = 2;
				}
			}
			
		}
		
		return moves;
	}
	
	public String toString() {
		return ("Player " + getPlayer() + "'s Knight ");
	}
	
	public String getType() {
		return "h"; // h for horse 0.0
	}
	
}
