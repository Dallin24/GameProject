package game.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import game.model.Cell;
import game.model.HealthCell;

import javax.swing.ImageIcon;

import java.awt.Font;
import java.util.ArrayList;

public class PlayerInfo extends JPanel
{
	private int cellWidth;
	private int cellHeight;

	private JTable healthBar;
	private ArrayList<HealthCell> healthData;

	private Dimension normalDimension;
	
	private Cell player;

	private JTextField playerName;
	private JTextField playerHealth;

	private SpringLayout layout;

	public PlayerInfo(Cell player, int cellWidth, int cellHeight)
	{
		super();

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;

		this.normalDimension = new Dimension(380, 990);

		this.healthBar = new JTable(10, 1)
		{
			public Class getColumnClass(int column)
			{
				return ImageIcon.class;
			}
		};
		this.healthData = new ArrayList<HealthCell>();

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

		if (player.getPlayerName().equals("RED"))
		{
			playerName.setForeground(Color.RED);
		}
		else
		{
			playerName.setForeground(Color.BLUE);
		}
		this.add(playerName);

		playerHealth.setText(player.getHealth() + "");
		playerHealth.setFont(new Font("Kohinoor Gujarati", Font.BOLD, 22));
		playerHealth.setHorizontalAlignment(JTextField.CENTER);
		playerHealth.setEditable(false);
		playerHealth.setHighlighter(null);
		playerHealth.setBackground(this.getBackground());
		playerHealth.setBorder(null);
		this.add(playerHealth);

		healthBar.setRowHeight(30);
		healthBar.setPreferredSize(new Dimension(cellWidth, cellHeight * 10));
		healthBar.setSize(new Dimension(cellWidth, cellHeight * 10));
		healthBar.setCellSelectionEnabled(false);
		healthBar.setGridColor(Color.BLACK);
		healthBar.setEnabled(false);
		healthBar.setBackground(Color.BLACK);

		for (int index = 0; index < 10; index++)
		{
			healthData.add(new HealthCell("GREEN"));
			healthBar.setValueAt(healthData.get(index).getColor(), index, 0);
		}

		this.add(healthBar);

	}

	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, playerName, cellHeight, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, playerName, cellWidth, SpringLayout.WEST, this);	
		layout.putConstraint(SpringLayout.EAST, playerName, -cellWidth, SpringLayout.EAST, this);

		layout.putConstraint(SpringLayout.NORTH, playerHealth, 0, SpringLayout.SOUTH, healthBar);
		layout.putConstraint(SpringLayout.WEST, playerHealth, cellWidth * 3, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.WEST, healthBar, cellWidth * 3, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, healthBar, -cellWidth * 3, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, healthBar, cellHeight * 4, SpringLayout.NORTH, this);

	}

	public void updatePlayerData(String type, int healthChange)
	{
		if (type.equals("HEALTH"))
		{
			player.setHealth(player.getHealth() + healthChange);
			playerHealth.setText(player.getHealth() + "");
			healthData.remove(9);
			healthData.add(0, new HealthCell("WHITE"));
			
			
			for (int index = 0; index < 10; index++)
			{
				healthBar.setValueAt(healthData.get(index).getColor(), index, 0);
			}

		}
	}

}
