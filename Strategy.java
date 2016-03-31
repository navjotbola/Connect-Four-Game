import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Strategy {
	ArrayList validPositions = new ArrayList();
	private int totalButtons;

	public Strategy(int size) {
		totalButtons = size * size;
		createEnabledList(size);
	}

	/**
	 * creates an arraylist that holds "enabled" buttons. Enabled button is a
	 * button that is free to be clicked
	 * 
	 * @param size
	 */
	private void createEnabledList(int size) {
		for (int i = totalButtons; i > (totalButtons - size); i--) {
			validPositions.add(i);
		}
	}

	/**
	 * Checks if click is at a valid position
	 * 
	 * @param position
	 * @param size
	 * @return
	 */
	public boolean checkValid(int position, int size) {
		if (validPositions.contains(position)) {
			validPositions.add(position - size);
			return true;
		} else {
			return false;
		}
	}
}
