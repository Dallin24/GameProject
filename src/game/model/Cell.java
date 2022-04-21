package game.model;

public class Cell
{
	private String cellType;

	private int cellDirection;

	public Cell(String cellType, int cellDirection)
	{
		this.cellType = cellType;
		this.cellDirection = cellDirection;
	}
	
	public Cell(String playerName, String cellType, int cellDirection)
	{
		this.cellType = cellType;
		this.cellDirection = cellDirection;
	}

	public void setCellType(String cellType)
	{
		this.cellType = cellType;
	}
	
	public void setDirection(int newDirection)
	{
		this.cellDirection = newDirection;
	}

	public String getCellType()
	{
		return this.cellType;
	}

	public int getDirection()
	{
		return this.cellDirection;
	}
	
}