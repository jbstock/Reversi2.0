
public class ReversiBoard implements Board{
	
	//rows and cols variables used to make loops more readable later
	final static int rows = 8;
	final static int cols = 8;
	protected static int[][] gameBoard = new int[rows][cols];
	//for gameBoard, 0 signifies empty space, 1 signifies X, 2 signifies O, 3 signifies "_"
	private String[][] displayBoard = new String[rows][cols];
	
	/** Welcomes the player and states the directions*/
	public void welcome(){
		System.out.println("");
		System.out.println("Welcome to Reversi!  Moves should be entered int \"[row] [column]\" format.");
	}// welcome
	
	/** ReversiBoard constructor */
	public ReversiBoard(){
		this.setUp();
	}// ReversiBoard
	
	/** Sets up the ReversiBoard to start out */
	public void setUp() {
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j ++)
			{
				gameBoard[i][j] = 0;
				displayBoard[i][j]= ".";
			}
		}
		gameBoard[3][3] = 1;
		gameBoard[3][4] = 2;
		gameBoard[4][4] = 1;
		gameBoard[4][3] = 2;
		displayBoard[3][3] = "X";
		displayBoard[3][4] = "O";
		displayBoard[4][4] = "X";
		displayBoard[4][3] = "O";
	}// setUp
	
	/** Resets the GameBoard */
	public void gameBoardReset() {
		for (int i = 0; i < rows; i ++)
		{
			for (int j = 0; j < cols; j ++)
			{
				if (gameBoard[i][j] == 3)
					gameBoard[i][j] = 0;
			}
		}
	}
	
	/** Syncs the displayBoard with the gameBoard
	 * i.e. if gameBoard has this, make displayBoard show that
	 */
	public void syncBoards() {
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (gameBoard[i][j] == 0)
					displayBoard[i][j] = ".";
				else if (gameBoard[i][j] == 1)
					displayBoard[i][j] = "X";
				else if (gameBoard[i][j] == 2)
					displayBoard[i][j] = "O";
				else if (gameBoard[i][j] == 3)
					displayBoard[i][j] = "_";
			}
		}
	}//syncBoards
	
	/** Determines whether the game is completed by checking if there
	 * are any more available moves.
	 */
	public int isOver() {
		//Need to account for no moves?
		int empty = 0;
		int underscores = 0;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (gameBoard[i][j] == 0)
				{
					empty++;
				}
			}
		}
		//checks for any 3's or "_" signifying an available move
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (gameBoard[i][j] == 3)
					underscores++;
			}
		}
		return underscores;
	}//isOver
	
	/** Prints the board to the player */
	public void printBoard() {
		System.out.println("");
		for (int i = 0; i < rows; i++)
		{
			if (i == 0)
				System.out.print("  ");
			System.out.print(i+1 +" ");
		}
		System.out.println(" ");
		
		//Need to print the ., X, _, and /
		//We want a temp board that holds what we want to print,
		//not the game data
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (j == 0)
				{
					System.out.print(i+1 + " ");
					System.out.print(displayBoard[i][j] + " ");
				}
				else if (j == cols-1)
				{
					System.out.println(displayBoard[i][j]);
				}
				else 
					System.out.print(displayBoard[i][j] + " ");
				}
		}
		System.out.println("");
	}// PrintBoard

	/** Syncs the board and prints it with the syncBoards() and printBoard()
	 * methods.
	 */
	public void syncPrint()
	{
		syncBoards();
		printBoard();
	}
	
	/** Returns the gameBoard. */
	public int[][] getGameBoard() {
		return gameBoard;
	}//getGameBoard

}//ReversiBoard

