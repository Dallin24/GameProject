package game.view;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;


import game.controller.Controller;
import game.model.Cell;

public class GamePanel extends JPanel
{
	private Controller app;

	private int gameRowCellCount;
	private int gameColumnCellCount;

	private Cell redPlayer;

	private JPanel gameFieldPanel;
	private JTable gameTable;
	private Cell blank;

	private SpringLayout layout;
	private GridLayout gameTableLayout;

	public HashMap<Integer, Boolean> keysState;

	public GamePanel(Controller app)
	{
		super();
		this.app = app;

		setScreenProportions();

		this.redPlayer = new Cell("RED", "PLAYER", 90, 0, 0);

		this.gameFieldPanel = new JPanel();
		this.gameTable = new JTable(gameRowCellCount, gameColumnCellCount)
		{
			public Class getColumnClass(int column)
			{
				return ImageIcon.class;
			}
		};

		this.blank = new Cell("BLANK");

		this.layout = new SpringLayout();
		this.gameTableLayout = new GridLayout(gameRowCellCount, gameColumnCellCount);

		this.keysState = new HashMap<Integer, Boolean>()
		{
			{
			}
		};

		setupPanel();
		setupListeners();
		setupLayout();
	}

	private void setupPanel()
	{
		this.setLayout(layout);
		this.setBackground(Color.DARK_GRAY);

		setupInitialGameField();
	}

	private void setScreenProportions()
	{
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight = (int) size.getHeight();
		int screenWidth = (int) size.getWidth();

		gameColumnCellCount = (int) (screenWidth / 38) - 18;
		gameRowCellCount = (int) (screenHeight / 30) - 10;
	}

	private void setupInitialGameField()
	{
		gameTable.setRowHeight(30);
		gameTable.setPreferredSize(new Dimension(38 * gameColumnCellCount, 30 * gameRowCellCount));
		gameTable.setLayout(gameTableLayout);
		gameTable.setCellSelectionEnabled(false);
		gameTable.setGridColor(Color.BLACK);
		gameTable.setBackground(Color.GREEN);
		gameTable.setEnabled(false);

		for (int row = 0; row < gameTable.getRowCount(); row++)
		{
			for (int column = 0; column < gameTable.getColumnCount(); column++)
			{
				gameTable.setValueAt(blank.getImage(), row, column);
			}
		}

		int midHeight = gameTable.getColumnCount() / 2;
		int midWidth = (gameTable.getRowCount() / 2) / 2;

		redPlayer.setLocation(midHeight, midWidth);
		gameTable.setValueAt(redPlayer.getImage(), midHeight, midWidth);

		this.add(gameFieldPanel);

		gameFieldPanel.setBackground(Color.LIGHT_GRAY);
		gameFieldPanel.add(gameTable);
	}

	private void setupListeners()
	{
		this.addKeyListener(new KeyListener()
		{
			long lastMove = System.currentTimeMillis();
			final long threshold = 100; // 500msec = half second

			@Override
			public void keyTyped(KeyEvent keyboard)
			{
			}

			@Override
			public void keyReleased(KeyEvent keyboard)
			{
			}

			@Override
			public void keyPressed(KeyEvent keyboard)
			{
				long now = System.currentTimeMillis();
				// e.getKeyChar()
				switch (keyboard.getKeyChar())
				{
				case 'w':

					if (now - lastMove > threshold)
					{
						handleWKey();
						lastMove = now;
					}
					break;
				case 'a':
					if (now - lastMove > threshold)
					{
						handleAKey();
						lastMove = now;
					}
					break;
				case 's':
					if (now - lastMove > threshold)
					{
						handleSKey();
						lastMove = now;
					}
					break;
				case 'd':
					if (now - lastMove > threshold)
					{
						handleDKey();
						lastMove = now;
					}
					break;
				}
				gameTable.updateUI();
			}
		});

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private void handleWKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		try
		{
			redPlayer.setLocation(currentRow - 1, currentColumn);
			redPlayer.setDirection(0);
			gameTable.setValueAt(redPlayer.getImage(), currentRow - 1, currentColumn);

			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
		}
	}

	private void handleAKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		try
		{
			redPlayer.setLocation(currentRow, currentColumn - 1);
			redPlayer.setDirection(270);
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn - 1);

			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
		}
	}

	private void handleSKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		try
		{
			redPlayer.setLocation(currentRow + 1, currentColumn);
			redPlayer.setDirection(180);
			gameTable.setValueAt(redPlayer.getImage(), currentRow + 1, currentColumn);

			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
		}
	}

	private void handleDKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		try
		{

			redPlayer.setLocation(currentRow, currentColumn + 1);
			redPlayer.setDirection(90);
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn + 1);

			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
		}

	}

	public void checkCells()
	{

	}

	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.WEST, gameFieldPanel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, gameFieldPanel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, gameFieldPanel, 0, SpringLayout.EAST, this);
	}
}
