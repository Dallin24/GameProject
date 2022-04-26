package game.view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import game.model.Cell;

public class GameInfo extends JPanel
{
	private int cellWidth;
	private int cellHeight;
	
	private int totalCellCountHorizontal;
	private int totalCellCountVertical;
	
	private Cell player;
	
	private JTextArea playerName;
	private JTextArea playerHealth;
	
	private SpringLayout layout;

	public GameInfo(Cell player, int cellWidth, int cellHeight, int totalCellCountHorizontal, int totalCellCountVertical)
	{
		super();

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		
		this.totalCellCountHorizontal = totalCellCountHorizontal;
		this.totalCellCountVertical = totalCellCountVertical;
		
		this.player = player;

		this.playerName = new JTextArea();
		this.playerHealth = new JTextArea();

		setupPanel();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setPreferredSize(new Dimension(cellWidth * 8, cellHeight * 10));
		
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
