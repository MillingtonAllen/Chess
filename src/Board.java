/**
 * Represents a game board
 * 
 * @author Owner Allen Millington
 */

public class Board {
	
	private Stack<ChessPiece> stolenPieces = new Stack<ChessPiece>();
	private ChessPiece[][] chessBoard = new ChessPiece[8][8];
	
	private boolean check;
	private boolean checkmate; // is it a checkmate?
	
	/**
	 * Constructor
	 */
	public Board() {
		
		chessBoard[0][0] = new Rook(2);
		chessBoard[0][1] = new Knight(2);
		chessBoard[0][2] = new Bishop(2);
		chessBoard[0][3] = new Queen(2);
		chessBoard[0][4] = new King(2);
		chessBoard[0][5] = new Bishop(2);
		chessBoard[0][6] = new Knight(2);
		chessBoard[0][7] = new Rook(2);
		
		for (int i = 0; i < 8; i++) {
			chessBoard[1][i] = new Pawn(2);
		}
		
		chessBoard[7][0] = new Rook(1);
		chessBoard[7][1] = new Knight(1);
		chessBoard[7][2] = new Bishop(1);
		chessBoard[7][3] = new Queen(1);
		chessBoard[7][4] = new King(1);
		chessBoard[7][5] = new Bishop(1);
		chessBoard[7][6] = new Knight(1);
		chessBoard[7][7] = new Rook(1);
		
		for (int i = 0; i < 8; i++) {
			chessBoard[6][i] = new Pawn(1);
		}
		
	}
	
	/**
	 * Gets the piece at the desired position. Safe to return shallow copy because
	 * pieces have no changeable data fields.
	 * 
	 * @param x
	 *            X coordinate of desired piece
	 * @param y
	 *            Y coordinate of the desired piece
	 * @return The chess Piece itself
	 */
	public ChessPiece getPiece(int x, int y) {
		return chessBoard[x][y];
	}
	
	/**
	 * Returns who owns which piece
	 * 
	 * @return int array of who owns which piece
	 *         0 = empty
	 *         1 = player1
	 *         2 = player2
	 */
	public int[][] getPieceOwnerLayout() {
		
		int[][] board = new int[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				// if there is no piece, set to 0
				if (chessBoard[i][j] == null)
					board[i][j] = 0;
				else
					board[i][j] = chessBoard[i][j].getPlayer();
				
		}
		
		return board;
		
	}
	
	/**
	 * prints the Player Layout of the board to command line
	 * early testing of backend
	 */
	// CommandLine Testing Only
	public void printPieceOwnerLayout() {
		int[][] board = getPieceOwnerLayout();
		
		System.out.println("Board Piece Layout:");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
		
	}
	
	/**
	 * Moves a piece to the desired position
	 * 
	 * @param origX
	 *            the original x position of the piece
	 * @param origY
	 *            the original y position of the piece
	 * @param desiredX
	 *            the desired x position of the piece
	 * @param desiredY
	 *            the desired y position of the piece
	 * @param playerTurn
	 *            which player turn that is active
	 * @return Boolean whether the move is valid
	 */
	public boolean movePiece(int origX, int origY, int desiredX, int desiredY, int playerTurn) {
		
		int owner = 0;
		
		// sets player equal to the owner of the piece wanting to be moved
		if (chessBoard[origX][origY] != null)
			owner = chessBoard[origX][origY].getPlayer();
		
		// checks to see if the piece belongs to the player making the move
		if (owner != 0 && playerTurn == owner) {
			
			if (chessBoard[origX][origY].isValidMove(origX, origY, desiredX, desiredY, getPieceOwnerLayout())) {
				
				// stolen pieces, will also push null
				stolenPieces.push(chessBoard[desiredX][desiredY]);
				
				// Moves piece and sets original position = null
				chessBoard[desiredX][desiredY] = chessBoard[origX][origY];
				chessBoard[origX][origY] = null;
				
				if (!checkCheck(playerTurn)) {
					
					chessBoard[origX][origY] = null;
					stolenPieces.trimNull();
					return true;
					
				}
				else {
					System.out.println("You can't move a piece that allows a check");
				}
				
				// if the king is checked, the move must end the check.
				// the moved piece can not cause a check
				// if the player causes self a check, move his piece back and return any stolen
				// piece.
				chessBoard[origX][origY] = chessBoard[desiredX][desiredY];
				chessBoard[desiredX][desiredY] = stolenPieces.pop();
				
				return false;
				
			}
		}
		
		return false;
		
	}
	
	/**
	 * Promotes pawns in the enemy's home line to a Queen
	 */
	public void PromotePawn() {
		for (int i = 0; i < 8; i++) {
			
			// if pawn in enemy territory
			if (chessBoard[7][i] != null)
				if (chessBoard[7][i].getType().equals("p"))
					chessBoard[7][i] = (ChessPiece) new Queen(2); // change it to queen
					
			if (chessBoard[0][i] != null)
				if (chessBoard[0][i].getType().equals("p"))
					chessBoard[0][i] = (ChessPiece) new Queen(1);
				
		}
	}
	
	/**
	 * Sets a piece at the current position in the board.
	 * Used for moving pieces?
	 * Also used for testing purposes
	 * 
	 * @param x
	 *            the x coordinate of the piece to set
	 * @param y
	 *            the y coordinate of the piece to set
	 * @param piece
	 *            the piece you want to set at the given position
	 */
	public void setPiece(int x, int y, ChessPiece piece) {
		
		chessBoard[x][y] = piece;
	}
	
	/**
	 * Checks if it's a check
	 * 
	 * @param turn
	 *            the current player's turn
	 * @return boolean of true / false
	 */
	public boolean checkCheck(int turn) {
		
		// Originally, I figured I'd only needed to check the last moved piece.
		// Unfortunately, this is not true. If it was causing a collision before, other
		// pieces can now potentially reach the king.
		
		// Thinking about it, I can use King's isSafe method.
		
		// find the king
		
		int kingX = 0;
		int kingY = 0;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// if it's the player's king
				if (chessBoard[i][j] != null) {
					// if the piece is a king, and the piece belongs to the current player
					if (chessBoard[i][j].getType().equals("k") && chessBoard[i][j].getPlayer() == turn) {
						kingX = i;
						kingY = j;
						
						// Check to see if the player's king is safe
						
						// printPieceOwnerLayout();
						
						check = !King.isMoveSafe(kingX, kingY, getPieceOwnerLayout(), kingX, kingY);
						
						// If it's not safe
						if (check) {
							// assume it's a checkmate
							checkmate = true;
							
							// then prove that it's not a checkmate
							int[][] kingMoves = chessBoard[i][j].getMoves(i, j, getPieceOwnerLayout());
							for (int a = 0; a <= 7; a++) {
								for (int b = 0; b <= 7; b++) {
									if (kingMoves[a][b] != 0)
										checkmate = false;
								}
							}
						}
					}
				}
			}
		}
		return check;
		
	}
	
	/*
	 * 
	 * // find the opponent's opponent's king
	 * for (int i = 0; i <= 7; i++) {
	 * for (int j = 0; j <= 7; j++) {
	 * if (moves[i][j] == 2 && chessBoard[i][j].isKing() &&
	 * chessBoard[i][j].getPlayer() != turn) {
	 * 
	 * // checkmate is assumed true until we prove there isn't a checkmate
	 * checkmate = true;
	 * int[][] kingMoves = chessBoard[i][j].possibleMoves(i, j,
	 * getPieceOwnerLayout());
	 * for (int a = 0; a <= 7; a++) {
	 * for (int b = 0; b <= 7; b++) {
	 * if (kingMoves[a][b] != 0)
	 * checkmate = false;
	 * }
	 * }
	 * 
	 * return true;
	 * }
	 * }
	 * }
	 */
	
	/**
	 * Checks if it is a checkmate.
	 * Needs to be fixed 4_13
	 * 
	 * @param turn
	 *            The current player's turn
	 * @return True if checkmate
	 */
	// TODO fix me
	public boolean isCheckmate(int turn) {
		checkCheck(turn);
		return checkmate;
	}
	
	public int[][] getMovesGui(int x, int y) {
		
		return chessBoard[x][y].getMoves(x, y, getPieceOwnerLayout());
	}
}
