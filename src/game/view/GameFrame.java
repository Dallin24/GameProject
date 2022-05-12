package game.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.controller.Controller;

/**
 * Creates GameFrame visual
 * 
 * @author Dallin Gibbs
 */
public class GameFrame extends JFrame
{
	/**
	 * Holds controller for the application
	 */
	private Controller app;

	/**
	 * Holds panel for the frame
	 */
	private GamePanel panel;

	/**
	 * Creates frame
	 * 
	 * @param app
	 */
	public GameFrame(Controller app)
	{
		super();

		this.app = app;
		this.panel = new GamePanel(app);

		setupFrame();
	}

	/**
	 * Set properties of the frame
	 */
	private void setupFrame()
	{
		this.setTitle("Game Project");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Reset the panel
	 * 
	 * @param app
	 */
	public void restart(Controller app)
	{
		this.panel.removeAll();
		this.panel = new GamePanel(app);
	}

	/**
	 * Update cells on screen
	 * 
	 * @param lastCycle
	 * @param cycleThreshold
	 */
	public void checkCells(long lastCycle, long cycleThreshold)
	{
		this.panel.checkCells(lastCycle, cycleThreshold);
	}

	/**
	 * Create bullets from players
	 * 
	 * @param lastShot
	 * @param shotThreshold
	 */
	public void fireBullets(long lastShot, long shotThreshold)
	{
		this.panel.fireBullets(lastShot, shotThreshold);
	}

	/**
	 * Update closing border on screen
	 * 
	 * @param lastShrink
	 * @param shrinkThreshold
	 */
	public void shrinkScreen(long lastShrink, long shrinkThreshold)
	{
		this.panel.shrinkScreen(lastShrink, shrinkThreshold);
	}

	/**
	 * Checks if plauers are dead or wall closes
	 * 
	 * @return boolean
	 */
	public boolean arePlayersDead()
	{
		return this.panel.arePlayersDead();
	}

	/**
	 * Gets who won the gmae
	 * 
	 * @returns who won the game, player or wall
	 */
	public String playerVictor()
	{
		return this.panel.playerVictor();
	}

	public long getPanelLastShot()
	{
		return this.panel.getPanelLastShot();
	}

	public long getPanelLastCycle()
	{
		return this.panel.getPanelLastCycle();
	}

	public long getPanelLastShrink()
	{
		return this.panel.getPanelLastShrink();
	}
}
