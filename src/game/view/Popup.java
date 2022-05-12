package game.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * Creates Popup Object
 * @author Dallin Gibbs
 */
public class Popup
{
	/**
	 * Holds object array with options Yes or No for user
	 */
	private Object[] options = { "Yes", "No" };

	/**
	 * Creates popup to display text
	 * @param frame
	 * @param message
	 */
	public void displayMessage(JFrame frame, String message)
	{
		JOptionPane.showMessageDialog(frame, message, null, JOptionPane.PLAIN_MESSAGE, null);
	}

	/**
	 * Creates popup to display game end text
	 * @param frame
	 * @param message
	 * @param color
	 */
	public void displayMessage(JFrame frame, String message, Color color)
	{
		UIManager um = new UIManager();
		um.put("OptionPane.messageForeground", color);

		JOptionPane.showMessageDialog(frame, message, "GAME OVER", JOptionPane.PLAIN_MESSAGE, null);
	}

	/**
	 * Creates popup to check if player wants to play again
	 * @param frame
	 * @param message
	 * @return YES or NO String
	 */
	public String playAgain(JFrame frame, String message)
	{

		UIManager um = new UIManager();
		um.put("OptionPane.messageForeground", Color.BLACK);

		int result = JOptionPane.showOptionDialog(frame, message, null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (result == 0)
		{
			return "YES";
		}
		else
		{
			return "NO";
		}
	}

}
