
public abstract class Player extends ReversiBoard {

	private int pieceCount;
	public static int players = 0;
	private int color;
	//1 for X, 2 for O like gameBoard
	
	//not sure whether to make it a String[] or something else
	//to hold available moves, I was thinking each spot could hold
	//"x y" and we could parse it to update the gameBoard in ReversiBoard
	
	/** Calculates if x and y coordinate is an available move using
	 * the recursiveMoveCheck() method.
	 * 
	 * @param int y
	 * @param int x
	 */
	public abstract void calculateMoves(int x, int y);// calculateMoves is abstract because the moves will be different
	//for each player
	
	/** Determines if each coordinate on the gameBoard is an available move for
	 * the player.
	 * 
	 * @param int[][] x
	 *  
	 */
	public abstract void determineAvailableMoves(int[][] x);// determineAvailableMoves
	
	/** Plays the turn for player using 
	 * 
	 */
	public abstract void playTurn(int x, int y);
	
	/** Sets the players color based on the String entered in the parameter.
	 * Can be "Black" or "White".
	 * 
	 * @param String s 
	 * @return int
	 */
	public int setColor(String s)
	{
		if (s.equals("Black"))
			this.color = 1;
		if (s.equals("White"))
			this.color = 2;
		return color;
	}// setColor
	
	/** Returns color.
	 * 
	 * @return int
	 */
	public int getColor()
	{
		return color;
	}
	
	/** Returns the players color in String form.
	 * 
	 * @return String
	 */
	public String getColorXO()
	{
		String s = "";
		if (getColor() == 1)
			s = "X";
		else if (getColor() == 2)
			s = "O";
		return s;
	}
	
	/** Returns the players piece count.
	 * 
	 * @return int
	 */
	public int getPieceCount(){
		return pieceCount;
	}// getPieceCount
}//Player
