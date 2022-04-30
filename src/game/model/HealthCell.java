package game.model;

import javax.swing.ImageIcon;

public class HealthCell extends Cell
{
	private ImageIcon cellImage;

	private String color;

	public HealthCell(String color)
	{
		super();

		this.color = color;
		
		if (color.equals("GREEN"))
		{
			this.cellImage = new ImageIcon(getClass().getResource("/game/view/images/Green.png"));
		}
		else
		{
			this.cellImage = new ImageIcon(getClass().getResource("/game/view/images/White.png"));
		}
		
		
	}

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

		this.color = color;
		
		return cellColor;
	}
	
	public ImageIcon getColor()
	{
		return this.cellImage;
	}
}
