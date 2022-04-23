package game.model;

import javax.swing.ImageIcon;

public class Cell
{
	private String cellName;
	private String cellType;

	private ImageIcon cellImage;

	private int cellDirection;
	private int cellRow;
	private int cellColumn;

	public Cell(String cellType)
	{
		this.cellType = cellType;
		this.cellImage = setImageIcon(cellType);
	}

	public Cell(String cellType, int cellDirection)
	{
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
	}

	public Cell(String playerName, String cellType, int cellDirection, int row, int column)
	{
		this.cellName = playerName;
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
	}

	private ImageIcon setImageIcon(String cellType)
	{
		ImageIcon cellImage = new ImageIcon(getClass().getResource("/game/view/images/Background.png"));
		switch (cellType)
		{
		case "BLANK":
			cellImage = new ImageIcon(getClass().getResource("/game/view/images/Background.png"));
			break;
		case "PLAYER":
			if (this.cellName.equals("RED"))
			{
				cellImage = new ImageIcon(getClass().getResource("/game/view/images/RedRightNormal.png"));
			} else
			{
				cellImage = new ImageIcon(getClass().getResource("/game/view/images/BlueLeftNormal.png"));
			}

			break;
		}

		return cellImage;
	}

	private void setImageIcon(String cellName, String cellType, int direction)
	{
		ImageIcon playerImage = new ImageIcon();

		if (cellName.equals("RED"))
		{
			switch (direction)
			{
			case 0:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedUpNormal.png"));
				break;
			case 90:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedRightNormal.png"));
				break;
			case 180:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedDownNormal.png"));
				break;
			case 270:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedLeftNormal.png"));
				break;
			}
		} else
		{
			switch (direction)
			{
			case 0:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueUpNormal.png"));
				break;
			case 90:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueRightNormal.png"));
				break;
			case 180:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueDownNormal.png"));
				break;
			case 270:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueLeftNormal.png"));
				break;
			}
		}
		
		this.cellImage = playerImage;
	}

	public void setCellType(String cellType)
	{
		this.cellType = cellType;
	}

	public void setDirection(int newDirection)
	{
		this.cellDirection = newDirection;
		if (this.cellType.equals("PLAYER"))
		{
			setImageIcon(this.cellName, this.cellType, newDirection);
		}
	}

	public void setLocation(int row, int column)
	{
		this.cellRow = row;
		this.cellColumn = column;
	}

	public ImageIcon getImage()
	{
		return this.cellImage;
	}

	public String getCellType()
	{
		return this.cellType;
	}

	public int getDirection()
	{
		return this.cellDirection;
	}

	public int getRow()
	{
		return this.cellRow;
	}

	public int getColumn()
	{
		return this.cellColumn;
	}
}