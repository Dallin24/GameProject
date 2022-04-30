package game.controller;

import java.awt.Color;

import game.view.GameFrame;
import game.view.Popup;

public class Controller
{
	private GameFrame display;
	
	private Popup menu;
	
	private boolean isPlayerDead;

	public Controller()
	{
		this.display = new GameFrame(this);
		
		this.menu = new Popup();
		
		this.isPlayerDead = false;
	}

	public String start()
	{
		long lastShot = System.currentTimeMillis();
		final long threshold = 400; 
		
		long lastCycle = System.currentTimeMillis();
		final long cycleThreshold = 75; 
		
		while (!isPlayerDead)
		{
			lastShot = display.getPanelLastShot();
			lastCycle = display.getPanelLastCycle();
			display.fireBullets(lastShot, threshold);
			display.checkCells(lastCycle, cycleThreshold);

			isPlayerDead = display.arePlayersDead();
		}
		
		Color color;
		if(display.playerVictor().equals("RED"))
		{
			 color = Color.RED;
		}
		else
		{
			 color = Color.BLUE;
		}
		menu.displayMessage(display, display.playerVictor() + " has won!", color);
		
		String result = menu.playAgain(display, "Play again?");
		
		if(result.equals("NO"))
		{
			return "NO";
		}
			
		display.dispose();
		display = new GameFrame(this);
		isPlayerDead = false;
		
		return "YES";
	}
	
	public void clearScreen()
	{
		display.dispose();
	}
}
