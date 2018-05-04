/**
 * Represents a chess piece
 * 
 * @author Owner Allen Millington
 */
public abstract class ChessPiece {
	
	// Pawn 1, Knight 3, Rook 5, Queen 9, king 100
	
	private int player;
	
	protected int points;
	
	/**
	 * Generic chess piece
	 * 
	 * @param player
	 *            The player that the piece belongs to
	 */
	public ChessPiece(int player) {
		this.player = player;
	}
	
	/**
	 * Returns the possible moves
	 * 
	 * @param x
	 *            current X position of the piece
	 * @param y
	 *            current Y position of the piece
	 * @param boardLayout
	 *            the layout of the board.
	 *            0 = empty
	 *            1 = player1
	 *            2 = player2
	 * @return int array of possible moves
	 *         0 = not a valid move
	 *         1 = valid move
	 *         2 = attack
	 */
	public abstract int[][] getMoves(int x, int y, int[][] getPieceOwnerLayout);
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Get the player that owns the piece
	 * 
	 * @return the player that owns the piece
	 */
	public int getPlayer() {
		return this.player;
	}
	
	/**
	 * Determines if a move is valid
	 * 
	 * @param origX
	 *            the X coordinate of the piece to move
	 * @param origY
	 *            the Y coordinate of the piece to move
	 * @param desiredX
	 *            The desired X location
	 * @param desiredY
	 *            The desired Y location
	 * @param boardLayout
	 *            int array of who owns which piece
	 *            0 = empty
	 *            1= player1
	 *            2= player2
	 * @return the validity of the move
	 */
	public boolean isValidMove(int origX, int origY, int desiredX, int desiredY, int[][] boardLayout) {
		int[][] availableMoves = getMoves(origX, origY, boardLayout);
		
		// if the spot is open, or if the attack is valid
		if (availableMoves[desiredX][desiredY] == 1 || availableMoves[desiredX][desiredY] == 2)
			return true;
		
		return false;
	}
	
	/**
	 * Attack pattern for diagonal moves
	 * 
	 * @param origX
	 *            X location of the piece
	 * @param origY
	 *            Y location of the piece
	 * @param boardLayout
	 *            int array of who owns which piece
	 *            0 = empty
	 *            1 = player1
	 *            2= player2
	 * @return the attack pattern for diagonal moves
	 *         0 = invalid move
	 *         1 = move to empty spot
	 *         2 = attack
	 */
	public int[][] moveDiagonal(int origX, int origY, int[][] boardLayout) {
		
		// 0 = not possible
		// 1 = Standard move
		// 2 = attack
		int[][] moves = new int[8][8]; // all positions initialized to 0
		int player = boardLayout[origX][origY]; // easier to remember
		
		boolean collision = false;
		
		// preserving initial inputs
		int x = origX;
		int y = origY;
		
		// Bottom Right Diagonal
		while (!collision && x < 7 && y < 7) {
			x++;
			y++;
			
			// System.out.println(boardLayout[x][y]);
			// System.out.println(player);
			// Standard Move
			if (boardLayout[x][y] == 0) {
				moves[x][y] = 1;
			}
			// Player's own Piece in the Way
			else if (boardLayout[x][y] == player) {
				collision = true;
			}
			
			// Player's Piece can attack
			else {
				
				moves[x][y] = 2;
				collision = true;
				
			}
			
		}
		
		x = origX;
		y = origY;
		collision = false;
		
		// While there isn't a collision, and the piece is within bounds
		while (!collision && x > 0 && y < 7) {
			x--;
			y++;
			
			// Standard Move
			if (boardLayout[x][y] == 0) {
				moves[x][y] = 1;
			}
			// Player's own Piece in the Way
			else if (boardLayout[x][y] == player) {
				collision = true;
			}
			
			// Player's Piece can attack
			else {
				moves[x][y] = 2;
				collision = true;
				
			}
		}
		
		x = origX;
		y = origY;
		collision = false;
		
		while (!collision && x < 7 && y > 0) {
			x++;
			y--;
			
			// Standard Move
			if (boardLayout[x][y] == 0) {
				moves[x][y] = 1;
			}
			
			// Player's own Piece in the Way
			else if (boardLayout[x][y] == player) {
				collision = true;
			}
			
			// Player's Piece can attack
			else {
				moves[x][y] = 2;
				collision = true;
				
			}
		}
		
		x = origX;
		y = origY;
		collision = false;
		
		while (!collision && x > 0 && y > 0)
		
		{
			x--;
			y--;
			
			// Standard Move
			if (boardLayout[x][y] == 0) {
				moves[x][y] = 1;
			}
			
			// Player's own Piece in the Way
			else if (boardLayout[x][y] == player) {
				collision = true;
			}
			
			// Player's Piece can attack
			else {
				moves[x][y] = 2;
				collision = true;
				
			}
		}
		
		x = origX;
		y = origY;
		collision = false;
		return moves;
		
	}
	
	/**
	 * Print the possible moves of a piece
	 * 
	 * @param x
	 *            the X position of the piece
	 * @param y
	 *            the Y position of th piece
	 * @param boardLayout
	 *            int array of who owns which piece
	 *            0 = empty
	 *            1= player1
	 *            2= player2
	 */
	public void printMoves(int x, int y, int[][] boardLayout) {
		
		// get possible moves of the piece
		int[][] getPieceMoves = getMoves(x, y, boardLayout);
		
		// Print attack pattern
		System.out.println("Attack Pattern:");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(getPieceMoves[i][j]);
			}
			System.out.println("");
		}
		
	}
	
	/**
	 * Calculates cardinal direction moves (Up, Down, Left, Right)
	 * 
	 * @param x
	 *            current X location
	 * @param y
	 *            current Y location
	 * @param boardLayout
	 *            int array of who owns which piece
	 *            0 = empty
	 *            1= player1
	 *            2= player2
	 * @return int array of possible moves
	 *         0 = not a valid move
	 *         1 = valid move
	 *         2 = attack
	 */
	public int[][] moveCardinal(int x, int y, int[][] boardLayout) {
		boolean collision = false;
		int counter = 1;
		int moves[][] = new int[8][8];
		
		// move right
		while (!collision && (y + counter <= 7)) {
			
			// player != player off desired piece (or if the spot is empty)
			if (boardLayout[x][y] != boardLayout[x][y + counter])
				// If the position is open
				if (boardLayout[x][y + counter] == 0)
					moves[x][y + counter] = 1; // assign move to open spot
				else {
					moves[x][y + counter] = 2; // Attack opponent piece
					collision = true;
				}
			else {
				// Else, there is a collision with your own piece
				collision = true;
			}
			counter++;
		}
		
		// resetting variables for other directions of movement
		collision = false;
		counter = 1;
		
		// move left
		while (!collision && (y - counter >= 0)) {
			
			// check owner
			if (boardLayout[x][y] != boardLayout[x][y - counter])
				if (boardLayout[x][y - counter] == 0)
					moves[x][y - counter] = 1;
				else {
					moves[x][y - counter] = 2;
					collision = true;
				}
			else {
				collision = true;
			}
			counter++;
		}
		
		collision = false;
		counter = 1;
		
		// move down
		while (!collision && (x + counter <= 7)) {
			
			// check owner
			if (boardLayout[x][y] != boardLayout[x + counter][y])
				if (boardLayout[x + counter][y] == 0)
					moves[x + counter][y] = 1;
				else {
					moves[x + counter][y] = 2;
					collision = true;
				}
			else {
				collision = true;
			}
			counter++;
		}
		
		collision = false;
		counter = 1;
		
		// move up
		while (!collision && (x - counter >= 0)) {
			
			// check owner
			if (boardLayout[x][y] != boardLayout[x - counter][y])
				if (boardLayout[x - counter][y] == 0)
					moves[x - counter][y] = 1;
				else {
					moves[x - counter][y] = 2;
					collision = true;
				}
			else {
				collision = true;
			}
			counter++;
		}
		
		return moves;
	}
	
	/**
	 * Returns the type of the piece (Used for generating Icon filenames).
	 * b = Bishop
	 * k = King
	 * h = Rook (horse)
	 * p = Pawn
	 * q = queen
	 * r = rook
	 */
	public abstract String toString();
	
	public abstract String getType();
	
	public int getPoints() {
		return points;
	}
	
}
