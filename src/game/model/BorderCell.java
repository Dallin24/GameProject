package game.model;

public class BorderCell extends Cell
{
	/**
	 * Creates a border cell
	 * 
	 * @param playerName
	 * @param cellType
	 * @param cellDirection
	 * @param row
	 * @param column
	 */
	public BorderCell()
	{
		super(null, "COMPUTER", "BORDER", 0, -1, -1, false);
	}
}
