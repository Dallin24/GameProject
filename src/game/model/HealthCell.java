package game.model;

import javax.swing.ImageIcon;

/**
 * HealthCell data object for PlayerInfo panel
 * 
 * @author Dallin Gibbs
 */
public class HealthCell extends Cell
{
	/**
	 * Holds the image for the cell
	 */
	private ImageIcon cellImage;

	/**
	 * Creates HealthCell Object with input color
	 * 
	 * @param color
	 */
	public HealthCell(String color)
	{
		super(color);

		if (color.equals("GREEN"))
		{
			this.cellImage = new ImageIcon(getClass().getResource("/game/view/images/Green.png"));
		}
		else
		{
			this.cellImage = new ImageIcon(getClass().getResource("/game/view/images/White.png"));
		}

	}

	/**
	 * Overridden method to set cell image
	 */
	@Override
	protected ImageIcon setImageIcon(String color)
	{
		ImageIcon cellColor;
		if (color.equals("GREEN"))
		{
			cellColor = new ImageIcon(getClass().getResource("/game/view/images/Green.png"));
		}
		else
		{
			cellColor = new ImageIcon(getClass().getResource("/game/view/images/White.png"));
		}

		return cellColor;
	}

	/**
	 * @return ImageIcon color of cell
	 */
	public ImageIcon getColor()
	{
		return this.cellImage;
	}
}
