import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Scanner;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

	static JButton b[][];
	private int clicks;
	private int player;
	private static int size;
	static int boardSize;
	static int connectionLength;
	Controller gameController = new Controller();
	Strategy gameStrategy;

	/**
	 * Creates new JFrame with title Creates matrix of JButtons and sets
	 * actionCommand for each.
	 *
	 * @param size
	 */
	public GUI(int newSize, int connect) {
		super(newSize + " Square");
		size = newSize;
		b = new JButton[size][size];
		setLayout(new GridLayout(size, size));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gameStrategy = new Strategy(newSize);
		makeBoard();
	}

	/**
	 * Method that makes GUI board
	 */
	public void makeBoard() {
		int buttonNumber = 1;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				b[i][j] = new JButton();
				b[i][j].setActionCommand("" + buttonNumber);
				// b[i][j].setActionCommand("" + i + j);
				b[i][j].addActionListener(this);
				add(b[i][j]);
				buttonNumber++;
			}
	}

	@Override
	/**
	 * Performs actions on clicked button.
	 */
	public void actionPerformed(ActionEvent ae) {

		String position = ae.getActionCommand();
		int positionInt = Integer.valueOf(position);

		dropPiece(positionInt);

	}

	/**
	 * Drop piece method to get color of current player and set piece to that
	 * color. Switches colors of clicks to represent changing users setOpaque
	 * and setBorderPainted for Mac users
	 * 
	 * @param position
	 */
	private void dropPiece(int position) {
		// nested forloop that goes through every b[][] and checks for color, if
		// all of the buttons have color its a tie.

		if (position > size * size) {
			return;
		}

		else {

			if (gameStrategy.checkValid(position, size) == true) {
				countClicks();
				Color backgroundColor = null;

				int column;
				int row;

				column = position % size - 1;

				if (column == -1) {
					column = size - 1;
				}

				row = (position - 1) / size;

				player = gameController.whichPlayerTurn(clicks);
				backgroundColor = gameController.getBackgroundColor(player);
				if (player == 1) {
					b[row][column].setBackground(backgroundColor);
					b[row][column].setText("RED");
					b[row][column].setForeground(Color.BLACK);
					// mac background setting
					b[row][column].setOpaque(true);
					b[row][column].setBorderPainted(false);
					b[row][column].setEnabled(false);

				} else {
					b[row][column].setBackground(backgroundColor);
					b[row][column].setText("BLACK");
					b[row][column].setForeground(Color.WHITE);
					// mac background setting
					b[row][column].setOpaque(true);
					b[row][column].setBorderPainted(false);
					b[row][column].setEnabled(false);
				}

				System.out.println("Player (" + b[row][column].getText() + ")"
						+ " has placed a position at (" + row + ", " + column
						+ ")");

				if (gameController.checkWinningSequence(row, column, size,
						connectionLength, b) == true) {
					System.out.println("Player (" + b[row][column].getText()
							+ ") "
							+ "has won game by placing winning position on ("
							+ row + ", " + column + ")!");
					for (int f = 0; f < b.length; f++) {
						for (int h = 0; h < b.length; h++) {
							b[f][h].setEnabled(false);
						}
					}

					JFrame winner = new JFrame("");
					winner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					winner.setLayout(new GridLayout(1, 1));
					JButton winnerButton = new JButton("Player "
							+ b[row][column].getText() + " " + "Wins!");
					winner.add(winnerButton);
					winner.pack();
					winner.setVisible(true);
				}

			} else {
				// /call the function again with position + size
				dropPiece(position + size);

			}

		}

	}

	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Controller control = new Controller();
		Scanner scanner = new Scanner(System.in);
		if (args.length != 0) {
			while (true) {
				boardSize = Integer.parseInt(args[0]);
				connectionLength = Integer.parseInt(args[1]);

				if (connectionLength > boardSize) {
					System.out
							.println("Please enter valid connection sequence and board size (i.e. Connection Sequence <= BoardSize)");
				} else
					break;

			}

		} else {
			while (true) {

				System.out.println("Enter a size for the rows/columns");
				boardSize = scanner.nextInt();
				System.out.println("Enter a size for the connection");
				connectionLength = scanner.nextInt();

				if (connectionLength > boardSize) {
					System.out
							.println("Please enter valid connection sequence and board size (i.e. Connection Sequence <= BoardSize)");
				} else
					break;
			}
		}
		GUI g = new GUI(boardSize, connectionLength);
		g.setVisible(true);
		g.setSize(500, 500);
	}

	public void countClicks() {
		clicks++;
	}

}