package game.controller;

import game.view.GameFrame;

public class Controller
{
	private GameFrame display;
	
	public Controller()
	{
		display = new GameFrame(this);
	}
	
	public void start() 
	{
	}
}
