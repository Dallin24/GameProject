package game.controller;

import game.view.GameFrame;

public class Controller
{
	private GameFrame display;
	
	public Controller()
	{
		this.display = new GameFrame(this);
	}
	
	public void start() 
	{
		while(true)
		{
			this.display.checkCells();
		}
	}
}
