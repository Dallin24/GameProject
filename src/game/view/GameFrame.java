package game.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.controller.Controller;

public class GameFrame extends JFrame
{
	private Controller app;
	private GamePanel panel;
	
	public GameFrame(Controller app)
	{
		super();
		
		this.app = app;
		this.panel = new GamePanel(app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setTitle("Game Project");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void checkCells()
	{
		this.panel.checkCells();
	}
	
	public void fireBullets(long lastMove, long threshold)
	{
		this.panel.fireBullets(lastMove, threshold);
	}
	
	public long getPanelLastShot()
	{
		return this.panel.getPanelLastShot();
	}
}
