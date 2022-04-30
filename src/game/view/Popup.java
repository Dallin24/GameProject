package game.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Popup {
	private Object[] options = { "Yes", "No" };

	public void displayMessage(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame, message, null, JOptionPane.PLAIN_MESSAGE, null);
	}

	public void displayMessage(JFrame frame, String message, Color color) {
		UIManager um = new UIManager();
		um.put("OptionPane.messageForeground", color);

		JOptionPane.showMessageDialog(frame, message, "GAME OVER", JOptionPane.PLAIN_MESSAGE, null);
	}

	public String playAgain(JFrame frame, String message) {

		UIManager um = new UIManager();
		um.put("OptionPane.messageForeground", Color.BLACK);

		int result = JOptionPane.showOptionDialog(frame, message, null, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (result == 0) {
			return "YES";
		} else {
			return "NO";
		}
	}

}
