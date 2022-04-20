package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import game.controller.Controller;

public class GamePanel extends JPanel
{
	private Controller app;

	private String[][] dataGrid;
	
	private JTable gameFieldTable;

	private SpringLayout layout;
	private GridLayout gameFieldTableLayout;

	public GamePanel(Controller app)
	{
		super();
		this.app = app;
		
		this.dataGrid = new String[25][35];
		
		this.gameFieldTable = new JTable(25, 35);

		this.layout = new SpringLayout();

		this.gameFieldTableLayout = new GridLayout(25, 35);

		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.BLACK);
		
		gameFieldTable.setRowHeight(30);
		gameFieldTable.setPreferredSize(new Dimension(1330, 750));
		gameFieldTable.setLayout(gameFieldTableLayout);
		setupInitialGameField();
 
		this.add(gameFieldTable);

	}

	private void setupInitialGameField()
	{
		JLabel initialImage = new JLabel(new ImageIcon(getClass().getResource("/game/view/images/Red.png")));
		for (int row = 0; row < dataGrid.length; row++)
		{
			for (int column = 0; column < dataGrid[0].length; column++)
			{
				dataGrid[row][column] = "Empty";
				gameFieldTable.setValueAt(initialImage, row, column);
			}
		}
	}

	private void setupListeners()
	{

	}

	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.WEST, gameFieldTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, gameFieldTable, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, gameFieldTable, -250, SpringLayout.EAST, this);
	}
}
