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
		long lastMove = System.currentTimeMillis();
		final long threshold = 1000; 
		
		while (true)
		{
			lastMove = this.display.getPanelLastMove();
			this.display.fireBullets(lastMove, threshold);
			this.display.checkCells();
		}
	}
}
