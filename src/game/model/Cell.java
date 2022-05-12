package game.model;

import javax.swing.ImageIcon;

/**
 * Contains cell information for game
 * 
 * @author Dallin Gibbs
 */
public class Cell
{
	/**
	 * Holds the owner of the cell, usually for bullets
	 */
	private String cellOwner;

	/**
	 * Holds the name of the cell
	 */
	private String cellName;

	/**
	 * Holds the cell type
	 */
	private String cellType;

	/**
	 * Holds boolean if cell was previously checked
	 */
	private boolean cellChecked;

	/**
	 * Holds the cell image
	 */
	private ImageIcon cellImage;

	/**
	 * Holds the health value of the cell
	 */
	private int cellHealth;

	/**
	 * Holds the direction of cell in degrees
	 */
	private int cellDirection;

	/**
	 * Holds the current row of the cell
	 */
	private int cellRow;

	/**
	 * Holds the current column of the cell
	 */
	private int cellColumn;

	/**
	 * Creates a blank cell
	 * 
	 * @param cellType
	 */
	public Cell(String cellType)
	{
		this.cellType = cellType;
		this.cellImage = setImageIcon(cellType);
	}

	/**
	 * Creates bullet cell
	 * 
	 * @param cellOwner
	 * @param cellType
	 * @param cellDirection
	 * @param cellChecked
	 */
	public Cell(String cellOwner, String cellType, int cellDirection, boolean cellChecked)
	{
		this.cellOwner = cellOwner;
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
		this.cellChecked = cellChecked;
	}

	/**
	 * Creates a player cell
	 * 
	 * @param playerName
	 * @param cellType
	 * @param cellDirection
	 * @param row
	 * @param column
	 */
	public Cell(String playerName, String cellType, int cellDirection, int row, int column)
	{
		this.cellName = playerName;
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
		this.cellHealth = 100;
	}

	/**
	 * 
	 * Set image for cell
	 * 
	 * @param cellType
	 * @return
	 */
	protected ImageIcon setImageIcon(String cellType)
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
			}
			else
			{
				cellImage = new ImageIcon(getClass().getResource("/game/view/images/BlueLeftNormal.png"));
			}
			break;
		case "BULLET":
			cellImage = new ImageIcon(getClass().getResource("/game/view/images/BulletNormal.png"));
			break;
		case "BORDER":
			cellImage = new ImageIcon(getClass().getResource("/game/view/images/OrangeBorder.png"));
			break;
		}

		return cellImage;
	}

	/**
	 * Set image for player cell
	 * 
	 * @param cellName
	 * @param cellType
	 * @param direction
	 */
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
		}
		else
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

	/**
	 * Sets image for player cell when firing
	 * 
	 * @param cellName
	 * @param cellType
	 * @param direction
	 * @param isFire
	 */
	private void setImageIcon(String cellName, String cellType, int direction, boolean isFire)
	{
		ImageIcon playerImage = new ImageIcon();

		if (cellName.equals("RED"))
		{
			switch (direction)
			{
			case 0:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedUpFire.png"));
				break;
			case 90:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedRightFire.png"));
				break;
			case 180:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedDownFire.png"));
				break;
			case 270:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/RedLeftFire.png"));
				break;
			}
		}
		else
		{
			switch (direction)
			{
			case 0:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueUpFire.png"));
				break;
			case 90:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueRightFire.png"));
				break;
			case 180:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueDownFire.png"));
				break;
			case 270:
				playerImage = new ImageIcon(getClass().getResource("/game/view/images/BlueLeftFire.png"));
				break;
			}
		}

		this.cellImage = playerImage;
	}

	public void setCellType(String cellType)
	{
		this.cellType = cellType;
	}

	public void setCellChecked(boolean newCheck)
	{
		this.cellChecked = newCheck;
	}

	public void setDirection(int newDirection)
	{
		this.cellDirection = newDirection;
		if (this.cellType.equals("PLAYER"))
		{
			setImageIcon(this.cellName, this.cellType, newDirection);
		}
	}

	public void setDirection(int direction, boolean isFire)
	{
		this.cellDirection = direction;
		if (this.cellType.equals("PLAYER"))
		{
			setImageIcon(this.cellName, this.cellType, direction, isFire);
		}
	}

	public void setLocation(int row, int column)
	{
		this.cellRow = row;
		this.cellColumn = column;
	}

	public void setHealth(int healthPoints)
	{
		this.cellHealth = healthPoints;
	}

	public ImageIcon getImage()
	{
		return this.cellImage;
	}

	public String getCellType()
	{
		return this.cellType;
	}

	public boolean getCellChecked()
	{
		return this.cellChecked;
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

	public int getHealth()
	{
		return this.cellHealth;
	}

	public String getPlayerName()
	{
		return this.cellName;
	}

	public String getOwner()
	{
		return cellOwner;
	}
}