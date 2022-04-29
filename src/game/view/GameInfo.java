package game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.TableCellRenderer;

import game.model.Cell;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.util.ArrayList;

public class GameInfo extends JPanel
{
	private int cellWidth;
	private int cellHeight;

	private JTable healthBar;
	private ArrayList<Boolean> healthData;

	private Dimension normalDimension;

	private ImageIcon green;
	private ImageIcon white;

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

		this.healthBar = new JTable(10, 1)
		{
			public Class getColumnClass(int column)
			{
				return ImageIcon.class;
			}
		};
		this.healthData = new ArrayList<Boolean>();

		this.green = new ImageIcon(getClass().getResource("/game/view/images/Green.png"));
		this.white = new ImageIcon(getClass().getResource("/game/view/images/White.png"));

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

		playerHealth.setText(player.getHealth() + "");

		this.add(playerName);

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
			healthData.add(true);
			healthBar.setValueAt(green, index, 0);
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
			healthData.remove(healthData.size() - 1);
			
			
			for (int index = 0; index < 10; index++)
			{
				healthBar.setValueAt(green, index, 0);
			}

			
			for (int index = 9 - healthData.size(); index >= 0; index--)
			{
				healthBar.setValueAt(white, index, 0);
			}
		}
	}

}
