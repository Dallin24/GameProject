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
import javax.swing.table.DefaultTableModel;

import game.controller.Controller;
import game.model.Cell;

public class GamePanel extends JPanel
{
	private Controller app;

<<<<<<< Updated upstream
	private String[][] dataGrid;
	
=======
	private Cell[][] gridData;
	
	private JPanel gameFieldPanel;
>>>>>>> Stashed changes
	private JTable gameFieldTable;

	private SpringLayout layout;
	private GridLayout gameFieldTableLayout;
<<<<<<< Updated upstream
=======
	
	private JLabel test;
>>>>>>> Stashed changes

	public GamePanel(Controller app)
	{
		super();
		this.app = app;
		
<<<<<<< Updated upstream
		this.dataGrid = new String[25][35];
		
		this.gameFieldTable = new JTable(25, 35);

		this.layout = new SpringLayout();

=======
		this.gridData = new Cell[25][35];
		
		this.gameFieldPanel = new JPanel();
		this.gameFieldTable = new JTable(25, 35)
				{
					public Class getColumnClass(int column)
					{
						return ImageIcon.class;
					}
				};

		this.layout = new SpringLayout();


>>>>>>> Stashed changes
		this.gameFieldTableLayout = new GridLayout(25, 35);

		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.BLACK);
<<<<<<< Updated upstream
		
		gameFieldTable.setRowHeight(30);
		gameFieldTable.setPreferredSize(new Dimension(1330, 750));
		gameFieldTable.setLayout(gameFieldTableLayout);
		setupInitialGameField();
 
		this.add(gameFieldTable);
=======
>>>>>>> Stashed changes

		setupInitialGameField();
	}

	private void setupInitialGameField()
	{
<<<<<<< Updated upstream
		JLabel initialImage = new JLabel(new ImageIcon(getClass().getResource("/game/view/images/Red.png")));
		for (int row = 0; row < dataGrid.length; row++)
		{
			for (int column = 0; column < dataGrid[0].length; column++)
			{
				dataGrid[row][column] = "Empty";
				gameFieldTable.setValueAt(initialImage, row, column);
=======
		gameFieldTable.setRowHeight(30);
		gameFieldTable.setPreferredSize(new Dimension(1330, 750));
		gameFieldTable.setLayout(gameFieldTableLayout);
		gameFieldTable.setCellSelectionEnabled(false);
		gameFieldTable.setGridColor(Color.BLACK);
		gameFieldTable.setBackground(Color.BLACK);
		gameFieldTable.setEnabled(false);
		
		ImageIcon initialImage = new ImageIcon(getClass().getResource("/game/view/images/Red.png"));
		
		for (int row = 0; row < gridData.length; row++)
		{
			for (int column = 0; column < gridData[0].length; column++)
			{
				gridData[row][column] = new Cell("Player", 90);
				gameFieldTable.setValueAt(initialImage, row, column);
				
>>>>>>> Stashed changes
			}
		}
		
		this.add(gameFieldPanel);
		
		gameFieldPanel.setBackground(Color.LIGHT_GRAY);
		gameFieldPanel.add(gameFieldTable);
	}

	private void setupListeners()
	{

	}

	private void setupLayout()
	{
<<<<<<< Updated upstream
		layout.putConstraint(SpringLayout.WEST, gameFieldTable, 250, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, gameFieldTable, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, gameFieldTable, -250, SpringLayout.EAST, this);
=======
		layout.putConstraint(SpringLayout.WEST, gameFieldPanel, 353, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, gameFieldPanel, -353, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, gameFieldPanel, 0, SpringLayout.SOUTH, this);
>>>>>>> Stashed changes
	}
}
