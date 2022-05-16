package game.model;

import javax.swing.ImageIcon;

public class PlayerCell extends Cell
{
	/**
	 * Creates a player cell
	 * 
	 * @param playerName
	 * @param cellType
	 * @param cellDirection
	 * @param row
	 * @param column
	 */
	public PlayerCell(String playerName, String cellType, int cellDirection, int cellRow, int cellColumn)
	{
		super(playerName, "USER", "PLAYER", cellDirection, cellRow, cellColumn, false);
	}

	private void setImageIcon(String cellName, int direction, boolean isFire)
	{
		ImageIcon playerImage = new ImageIcon();

		if (isFire)
		{
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
		else
		{
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

	}

	@Override
	public void setDirection(int newDirection)
	{
		cellDirection = newDirection;
		this.setImageIcon(cellName, newDirection, false);
	}
	
	public void setDirection(int newDirection, boolean isFire)
	{
		cellDirection = newDirection;
		this.setImageIcon(cellName, newDirection, isFire);
	}
}
