package game.view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import game.controller.Controller;

public class GameFrame extends JFrame
{
	private Controller app;
	private GamePanel panel;
	
	public GameFrame(Controller app, int numCols, int numRows)
	{
		super();
		
		this.app = app;
		this.panel = new GamePanel(app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setTitle("Game Project");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setResizable(false);
		this.setVisible(true);
	}
}
