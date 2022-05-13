package game.model;

public class BulletCell extends Cell
{
	public BulletCell(String cellOwner, int cellDirection, boolean cellChecked)
	{
		super(null, cellOwner, "BULLET", cellDirection, -1, -1, cellChecked);
	}
	
}
