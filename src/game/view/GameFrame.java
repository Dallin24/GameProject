package game.view;

import javax.swing.JFrame;

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
		this.setSize(800, 600);
		//this.setContentPane(panel);
		this.setVisible(true);
	}
}
