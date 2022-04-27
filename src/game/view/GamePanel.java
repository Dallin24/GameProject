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

	private int totalCellCountHorizontal;
	private int totalCellCountVertical;

	private int cellWidth;
	private int cellHeight;

	private int gameRowCellCount;
	private int gameColumnCellCount;

	private Cell[][] gameData;

	private Cell redPlayer;
	private Cell bluePlayer;
	private Cell blank;

	private GameInfo redPlayerData;
	private GameInfo bluePlayerData;

	private Long panelLastShot;
	private Long panelLastCycle;

	private JPanel gameFieldPanel;
	private JTable gameTable;
	private JTable testTable;

	private Set<Integer> pressedKeysWASD;
	private Set<Integer> pressedKeysArrows;

	private SpringLayout overallLayout;
	private SpringLayout fieldLayout;

	public GamePanel(Controller app)
	{
		super();
		this.app = app;

		setScreenProportions();

		this.gameData = new Cell[gameRowCellCount][gameColumnCellCount];
		this.redPlayer = new Cell("RED", "PLAYER", 90, 0, 0);
		this.bluePlayer = new Cell("BLUE", "PLAYER", 270, 0, 0);
		this.blank = new Cell("BLANK");
		this.redPlayerData = new GameInfo(redPlayer, cellWidth, cellHeight);
		this.bluePlayerData = new GameInfo(bluePlayer, cellWidth, cellHeight);

		this.gameFieldPanel = new JPanel();
		this.gameTable = new JTable(gameRowCellCount, gameColumnCellCount)
		{
			public Class getColumnClass(int column)
			{
				return ImageIcon.class;
			}
		};
		this.testTable = new JTable(gameRowCellCount, gameColumnCellCount);

		this.panelLastShot = System.currentTimeMillis();
		this.panelLastCycle = System.currentTimeMillis();

		this.pressedKeysWASD = new TreeSet<Integer>();
		this.pressedKeysArrows = new TreeSet<Integer>();

		this.overallLayout = new SpringLayout();
		this.fieldLayout = new SpringLayout();

		setupPanel();
		setupListeners();
		setupLayout();

	}

	private void setupPanel()
	{
		this.setLayout(overallLayout);
		this.setBackground(Color.DARK_GRAY);

		gameFieldPanel.setLayout(fieldLayout);
		// this.add(testTable);

		setupInitialGameField();
	}

	private void setScreenProportions()
	{
		this.cellWidth = 38;
		this.cellHeight = 30;

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		int screenHeight = (int) size.getHeight();
		int screenWidth = (int) size.getWidth();

		this.totalCellCountHorizontal = (int) (screenWidth / cellWidth);
		this.totalCellCountVertical = (int) (screenHeight / cellHeight);

		gameColumnCellCount = totalCellCountHorizontal - 18;
		gameRowCellCount = totalCellCountVertical - 10;

		this.setSize(new Dimension((gameColumnCellCount + 18) * cellWidth, (gameRowCellCount + 10) * cellHeight));
	}

	private void setupInitialGameField()
	{
		gameTable.setRowHeight(30);
		// 1330, 840
		gameTable.setSize(new Dimension(38 * gameColumnCellCount, 30 * gameRowCellCount));
		gameTable.setCellSelectionEnabled(false);
		gameTable.setGridColor(Color.BLACK);
		gameTable.setBackground(Color.BLACK);
		gameTable.setEnabled(false);

		for (int row = 0; row < gameTable.getRowCount(); row++)
		{
			for (int column = 0; column < gameTable.getColumnCount(); column++)
			{
				gameData[row][column] = blank;
				gameTable.setValueAt(blank.getImage(), row, column);
			}
		}

		int midHeight = gameData.length / 2;
		int midWidth = (gameData[0].length / 2) / 2;

		redPlayer.setLocation(midHeight, midWidth);
		bluePlayer.setLocation(midHeight, midWidth * 3 + 1);

		gameData[midHeight][midWidth] = redPlayer;
		gameData[midHeight][midWidth * 3 + 1] = bluePlayer;
		gameTable.setValueAt(redPlayer.getImage(), midHeight, midWidth);
		gameTable.setValueAt(bluePlayer.getImage(), midHeight, midWidth * 3 + 1);

		this.add(gameFieldPanel);

		gameFieldPanel.setBackground(Color.LIGHT_GRAY);

		gameFieldPanel.add(redPlayerData);
		gameFieldPanel.add(gameTable);
		gameFieldPanel.add(bluePlayerData);

		redPlayerData.setSize(new Dimension(cellWidth * (((totalCellCountHorizontal - gameRowCellCount) / 2) - 2), cellHeight * (gameColumnCellCount - 2)));
		bluePlayerData.setSize(new Dimension(cellWidth * (((totalCellCountHorizontal - gameRowCellCount) / 2) - 2), cellHeight * (gameColumnCellCount - 2)));

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

				}
				else
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
				// gameTable.updateUI();

				if (pressedKeysArrows.contains(val))
				{

				}
				else
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

				// gameTable.updateUI();
			}
		});

		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	private void setupLayout()
	{
		// overallLayout.putConstraint(SpringLayout.WEST, testTable, 0,
		// SpringLayout.WEST, this);
		// overallLayout.putConstraint(SpringLayout.EAST, testTable, 0,
		// SpringLayout.EAST, this);

		fieldLayout.putConstraint(SpringLayout.NORTH, gameTable, -(gameTable.getHeight()), SpringLayout.SOUTH, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.WEST, gameTable, ((this.getWidth() - gameTable.getWidth()) / 2), SpringLayout.WEST, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.EAST, gameTable, -((this.getWidth() - gameTable.getWidth()) / 2), SpringLayout.EAST, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.SOUTH, gameTable, 0, SpringLayout.SOUTH, gameFieldPanel);

		overallLayout.putConstraint(SpringLayout.NORTH, gameFieldPanel, 0, SpringLayout.NORTH, this);
		overallLayout.putConstraint(SpringLayout.WEST, gameFieldPanel, 0, SpringLayout.WEST, this);
		overallLayout.putConstraint(SpringLayout.SOUTH, gameFieldPanel, 0, SpringLayout.SOUTH, this);
		overallLayout.putConstraint(SpringLayout.EAST, gameFieldPanel, 0, SpringLayout.EAST, this);

		fieldLayout.putConstraint(SpringLayout.NORTH, redPlayerData, -(gameTable.getHeight()) + cellHeight, SpringLayout.SOUTH, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.WEST, redPlayerData, cellWidth, SpringLayout.WEST, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.EAST, redPlayerData, -cellWidth, SpringLayout.WEST, gameTable);
		fieldLayout.putConstraint(SpringLayout.SOUTH, redPlayerData, -cellHeight, SpringLayout.SOUTH, gameFieldPanel);

		fieldLayout.putConstraint(SpringLayout.NORTH, bluePlayerData, -(gameTable.getHeight()) + cellHeight, SpringLayout.SOUTH, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.EAST, bluePlayerData, -cellWidth, SpringLayout.EAST, gameFieldPanel);
		fieldLayout.putConstraint(SpringLayout.WEST, bluePlayerData, cellWidth, SpringLayout.EAST, gameTable);
		fieldLayout.putConstraint(SpringLayout.SOUTH, bluePlayerData, -cellHeight, SpringLayout.SOUTH, gameFieldPanel);
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

			if (gameData[currentRow - 1][currentColumn].getCellType().equals("BULLET") && gameData[currentRow - 1][currentColumn].getOwner().equals("BLUE"))
			{
				redPlayerData.updatePlayerData("HEALTH", -10);
			}
			gameTable.setValueAt(redPlayer.getImage(), currentRow - 1, currentColumn);
			gameData[currentRow - 1][currentColumn] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;

		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow][currentColumn - 1].getCellType().equals("BULLET") && gameData[currentRow][currentColumn - 1].getOwner().equals("BLUE"))
			{
				redPlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn - 1);
			gameData[currentRow][currentColumn - 1] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow + 1][currentColumn].getCellType().equals("BULLET") && gameData[currentRow + 1][currentColumn].getOwner().equals("BLUE"))
			{
				redPlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(redPlayer.getImage(), currentRow + 1, currentColumn);
			gameData[currentRow + 1][currentColumn] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow][currentColumn + 1].getCellType().equals("BULLET") && gameData[currentRow][currentColumn + 1].getOwner().equals("BLUE"))
			{
				redPlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(redPlayer.getImage(), currentRow, currentColumn + 1);
			gameData[currentRow][currentColumn + 1] = redPlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow - 1][currentColumn].getCellType().equals("BULLET") && gameData[currentRow - 1][currentColumn].getOwner().equals("RED"))
			{
				bluePlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(bluePlayer.getImage(), currentRow - 1, currentColumn);
			gameData[currentRow - 1][currentColumn] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow][currentColumn - 1].getCellType().equals("BULLET") && gameData[currentRow][currentColumn - 1].getOwner().equals("RED"))
			{
				bluePlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn - 1);
			gameData[currentRow][currentColumn - 1] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow + 1][currentColumn].getCellType().equals("BULLET") && gameData[currentRow + 1][currentColumn].getOwner().equals("RED"))
			{
				bluePlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(bluePlayer.getImage(), currentRow + 1, currentColumn);
			gameData[currentRow + 1][currentColumn] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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

			if (gameData[currentRow][currentColumn + 1].getCellType().equals("BULLET") && gameData[currentRow][currentColumn + 1].getOwner().equals("RED"))
			{
				bluePlayerData.updatePlayerData("HEALTH", -10);
			}

			gameTable.setValueAt(bluePlayer.getImage(), currentRow, currentColumn + 1);
			gameData[currentRow][currentColumn + 1] = bluePlayer;
			gameTable.setValueAt(blank.getImage(), currentRow, currentColumn);
			gameData[currentRow][currentColumn] = blank;
		}
		catch (IndexOutOfBoundsException error)
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
			int blueRow = bluePlayer.getRow();
			int blueColumn = bluePlayer.getColumn();
			int blueDirection = bluePlayer.getDirection();

			Cell blueBullet = new Cell("BLUE", "BULLET", blueDirection, false);

			switch (blueDirection)
			{
			case (0):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow - 1, blueColumn);
					gameData[blueRow - 1][blueColumn] = blueBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (90):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow, blueColumn + 1);
					gameData[blueRow][blueColumn + 1] = blueBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (180):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow + 1, blueColumn);
					gameData[blueRow + 1][blueColumn] = blueBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (270):
				try
				{
					gameTable.setValueAt(blueBullet.getImage(), blueRow, blueColumn - 1);
					gameData[blueRow][blueColumn - 1] = blueBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			}

			int redRow = redPlayer.getRow();
			int redColumn = redPlayer.getColumn();
			int redDirection = redPlayer.getDirection();

			Cell redBullet = new Cell("RED", "BULLET", redDirection, false);

			// System.out.println(redDirection);
			switch (redDirection)
			{
			case (0):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow - 1, redColumn);
					gameData[redRow - 1][redColumn] = redBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (90):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow, redColumn + 1);
					gameData[redRow][redColumn + 1] = redBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (180):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow + 1, redColumn);
					gameData[redRow + 1][redColumn] = redBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			case (270):
				try
				{
					gameTable.setValueAt(redBullet.getImage(), redRow, redColumn - 1);
					gameData[redRow][redColumn - 1] = redBullet;
				}
				catch (ArrayIndexOutOfBoundsException error)
				{

				}
				break;
			}

			Cell currentCell;
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

			for (int row = 0; row < gameData.length; row++)
			{
				for (int column = 0; column < gameData[0].length; column++)
				{
					currentCell = gameData[row][column];
					testTable.setValueAt(currentCell.getCellType(), row, column);

					if (currentCell.getCellType().equals("BULLET") && !currentCell.getCellChecked())
					{
						if (redPlayer.getRow() == row && redPlayer.getColumn() == column /* && currentCell.getOwner().equals("BLUE") */)
						{
							gameData[row][column] = redPlayer;
							gameTable.setValueAt(redPlayer.getImage(), row, column);
							redPlayerData.updatePlayerData("HEALTH", -10);
							return;
						}

						if (bluePlayer.getRow() == row && bluePlayer.getColumn() == column /* && currentCell.getOwner().equals("RED") */)
						{
							gameData[row][column] = bluePlayer;
							gameTable.setValueAt(bluePlayer.getImage(), row, column);
							bluePlayerData.updatePlayerData("HEALTH", -10);
							return;
						}

						Cell newBlankCell = new Cell("BLANK");
						switch (currentCell.getDirection())
						{
						case 0:
							try
							{
								gameData[row - 1][column] = currentCell;
								gameTable.setValueAt(currentCell.getImage(), row - 1, column);
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
								currentCell.setCellChecked(true);
							}
							catch (ArrayIndexOutOfBoundsException error)
							{
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
							}
							break;
						case 90:
							try
							{
								gameData[row][column + 1] = currentCell;
								gameTable.setValueAt(currentCell.getImage(), row, column + 1);
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
								currentCell.setCellChecked(true);
							}
							catch (ArrayIndexOutOfBoundsException error)
							{
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
							}
							break;
						case 180:
							try
							{
								gameData[row + 1][column] = currentCell;
								gameTable.setValueAt(currentCell.getImage(), row + 1, column);
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
								currentCell.setCellChecked(true);
							}
							catch (ArrayIndexOutOfBoundsException error)
							{
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
							}
							break;
						case 270:
							try
							{
								gameData[row][column - 1] = currentCell;
								gameTable.setValueAt(currentCell.getImage(), row, column - 1);
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
								currentCell.setCellChecked(true);
							}
							catch (ArrayIndexOutOfBoundsException error)
							{
								gameData[row][column] = newBlankCell;
								gameTable.setValueAt(blank.getImage(), row, column);
							}
							break;
						}

					}

				}
			}
			panelLastCycle = now;

			for (int row = 0; row < gameData.length; row++)
			{
				for (int column = 0; column < gameData[0].length; column++)
				{
					currentCell = gameData[row][column];
					currentCell.setCellChecked(false);
				}
			}
		}

	}

	public long getPanelLastCycle()
	{
		return panelLastCycle;
	}
}
