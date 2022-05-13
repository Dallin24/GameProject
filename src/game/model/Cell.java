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
	protected String cellOwner;

	/**
	 * Holds the name of the cell
	 */
	protected String cellName;

	/**
	 * Holds the cell type
	 */
	protected String cellType;

	/**
	 * Holds boolean if cell was previously checked
	 */
	protected boolean cellChecked;

	/**
	 * Holds the cell image
	 */
	protected ImageIcon cellImage;

	/**
	 * Holds the health value of the cell
	 */
	protected int cellHealth;

	/**
	 * Holds the direction of cell in degrees
	 */
	protected int cellDirection;

	/**
	 * Holds the current row of the cell
	 */
	protected int cellRow;

	/**
	 * Holds the current column of the cell
	 */
	protected int cellColumn;

	/**
	 * Creates a Cell
	 * 
	 * @param cellName
	 * @param cellOwner
	 * @param cellType
	 * @param cellDirection
	 * @param cellRow
	 * @param cellColumn
	 * @param cellChecked
	 */
	public Cell(String cellName, String cellOwner, String cellType, int cellDirection, int cellRow, int cellColumn, boolean cellChecked)
	{
		this.cellName = cellName;
		this.cellOwner = cellOwner;
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellRow = cellRow;
		this.cellColumn = cellColumn;
		this.cellChecked = cellChecked;
		
		this.cellHealth = 100;
		this.cellImage = setImageIcon(cellType);
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

	public void setCellChecked(boolean newCheck)
	{
		this.cellChecked = newCheck;
	}

	public void setDirection(int newDirection)
	{
		this.cellDirection = newDirection;
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