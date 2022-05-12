package game.controller;

import java.awt.Color;

import game.view.GameFrame;
import game.view.Popup;

/**
 * Controller that runs GameFrame and Menu for users
 * 
 * @author Dallin Gibbs
 */
public class Controller
{
	/**
	 * Used to create game
	 */
	private GameFrame display;

	/**
	 * Used to display menu to the future
	 */
	private Popup menu;

	/**
	 * Holds boolean if a player is dead
	 */
	private boolean isPlayerDead;

	/**
	 * Holds int array with win information
	 */
	private int[] winRecords;

	/**
	 * Holds location of save file
	 */
	private String dataFile;

	/**
	 * Creates game
	 */
	public Controller()
	{
		this.display = new GameFrame(this);

		this.menu = new Popup();

		this.isPlayerDead = false;

		this.winRecords = new int[3];
		this.dataFile = "save.wins";
	}

	/**
	 * Starts and runs the game
	 * 
	 * @return YES or NO string to continue or close game
	 */
	public String start()
	{
		this.winRecords = IOController.loadData(dataFile, display);

		menu.displayMessage(null, "The current records are:" + "\nRed Wins: " + winRecords[0] + "\nBlue Wins: " + winRecords[1] + "\nTies: " + winRecords[2] + "\n\nPress OK to start the game");

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
		if (display.playerVictor().equals("TIE"))
		{
			this.winRecords[2] += 1;
			color = Color.BLACK;
		}
		else if (display.playerVictor().equals("RED"))
		{
			this.winRecords[0] += 1;
			color = Color.RED;
		}
		else
		{
			this.winRecords[1] += 1;
			color = Color.BLUE;
		}

		IOController.saveData(dataFile, winRecords, display);

		if (display.playerVictor().equals("TIE"))
		{
			menu.displayMessage(display, "It's a TIE!", color);
		}
		else
		{
			menu.displayMessage(display, display.playerVictor() + " has won!", color);
		}

		String result = menu.playAgain(display, "Play again?");

		if (result.equals("NO"))
		{
			return "NO";
		}

		display.dispose();
		display = new GameFrame(this);
		isPlayerDead = false;

		return "YES";
	}

	/**
	 * Disposed of the frame
	 */
	public void clearScreen()
	{
		display.dispose();
	}

	/**
	 * @returns game win information
	 */
	@Override
	public String toString()
	{
		String text = "";

		this.winRecords = IOController.loadData(dataFile, display);

		text += "The current records are: " + "Red Wins: " + winRecords[0] + " Blue Wins: " + winRecords[1] + " Ties: " + winRecords[2];

		return text;
	}
}
