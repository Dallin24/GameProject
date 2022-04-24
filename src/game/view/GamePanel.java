package game.view;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.TreeSet;

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

	private Cell[][] gameData;

	private Cell redPlayer;
	private Cell bluePlayer;
	private Cell blank;

	private Long panelLastShot;
	private Long panelLastCycle;

	private JPanel gameFieldPanel;
	private JTable gameTable;

	private Set<Integer> pressedKeysWASD;
	private Set<Integer> pressedKeysArrows;

	private SpringLayout layout;

	public GamePanel(Controller app)
	{
		super();
		this.app = app;

		setScreenProportions();

		this.gameData = new Cell[gameRowCellCount][gameColumnCellCount];
		this.redPlayer = new Cell("RED", "PLAYER", 90, 0, 0);
		this.bluePlayer = new Cell("BLUE", "PLAYER", 270, 0, 0);
		this.blank = new Cell("BLANK");

		this.gameFieldPanel = new JPanel();
		this.gameTable = new JTable(gameRowCellCount, gameColumnCellCount)
		{
			public Class getColumnClass(int column)
			{
				return ImageIcon.class;
			}
		};

		this.panelLastShot = System.currentTimeMillis();
		this.panelLastCycle = System.currentTimeMillis();

		this.pressedKeysWASD = new TreeSet<Integer>();
		this.pressedKeysArrows = new TreeSet<Integer>();

		this.layout = new SpringLayout();

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
		gameTable.setCellSelectionEnabled(false);
		gameTable.setGridColor(Color.BLACK);
		gameTable.setBackground(Color.GREEN);
		gameTable.setEnabled(false);

		for (int row = 0; row < gameTable.getRowCount(); row++)
		{
			for (int column = 0; column < gameTable.getColumnCount(); column++)
			{
				gameData[row][column] = blank;
				gameTable.setValueAt(blank.getImage(), row, column);
			}
		}

		int midHeight = gameColumnCellCount / 2 - 2;
		int midWidth = ((gameRowCellCount / 2) + 1) / 2;

		redPlayer.setLocation(midHeight, midWidth);
		bluePlayer.setLocation(midHeight, midWidth * 3);

		gameData[midHeight][midWidth] = redPlayer;
		gameData[midHeight][midWidth * 3] = bluePlayer;
		gameTable.setValueAt(redPlayer.getImage(), midHeight, midWidth);
		gameTable.setValueAt(bluePlayer.getImage(), midHeight, midWidth * 3);

		this.add(gameFieldPanel);

		gameFieldPanel.setBackground(Color.LIGHT_GRAY);
		gameFieldPanel.add(gameTable);
	}

	private void setupListeners()
	{
		this.addKeyListener(new KeyListener()
		{
			long LastShot = System.currentTimeMillis();
			final long threshold = 100; // 500msec = half second

			@Override
			public void keyTyped(KeyEvent keyboard)
			{
			}

			@Override
			public void keyReleased(KeyEvent keyboard)
			{
				pressedKeysWASD.remove(keyboard.getKeyCode());
				pressedKeysArrows.remove(keyboard.getKeyCode());

			}

			@Override
			public void keyPressed(KeyEvent keyboard)
			{
				int code = keyboard.getKeyCode();
				Integer val = Integer.valueOf(code);

				if (pressedKeysWASD.contains(val))
				{

				} else
				{
					switch (keyboard.getKeyChar())
					{
					case 'w':
						handleWKey();
						break;
					case 'a':
						handleAKey();
						break;
					case 's':
						handleSKey();
						break;
					case 'd':
						handleDKey();
						break;
					}
					pressedKeysWASD.add(keyboard.getKeyCode());
				}
				gameTable.updateUI();

				if (pressedKeysArrows.contains(val))
				{

				} else
				{
					switch (keyboard.getKeyCode())
					{
					case 38:
						handleUpKey();
						break;
					case 37:
						handleLeftKey();
						break;
					case 40:
						handleDownKey();
						break;
					case 39:
						handleRightKey();
						break;

					}
					pressedKeysArrows.add(keyboard.getKeyCode());
				}
				gameTable.updateUI();
			}
		});

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.WEST, gameFieldPanel, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, gameFieldPanel, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, gameFieldPanel, 0, SpringLayout.EAST, this);
	}

	private void handleWKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		redPlayer.setDirection(0);

		if (bluePlayer.getRow() == currentRow - 1 && bluePlayer.getColumn() == currentColumn)
		{
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
			return;
		}

		try
		{
			redPlayer.setLocation(currentRow - 1, currentColumn);

			gameTable.setValueAt(redPlayer.getImage(), currentRow - 1, currentColumn);
			gameData[currentRow - 1][currentColumn] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
		}
	}

	private void handleAKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		redPlayer.setDirection(270);

		if (bluePlayer.getRow() == currentRow && bluePlayer.getColumn() == currentColumn - 1)
		{
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
			return;
		}

		try
		{
			redPlayer.setLocation(currentRow, currentColumn - 1);

			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn - 1);
			gameData[currentRow][currentColumn - 1] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
		}
	}

	private void handleSKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		redPlayer.setDirection(180);

		if (bluePlayer.getRow() == currentRow + 1 && bluePlayer.getColumn() == currentColumn)
		{
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
			return;
		}

		try
		{
			redPlayer.setLocation(currentRow + 1, currentColumn);

			gameTable.setValueAt(redPlayer.getImage(), currentRow + 1, currentColumn);
			gameData[currentRow + 1][currentColumn] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
		}
	}

	private void handleDKey()
	{
		int currentRow = redPlayer.getRow();
		int currentColumn = redPlayer.getColumn();

		redPlayer.setDirection(90);

		if (bluePlayer.getRow() == currentRow && bluePlayer.getColumn() == currentColumn + 1)
		{
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
			return;
		}

		try
		{

			redPlayer.setLocation(currentRow, currentColumn + 1);

			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn + 1);
			gameData[currentRow][currentColumn + 1] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			redPlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = redPlayer;
		}

	}

	private void handleUpKey()
	{
		int currentRow = bluePlayer.getRow();
		int currentColumn = bluePlayer.getColumn();

		bluePlayer.setDirection(0);

		if (redPlayer.getRow() == currentRow - 1 && redPlayer.getColumn() == currentColumn)
		{
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
			return;
		}

		try
		{
			bluePlayer.setLocation(currentRow - 1, currentColumn);

			gameTable.setValueAt(bluePlayer.getImage(), currentRow - 1, currentColumn);
			gameData[currentRow - 1][currentColumn] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			bluePlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
		}
	}

	private void handleLeftKey()
	{
		int currentRow = bluePlayer.getRow();
		int currentColumn = bluePlayer.getColumn();

		bluePlayer.setDirection(270);

		if (redPlayer.getRow() == currentRow && redPlayer.getColumn() == currentColumn - 1)
		{
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
			return;
		}

		try
		{
			bluePlayer.setLocation(currentRow, currentColumn - 1);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn - 1);
			gameData[currentRow][currentColumn - 1] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			bluePlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
		}
	}

	private void handleDownKey()
	{
		int currentRow = bluePlayer.getRow();
		int currentColumn = bluePlayer.getColumn();

		bluePlayer.setDirection(180);

		if (redPlayer.getRow() == currentRow + 1 && redPlayer.getColumn() == currentColumn)
		{
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
			return;
		}

		try
		{
			bluePlayer.setLocation(currentRow + 1, currentColumn);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow + 1, currentColumn);
			gameData[currentRow + 1][currentColumn] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			bluePlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
		}
	}

	private void handleRightKey()
	{
		int currentRow = bluePlayer.getRow();
		int currentColumn = bluePlayer.getColumn();

		bluePlayer.setDirection(90);

		if (redPlayer.getRow() == currentRow && redPlayer.getColumn() == currentColumn + 1)
		{
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
			return;
		}

		try
		{

			bluePlayer.setLocation(currentRow, currentColumn + 1);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn + 1);
			gameData[currentRow][currentColumn + 1] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		} catch (IndexOutOfBoundsException error)
		{
			bluePlayer.setLocation(currentRow, currentColumn);
			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = bluePlayer;
		}

	}

	public void fireBullets(long lastShot, long threshold)
	{

		long now = System.currentTimeMillis();

		if (now - lastShot > threshold)
		{
			// System.out.println("FIRE");

			int blueRow = bluePlayer.getRow();
			int blueColumn = bluePlayer.getColumn();
			int blueDirection = bluePlayer.getDirection();

			Cell blueBullet = new Cell("BULLET", blueDirection);

			// System.out.println(blueDirection);
			switch (blueDirection)
			{
			case (0):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow - 1, blueColumn);
					gameData[blueRow - 1][blueColumn] = blueBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (90):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow, blueColumn + 1);
					gameData[blueRow][blueColumn + 1] = blueBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (180):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow + 1, blueColumn);
					gameData[blueRow + 1][blueColumn] = blueBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (270):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow, blueColumn - 1);
					gameData[blueRow][blueColumn - 1] = blueBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			}

			int redRow = redPlayer.getRow();
			int redColumn = redPlayer.getColumn();
			int redDirection = redPlayer.getDirection();

			Cell redBullet = new Cell("BULLET", redDirection);

			// System.out.println(redDirection);
			switch (redDirection)
			{
			case (0):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow - 1, redColumn);
					gameData[redRow - 1][redColumn] = redBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (90):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow, redColumn + 1);
					gameData[redRow][redColumn + 1] = redBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (180):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow + 1, redColumn);
					gameData[redRow + 1][redColumn] = redBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (270):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow, redColumn - 1);
					gameData[redRow][redColumn - 1] = redBullet;
				} catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			}

			panelLastShot = now;
		}
	}

	public long getPanelLastShot()
	{
		return panelLastShot;
	}

	public void checkCells(long lastCycle, long cycleThreshold)
	{
		long now = System.currentTimeMillis();

		if (now - lastCycle > cycleThreshold)
		{
			Cell currentCell;
//			long lastCycleD = System.currentTimeMillis();
//			final long cycleThresholdD = 1000; 
			for (int row = 0; row < gameData.length; row++)
			{
				for (int column = 0; column < gameData[0].length; column++)
				{
					currentCell = gameData[row][column];
					if (currentCell.getCellType().equals("BULLET"))
					{
						switch (currentCell.getDirection())
						{
						case 0:
							gameTable.setValueAt(currentCell.getImage(), row - 1, column);
							gameTable.setValueAt(blank.getImage(), row, column);
							gameData[row - 1][column] = currentCell;
							break;
						case 90:
							break;
						case 180:
							break;
						case 270:
							break;
						}
					}

				}
			}
			panelLastCycle = now;

		}

	}

	public long getPanelLastCycle()
	{
		return panelLastCycle;
	}
}
