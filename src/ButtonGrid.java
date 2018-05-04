
/**
 * A grid of Buttons using WindowBuilder
 * Owner: Allen Millington
 */

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import java.awt.Color;

// This class built from WIKI How
// https://www.wikihow.com/Make-a-GUI-Grid-in-Java

public class ButtonGrid {
	JFrame frame = new JFrame(); // Creates a new JFrame
	JButton[][] grid; // names the grid of buttons
	
	// Can Change to 80 later
	public final int SIZE = 70;
	String buttonID;
	int buttonCount; // initializes to 0;
	
	/**
	 * Button Grid Constructor
	 * 
	 * @param width
	 *            number of columns
	 * @param length
	 *            number of rows
	 */
	
	public ButtonGrid(int width, int length) {
		
		frame.setLayout(new GridLayout(width, length));
		grid = new JButton[width][length];
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < width; x++) {
				// creates new buttons
				// grid[x][y] = new JButton(x + "," + y);
				grid[x][y] = new JButton();
				
				Dimension buttonSize = new Dimension();
				buttonSize.setSize(SIZE, SIZE);
				grid[x][y].setPreferredSize(buttonSize);
				frame.add(grid[x][y]); // adds button to grid
				
				// Button Listener
				// https://stackoverflow.com/questions/19216794/how-to-implement-actionlistener-for-a-grid-of-buttons-in-java-swing
				
				// I originally tried passing x and y directly, but it only allowed static final
				// variables.
				// Thus I had to calculate the position of the button from it's id.
				buttonID = Integer.toString(buttonCount);
				buttonCount++;
				grid[x][y].setActionCommand(buttonID);
				
				grid[x][y].addActionListener(new ActionListener() {
					
					//
					public void actionPerformed(ActionEvent ae) {
						
						int cmd = Integer.parseInt(ae.getActionCommand());
						// May need to swap these two numbers
						int x = cmd / 8;
						int y = cmd % 8;
						ChessGUI.clicked(x, y);
					}
					/*
					 * public void actionPerformed(ActionEvent e) {
					 * System.out.println("Hello");
					 * 
					 * ((JButton) e.getSource()).setBackground(Color.red);
					 * }(
					 */
					
				});
				
			}
			
			// String fileName = "C:/Users/Owner/Desktop/Chess Pieces/Black
			// Pieces/bb_g.png";
			// String fileName = "C:\\Users\\Owner\\Desktop\\Chess Pieces\\b\\b\\r.png";
			
			/*
			 * ========================================================
			 * String fileName = "src\\Images\\b\\b\\r.png";
			 * // System.out.println(fileName + "\n");
			 * ImageIcon icon = new ImageIcon(fileName);
			 * grid[0][0].setIcon(icon);
			 * =========================================================
			 */
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		}
	}
	
	/*
	 * public static void main(String[] args) {
	 * // Move along, nothing to see here #OfficerBarbrady (South Park)
	 * }
	 */
	/**
	 * Redraws the buttons
	 * 
	 * @param x
	 *            the X position of the button to draw
	 * @param y
	 *            the Y position of the button to draw
	 * @param input
	 *            the file path of the image you want to draw to the button.
	 */
	public void updateButtons(int x, int y, String input) {
		
		ImageIcon icon = new ImageIcon(input);
		grid[x][y].setIcon(icon);
	}
}
