/**
 * Represents a king Chess Piece
 * Currently isSafe ignores enemy king due to circular logic
 * 
 * @author Owner Allen Millington
 */
public class King extends ChessPiece {
	
	public King(int player) {
		super(player);
		setPoints(100);
	}
	
	@Override
	public int[][] getMoves(int x, int y, int[][] boardLayout) {
		
		int outputMoves[][] = new int[8][8];
		
		int diagonal[][] = moveDiagonal(x, y, boardLayout);
		int cardinal[][] = moveCardinal(x, y, boardLayout);
		
		int i = x - 1;
		int j = y - 1;
		
		// trim movement b/c king can only move one space at a time
		while (i <= x + 1) {
			while (j <= y + 1) {
				
				if (i <= 7 && i >= 0 && j <= 7 && j >= 0) {
					if (isMoveSafe(i, j, boardLayout, x, y)) {
						
						if (diagonal[i][j] == 1) {
							outputMoves[i][j] = 1;
						}
						else if (diagonal[i][j] == 2) {
							outputMoves[i][j] = 2;
						}
						else if (cardinal[i][j] == 1) {
							outputMoves[i][j] = 1;
						}
						else if (cardinal[i][j] == 2) {
							outputMoves[i][j] = 2;
						}
					}
				}
				j++;
				
			}
			j = y - 1;
			i++;
		}
		
		// Your move can not cause yourself to be in a check
		// to test, move the king each place and see if under attack
		
		/*
		 * 
		 * // parse entire board
		 * for (int a = 0; a <= 7; a++) {
		 * for (int b = 0; b <= 7; b++) {
		 * // if the space isn't empty, and if the piece at the position does not belong
		 * to
		 * // the current player
		 * if (boardLayout[a][b] != 0 && boardLayout[a][b] != boardLayout[x][y]) {
		 * ChessPiece opponentPiece = Game.boardGame.getPiece(a, b);
		 * 
		 * // remove king from board (temporarily) not counted as a collision (thus
		 * // allowing illegal moves)
		 * int[][] boardNoKing = boardLayout;
		 * 
		 * boardNoKing[x][y] = 0;
		 * 
		 * int[][] opponentMoves = opponentPiece.possibleMoves(a, b, boardNoKing);
		 * 
		 * 
		 * // King not allowed to move into current attack zones
		 * for (int c = 0; c <= 7; c++) {
		 * for (int d = 0; d <= 7; d++) {
		 * //if the king can currently move there, and (the opponent's piece can attack
		 * if (moves[c][d] != 0 && (opponentMoves[c][d] == 1 || opponentMoves[c][d] ==
		 * 2)) {
		 * moves[c][d] = 0;
		 * 
		 * if (
		 * }
		 * }
		 * }
		 */
		return outputMoves;
		
	}
	
	/**
	 * Checks if the king's current move is safe. Unfortunately, I hit circular
	 * logic, so the king is excluded for now (causes weird behavior)
	 * 
	 * @param desiredX
	 *            The desired X position
	 * @param desiredY
	 *            The desired Y position
	 * @param boardLayout
	 *            Who owns which piece
	 * @param origX
	 *            The original X position of the King
	 * @param origY
	 *            The original Y position of the King
	 * @return
	 */
	public static boolean isMoveSafe(int desiredX, int desiredY, int[][] boardLayout, int origX, int origY) {
		
		// Ok, so we know where the piece is going to be.
		// We need to check each enemy piece on the board to see if it is capable of
		// attacking
		
		// remove king from board (temporarily) so not counted as a collision
		
		// player that is moving the piece
		int playerMovingPiece = boardLayout[origX][origY];
		// piece located at the desired spot (either 0 empty or opponent's #)
		int pieceAtDesired = boardLayout[desiredX][desiredY];
		
		// Erase king's current position to avoid collisions
		boardLayout[origX][origY] = 0;
		
		// Make the desired position belong to the current player
		// allows us to run opponent attack on that space
		boardLayout[desiredX][desiredY] = playerMovingPiece;
		
		// So we start by looking at the board Layout to find enemy pieces
		for (int a = 0; a <= 7; a++) {
			for (int b = 0; b <= 7; b++) {
				// if the space isn't empty, and if the piece at the position does not belong to
				// the current player
				if (boardLayout[a][b] != 0 && boardLayout[a][b] != playerMovingPiece) {
					
					// get the enemy piece at the current board position
					ChessPiece opponentPiece = ChessGUI.boardGame.getPiece(a, b);
					// find possible moves of said piece
					
					int[][] opponentMoves = new int[8][8];
					
					// FIXME This will break king movement when next to each other
					// Needed to prevent circular calls between functions
					if (!opponentPiece.getType().equals("k"))
						opponentMoves = opponentPiece.getMoves(a, b, boardLayout);
					
					// If the opponent can attack the king's desired position
					if (opponentMoves[desiredX][desiredY] == 2) {
						// set the king to the board at the original position & set piece at desired
						// location to original owner
						boardLayout[origX][origY] = playerMovingPiece;
						boardLayout[desiredX][desiredY] = pieceAtDesired;
						
						return false;
						
					}
				}
			}
		}
		// set the king to the board at the original position & set piece at desired
		// location to original owner
		boardLayout[origX][origY] = playerMovingPiece;
		boardLayout[desiredX][desiredY] = pieceAtDesired;
		
		return true;
		
	}
	
	@Override
	public String toString() {
		return ("Player " + getPlayer() + "'s King ");
	}
	
	public String getType() {
		return "k";
	}
	
}
