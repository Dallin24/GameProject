package game.model;

import javax.swing.ImageIcon;

public class HealthCell extends Cell
{
	private ImageIcon cellImage;

	private String color;

	public HealthCell()
	{
		super();

		cellImage = new ImageIcon(getClass().getResource("/game/view/images/Green.png"));
		color = "GREEN";
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

		return cellColor;
	}

	public void setColor(String color)
	{
		this.color = color;
	}
	
	public String getColor()
	{
		return color;
	}
}
