package game.controller;

import game.view.Popup;

public class Runner
{
	public static void main(String[] args)
	{
		Controller app = new Controller();
		Popup popup = new Popup();
		
		popup.displayMessage(null, "Press OK to start the game");
		while(true)
		{
			String result = app.start();
			if(result.equals("NO"))
			{
				break;
			}
		}
		
		app.clearScreen();
		
	}
}
