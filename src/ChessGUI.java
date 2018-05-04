
/**
 * Chess Board Game - MAIN METHOD
 * @author Owner Allen Millington
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;

//import javax.swing.JButton;
//import java.util.List;
//import java.util.ArrayList;

// The thoughts going through my head when documenting this and thinking about it's efficiency
// https://www.youtube.com/watch?v=ZBpWdwXzpMk
// I Cry (Just A Little)
// Though I always like Flo Rida's Remix more
// https://www.youtube.com/watch?v=OLuWHr6-0YQ
public class ChessGUI {
	
	static int turn = 1; // Alternates between 1 and 2. 0 is used to indicate empty spaces on the board.
							// The turn corresponds to the piece owner field.
	static boolean clickCounter = false; // Used to determine if the the player has clicked once. Used to move pieces.
	static int lastX, lastY; // Stores the position of the last click
	
	public static Board boardGame = new Board(); // Creates a new board object.
	public static ButtonGrid buttons; // Makes a button grid object (internally, button grid is an array)
	
	private JFrame frame;
	
	// static JButton[][] buttons = new JButton[8][8];
	
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		/*
		 * PieceMove a = new PieceMove(0, 0, 0, 0, 5);
		 * PieceMove b = new PieceMove(0, 0, 0, 0, 10);
		 * System.out.println(a.compareTo(b));
		 */
		
		// System.out.println(8 % 8 + "\n");
		
		// int x = 7;
		// int y = 1;
		
		// boardGame.setPiece(x, y, new Knight(1));
		/*
		 * boardGame.setPiece(2, 1, new Rook(2));
		 * boardGame.setPiece(1, 7, new Rook(2));
		 * 
		 * boardGame.printPieceOwnerLayout();
		 */
		// showMove(x, y);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					// ChessGUI window = new ChessGUI();
					buttons = new ButtonGrid(8, 8);
					
					drawBoard();
					
					// GridButtonPanel buttons = new GridButtonPanel();
					
					// window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	public static void clicked(int x, int y) {
		// boardGame.checkCheck(turn);
		drawBoard();
		// moveEnemy();
		
		// If the board hasn't been clicked yet
		if (!clickCounter) {
			// Draw the moves for the current clicked position. If the position is empty,
			// drawmoves returns false.
			clickCounter = drawMoves(x, y, turn);
			lastX = x; // Stores the values of the last click
			lastY = y;
			
		}
		else {
			// Using values from the last click, attempt to move the piece to the new
			// clicked location.
			if (boardGame.movePiece(lastX, lastY, x, y, turn)) {
				
				/*
				 * try {
				 * Thread.sleep(3000);
				 * } catch (InterruptedException e) {
				 * // TODO Auto-generated catch block
				 * e.printStackTrace();
				 * }
				 */
				changeTurn();
				moveEnemy();
				
			}
			clickCounter = !clickCounter; // Reset click counter
			boardGame.PromotePawn(); // check to promote pawns
			drawBoard(); // redraw the board
			
		}
		
	}
	
	/**
	 * if player 1's turn, change to player 2's turn
	 * if player 2's turn, change to player 1's turn
	 */
	private static void changeTurn() {
		if (turn == 1)
			turn = 2;
		else
			turn = 1;
	}
	
	/**
	 * Draw moves
	 * 
	 * @param x
	 *            the x position of the piece
	 * @param y
	 *            the y position of the piece
	 * @param turn
	 *            the current player's turn
	 * @return Successful draw (piece is not null and piece belongs to player) true
	 */
	private static boolean drawMoves(int x, int y, int turn) {
		drawBoard();
		ChessPiece temp = null;
		temp = boardGame.getPiece(x, y);
		
		if (temp != null) {
			
			if (turn == temp.getPlayer()) {
				
				int[][] moves = temp.getMoves(x, y, boardGame.getPieceOwnerLayout());
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						String fileName = "src\\Images\\";
						
						if (moves[i][j] == 1) {
							fileName += "y.png";
						}
						else if (moves[i][j] == 2) {
							int player = temp.getPlayer();
							
							if (player == 1) {
								fileName += "b\\";
							}
							else
								fileName += "w\\";
							
							ChessPiece enemyPiece = boardGame.getPiece(i, j);
							String pieceType = enemyPiece.getType();
							fileName += pieceType + "\\";
							
							fileName += "r.png";
							
						}
						
						// System.out.println(fileName);
						if (!fileName.equals("src\\Images\\"))
							buttons.updateButtons(j, i, fileName);
						
					}
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Shows moves at the specified location
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public static void showMove(int x, int y) {
		
		ChessPiece myPiece = boardGame.getPiece(x, y);
		
		if (myPiece == null) {
			System.out.println("Error, no piece at specified location");
		}
		else
			myPiece.printMoves(x, y, boardGame.getPieceOwnerLayout());
	}
	
	/**
	 * Create the application.
	 */
	public ChessGUI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 600, 600);
		frame.getContentPane().add(table);
		
		// http://www.clker.com/clipart-7190.html
	}
	
	private static void drawBoard() {
		// String fileName = "src\\Images\\b\\b\\r.png";
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				perPiece(i, j);
				
			}
		}
	}
	
	/**
	 * Generates image filenames for given position
	 * 
	 * @param i
	 *            x location
	 * @param j
	 *            y location
	 */
	private static void perPiece(int i, int j) {
		String fileName = "src\\Images\\";
		
		ChessPiece temp = null;
		temp = boardGame.getPiece(i, j);
		
		if (temp != null) {
			
			int player = temp.getPlayer();
			
			if (player == 1) {
				fileName += "w\\";
			}
			else
				fileName += "b\\";
			
			String pieceType = temp.getType();
			fileName += pieceType + "\\";
		}
		
		// Thanks Mason & binary is faster
		if (((i + j) & 1) == 0) {
			fileName += "g.png";
		}
		else {
			fileName += "w.png";
		}
		buttons.updateButtons(j, i, fileName);
		// System.out.println(fileName);
		
	}
	
	/**
	 * Moves the enemy and changes the turn.
	 * Picks moves until successful move
	 * Priority Queue from advantageous moves to neutral moves.
	 * Dumps all neutral moves into a bag.
	 * Runs through priority queue for good to neutral moves.
	 * Dumps bad moves into a bag.
	 * Randomly pick moves until valid move found.
	 * If no moves available, enemy king is checkmate.
	 */
	private static void moveEnemy() {
		
		boolean moved = false;
		
		PriorityQueue<PieceMove> AI = new PriorityQueue<PieceMove>();
		Bag<PieceMove> randomMove = new Bag<PieceMove>();
		
		// Entire board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				// Get Chess Piece at position
				ChessPiece temp = boardGame.getPiece(i, j);
				
				// If if there is a piece at the position
				if (temp != null) {
					
					// if the piece belongs to the current player
					if (temp.getPlayer() == turn) {
						
						/*
						 * System.out.println("Piece at " + i + ", " + j);
						 * if (i == 6 && j == 0) {
						 * System.out.println("");
						 * }
						 */
						
						// Get all possible moves
						int moves[][] = temp.getMoves(i, j, boardGame.getPieceOwnerLayout());
						
						// Look for an attacks within piece moves
						for (int a = 0; a < 8; a++) {
							for (int b = 0; b < 8; b++) {
								
								// if the piece can attack the position
								if (moves[a][b] == 2) {
									
									/*
									 * // TEST where program crashes
									 * System.out.println("Looking to attack " + a + ", " + b);
									 * 
									 * boardGame.printPieceOwnerLayout();
									 */
									
									ChessPiece enemy = boardGame.getPiece(a, b);
									
									// get the value of the opponent piece
									int value = enemy.getPoints();
									
									// See if the enemy's piece will in danger in new position
									// Perhaps I should move this to outside the for loops rather than regenrate it
									// each time, but I'm worried about shallow vs deep copy
									if (!King.isMoveSafe(a, b, boardGame.getPieceOwnerLayout(), i, j)) {
										
										value -= temp.getPoints();
									}
									
									// Create a new pieceMove object
									// public PieceMove(int pieceX, int pieceY, int moveX, int moveY, int points) {
									PieceMove aMove = new PieceMove(i, j, a, b, value);
									
									AI.enqueue(aMove);
									
								}
								
								// If the move isn't an attack, we'll put it the in case a random move needs to
								// be picked
								else if (moves[a][b] == 1) {
									PieceMove aMove = new PieceMove(i, j, a, b, 0);
									randomMove.add(aMove);
									
								}
							}
							
						}
					}
				}
			} // End priority Queue each move
			
		}
		
		// randomMove.printBag();
		
		// AI.print();
		
		// While no piece moved and the PQ isn't null
		while (!moved && AI.getFront() != null) {
			
			// Dequeue a move from the PQ
			PieceMove dequed = AI.dequeue();
			
			// If the move is at least an equal tradeoff
			if (dequed.getPoints() >= 0) {
				moved = movePiece(dequed);
			}
			else {
				// Add the bad moves to the random bag. This decreases the likelihood that they
				// get chosen, but it keeps them available to check in circumstances such as
				// checkmate.
				
				randomMove.add(dequed);
				
			}
		}
		
		/*
		 * One day, I will have a program. It will have random number genrators with
		 * people names. It will have Rando, Randi, Rande, Rand, Randella, and Randello.
		 * Toward the end of the program, I will throw in one named Sam, just to be
		 * random XD
		 */
		
		while (!moved && !randomMove.isEmpty()) {
			
			PieceMove randi = randomMove.getRandomItem();
			
			moved = movePiece(randi);
		}
		
		if (!moved) {
			System.out.println("Error, no avaiable moves.  ENEMY KING CHECKMATE");
		}
		
		// And change turns
		changeTurn();
		
	}
	
	/**
	 * Move piece when given a PieceMove object
	 * 
	 * @param bestMove
	 *            a PieceMove Object that you want to move the
	 * @return true if move is valid
	 */
	private static boolean movePiece(PieceMove bestMove) {
		return (boardGame.movePiece(bestMove.getPieceX(), bestMove.getPieceY(), bestMove.getMoveX(),
				bestMove.getMoveY(), turn));
	}
	
}
