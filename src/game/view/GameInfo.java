package game.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.model.Cell;

public class GameInfo extends JPanel
{
	private Cell player;
	
	private JTextArea playerName;
	private JTextArea playerHealth;
	


	public GameInfo(Cell player)
	{
		super();

		this.player = player;

		this.playerName = new JTextArea();
		this.playerHealth = new JTextArea();

		setupPanel();
		setupLayout();
	}

	private void setupPanel()
	{
		playerName.setText(player.getPlayerName());
		playerHealth.setText(player.getHealth() + "");
		
		this.add(playerName);
		this.add(playerHealth);
	}
	
	private void setupLayout()
	{
		
	}
	
	public void updatePlayerData(String type, int healthChange)
	{
		if(type.equals("HEALTH"))
		{
			player.setHealth(player.getHealth() + healthChange);
			playerHealth.setText(player.getHealth() + "");
		}
	}
}
