package game.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Popup {
	private Object[] options = { "Yes", "No"};

	public void displayMessage(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame, message);
	}

	public String playAgain(JFrame frame, String message) {
		int result = JOptionPane.showOptionDialog(frame, message,
				null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		
		if(result == 0)
		{
			return "YES";
		}
		else
		{
			return "NO";
		}
	}

}
