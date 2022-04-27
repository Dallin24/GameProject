package game.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import game.model.Cell;
import javax.swing.BoxLayout;
import java.awt.Font;

public class GameInfo extends JPanel
{
	private int cellWidth;
	private int cellHeight;
	
	private Dimension normalDimension;
	
	private Cell player;
	
	private JTextField playerName;
	private JTextField playerHealth;
	
	private SpringLayout layout;

	public GameInfo(Cell player, int cellWidth, int cellHeight)
	{
		super();

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		
		this.normalDimension = new Dimension(380, 990);
		
		this.player = player;

		this.playerName = new JTextField();
		
		this.playerHealth = new JTextField();
		
		this.layout = new SpringLayout();
		

		setupPanel();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setSize(normalDimension);
		
		playerName.setText(player.getPlayerName());
		playerName.setFont(new Font("Kohinoor Gujarati", Font.BOLD, 54));
		playerName.setHorizontalAlignment(JTextField.CENTER);
		playerName.setEditable(false);
		playerName.setHighlighter(null);
		playerName.setBackground(this.getBackground());
		playerName.setBorder(null);
		
		if(player.getPlayerName().equals("RED"))
		{
			playerName.setForeground(Color.RED);
		}
		else
		{
			playerName.setForeground(Color.BLUE);
		}

		
		playerHealth.setText(player.getHealth() + "");
		
		this.add(playerName);
		this.add(playerHealth);
		
		
	}
	
	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, playerName, cellHeight, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, playerName, cellWidth, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, playerName, -cellWidth, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.NORTH, playerHealth, 309, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, playerHealth, 152, SpringLayout.WEST, this);
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
