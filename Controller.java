import java.awt.Color;

import javax.swing.JButton;

public class Controller {

	public int whichPlayerTurn(int click) {
		if (click % 2 == 0) {
			return 1;
		} else
			return 2;
	}

	public Color getBackgroundColor(int player) {
		if (player == 1) {
			return Color.RED;
		} else
			return Color.BLACK;

	}

	// ****The following are methods to check the winning sequence****
	public boolean checkWinningSequence(int row, int column, int size,
			int connectionLength, JButton b[][]) {

		return (checkWinningHorizontalSequence(row, column, size,
				connectionLength, b)
				|| checkWinningVerticalSequence(row, column, size,
						connectionLength, b)
				|| checkWinningLeftDiagonalSequence(row, column, size,
						connectionLength, b) || checkWinningRightDiagonalSequence(
					row, column, size, connectionLength, b));
	}

	public boolean checkWinningHorizontalSequence(int row, int column,
			int size, int connectionLength, JButton b[][]) {

		int counterRight = 1;
		int counterLeft = 0;

		for (int i = column + 1; i <= connectionLength + column; i++) {
			if (i >= size) {
				break;
			}

			if (b[row][column].getText() != b[row][i].getText()) {
				break;
			}

			if (b[row][column].getText() == b[row][i].getText()) {
				counterRight++;
			}

		}
		if (counterRight == connectionLength) {
			return true;
		}

		for (int i = column - 1; i >= column - connectionLength + counterRight; i--) {
			if (i >= size || i < 0) {
				break;
			}

			if (b[row][column].getText() != b[row][i].getText()) {
				break;
			}

			if (b[row][column].getText() == b[row][i].getText()) {
				counterLeft++;
			}

		}

		if ((counterRight + counterLeft) == connectionLength) {
			return true;
		}
		return false;
	}

	public boolean checkWinningVerticalSequence(int row, int column, int size,
			int connectionLength, JButton b[][]) {

		int counterUp = 1;
		int counterDown = 0;

		for (int i = row + 1; i <= connectionLength + row; i++) {
			if (i >= size) {
				break;
			}

			if (b[row][column].getText() != b[i][column].getText()) {
				break;
			}

			if (b[row][column].getText() == b[i][column].getText()) {
				counterUp++;
			}
		}
		if (counterUp == connectionLength) {
			return true;
		}
		for (int i = row - 1; i >= row - connectionLength + counterUp; i--) {
			if (i >= size || i < 0) {
				break;
			}

			if (b[row][column].getText() != b[i][column].getText()) {
				break;
			}

			if (b[row][column].getText() == b[i][column].getText()) {
				counterDown--;
			}

		}

		if ((counterUp + counterDown) == connectionLength) {
			return true;
		}

		return false;
	}

	// Checks Diagonal Winning Sequence from Right to Left
	public boolean checkWinningLeftDiagonalSequence(int row, int column,
			int size, int connectionLength, JButton b[][]) {

		int upCounter = 1;
		int downCounter = 0;
		for (int i = row + 1, j = column + 1; i <= row + connectionLength
				&& j <= column + connectionLength; i++, j++) {
			if (i >= size || j >= size) {
				break;
			} else if (b[row][column].getText().toString() != b[i][j].getText()
					.toString()) {
				break;
			} else if (b[row][column].getText().toString() == b[i][j].getText()
					.toString()) {
				upCounter++;
			}
		}
		if (upCounter == connectionLength) {
			return true;
		}
		for (int i = row - 1, j = column - 1; i >= row - connectionLength
				- upCounter
				&& j >= column - connectionLength - upCounter; i--, j--) {
			if (i >= size || i < 0 || j >= size || j < 0) {
				break;
			} else if (b[row][column].getText().toString() != b[i][j].getText()
					.toString()) {
				break;
			} else if (b[row][column].getText().toString() == b[i][j].getText()
					.toString()) {
				downCounter++;
			}
		}
		if ((upCounter + downCounter) == connectionLength) {
			return true;
		}
		return false;
	}

	// Checks Diagonal Winning Sequence from Left to Right
	public boolean checkWinningRightDiagonalSequence(int row, int column,
			int size, int connectionLength, JButton b[][]) {

		int counterRight = 0;
		int counterLeft = 1;

		for (int i = row + 1, j = column - 1; i <= connectionLength + row; i++, j--) {
			if (i >= size || j >= size || i < 0 || j < 0) {
				break;
			}

			if (b[row][column].getText().toString() != b[i][j].getText()
					.toString()) {
				break;
			}

			if (b[row][column].getText().toString() == b[i][j].getText()
					.toString()) {
				counterLeft++;
			}

		}
		if (counterLeft == connectionLength) {
			return true;
		}

		for (int i = row - 1, j = column + 1; i > row - connectionLength
				- counterLeft; i--, j++) {
			if (i >= size || i < 0 || j < 0 || j >= size) {
				break;
			}

			if (b[row][column].getText().toString() != b[i][j].getText()
					.toString()) {
				break;
			}

			if (b[row][column].getText().toString() == b[i][j].getText()
					.toString()) {
				counterRight++;
			}

		}

		if ((counterRight + counterLeft) == connectionLength) {
			return true;
		}
		return false;
	}

}
