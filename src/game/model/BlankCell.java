package game.model;

public class BlankCell extends Cell
{
	/**
	 * Creates a blank cell
	 * 
	 * @param playerName
	 * @param cellType
	 * @param cellDirection
	 * @param row
	 * @param column
	 */
	public BlankCell()
	{
		super(null, "COMPUTER", "BLANK", 0, -1, -1, false);
	}
}
