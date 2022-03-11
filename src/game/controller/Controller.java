package game.controller;

import game.view.GameFrame;

public class Controller
{
	private GameFrame display;
	
	public Controller()
	{
		display = new GameFrame(this, 500, 800);
	}
	
	public void start() 
	{
		
	}
}
