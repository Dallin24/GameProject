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
		final long shotThreshold = 400; 
		
		long lastCycle = System.currentTimeMillis();
		final long cycleThreshold = 75; 
		
		long lastShrink = System.currentTimeMillis();
		final long shrinkThreshold = 10000; 
		
		while (!isPlayerDead)
		{
			lastShot = display.getPanelLastShot();
			lastCycle = display.getPanelLastCycle();
			lastShrink = display.getPanelLastShrink();
			display.fireBullets(lastShot, shotThreshold);
			display.checkCells(lastCycle, cycleThreshold);
			display.shrinkScreen(lastShrink, shrinkThreshold);

			isPlayerDead = display.arePlayersDead();
		}
		
		Color color;
		if(display.playerVictor().equals("TIE"))
		{
			color = Color.BLACK;
		}
		else if(display.playerVictor().equals("RED"))
		{
			 color = Color.RED;
		}
		else
		{
			 color = Color.BLUE;
		}
		
		if(display.playerVictor().equals("TIE"))
		{
			menu.displayMessage(display, "It's a TIE!", color);
		}
		else
		{
			menu.displayMessage(display, display.playerVictor() + " has won!", color);
		}
		
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
