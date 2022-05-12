package game.controller;

/**
 * Starts game program
 * 
 * @author Dallin Gibbs
 *
 */
public class Runner
{
	/**
	 * Starts game program and repeats until user ends game
	 * @param args
	 */
	public static void main(String[] args)
	{
		Controller app = new Controller();

		while (true)
		{
			String result = app.start();
			if (result.equals("NO"))
			{
				break;
			}
		}

		app.clearScreen();

	}
}
