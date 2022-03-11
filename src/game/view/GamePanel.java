package game.view;

import javax.swing.JPanel;

import game.controller.Controller;

public class GamePanel extends JPanel
{
	private Controller app;
	
	public GamePanel(Controller app)
	{
		super();
		
		this.app = app;
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		
	}
	
	private void setupListeners()
	{
		
	}
	
	private void setupLayout()
	{
		
	}
}
