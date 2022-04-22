package game.model;

import javax.swing.ImageIcon;

public class Cell
{
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
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
	}
	
	private ImageIcon setImageIcon(String cellType)
	{
		ImageIcon cellImage = new ImageIcon(getClass().getResource("/game/view/images/Bullet.png"));
		switch(cellType)
		{
			case "BLANK":
				cellImage = new ImageIcon(getClass().getResource("/game/view/images/Bullet.png"));
				break;
			case "REDPLAYER":
				cellImage = new ImageIcon(getClass().getResource("/game/view/images/Red.png"));
				break;
		}
		
		return cellImage;
	}

	public void setCellType(String cellType)
	{
		this.cellType = cellType;
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