package game.controller;

import game.view.GameFrame;

public class Controller
{
	private GameFrame display;
	
	private boolean isPlayerDead;

	public Controller()
	{
		this.display = new GameFrame(this);
		
		this.isPlayerDead = false;
	}

	public void start()
	{
		long lastShot = System.currentTimeMillis();
		final long threshold = 400; 
		
		long lastCycle = System.currentTimeMillis();
		final long cycleThreshold = 75; 
		
		while (!isPlayerDead)
		{
			lastShot = this.display.getPanelLastShot();
			lastCycle = this.display.getPanelLastCycle();
			this.display.fireBullets(lastShot, threshold);
			this.display.checkCells(lastCycle, cycleThreshold);

			isPlayerDead = this.display.arePlayersDead();
		}
		
		this.display.dispose();
		this.display = new GameFrame(this);
		this.isPlayerDead = false;
	}
}
