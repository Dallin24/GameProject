package game.model;

import javax.swing.ImageIcon;

public class Cell
{
	private String cellOwner;
	private String cellName;
	private String cellType;
	
	private boolean cellChecked;

	private ImageIcon cellImage;

	private int cellHealth;
	private int cellDirection;
	private int cellRow;
	private int cellColumn;
	
	public Cell(String cellType)
	{
		this.cellType = cellType;
		this.cellImage = setImageIcon(cellType);
	}

	public Cell(String cellOwner, String cellType, int cellDirection, boolean cellChecked)
	{
		this.cellOwner = cellOwner;
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
		this.cellChecked = cellChecked;
	}

	public Cell(String playerName, String cellType, int cellDirection, int row, int column)
	{
		this.cellName = playerName;
		this.cellType = cellType;
		this.cellDirection = cellDirection;
		this.cellImage = setImageIcon(cellType);
		this.cellHealth = 100;
	}

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
			} else
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
		} else
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