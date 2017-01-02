import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Reversi {

	public static void main(String[] args) {
		
		if (args.length != 2)
		{
			System.out.println("Wrong format");
			System.exit(0);
		}
		
		for (int i = 0; i < 2; i++)
		{
			if (!((args[i].equalsIgnoreCase("Human"))||(args[i].equalsIgnoreCase("RandomComputerPlayer"))))
			{
				System.out.println("Wrong format");
				System.exit(0);
			}
		}
		
		ReversiBoard r = new ReversiBoard();
		
		Player p1 = null,p2 = null;
		
		if (args[0].equalsIgnoreCase("Human"))
		{
			p1 = new HumanPlayer();
		}
		else if (args[0].equalsIgnoreCase("RandomComputerPlayer"))
		{
			p1 = new RandomComputerPlayer();
		}
		
		if (args[1].equalsIgnoreCase("Human"))
		{
			p2 = new HumanPlayer();
		}
		else if (args[1].equalsIgnoreCase("RandomComputerPlayer"))
		{
			p2 = new RandomComputerPlayer();
		}

		r.welcome();
		
		boolean isOver = false;
		boolean p1Turn = true;
		boolean p2Turn = true;
		int p1isOver = 0;
		int p2isOver = 0;
		
		while (isOver == false)
		{
			p1isOver = 0;
			p2isOver = 0;
			p1Turn = true;
			p2Turn = true;
			p1.determineAvailableMoves(r.getGameBoard());
			p1isOver += r.isOver();
			r.gameBoardReset();
			p2.determineAvailableMoves(r.getGameBoard());
			p2isOver += r.isOver();
			r.gameBoardReset();
			
			if (p1isOver == 0 && p2isOver > 0)
				p1Turn = false;
			else if (p1isOver > 0 && p2isOver == 0)
				p2Turn = false;
			else if (p1isOver == 0 && p2isOver == 0)
			{
				p1Turn = false;
				p2Turn = false;
			}
			
			p1isOver = 0;
			p2isOver = 0;
			while (p1Turn)
			{
				p1.determineAvailableMoves(r.getGameBoard());
				p1isOver += r.isOver();
				r.syncPrint();
	
				if (p1 instanceof HumanPlayer)
				{
					if (p1isOver > 0)
					{
						Scanner input = new Scanner(System.in);
						System.out.print("Enter your move, " + p1.getColorXO() + " player: ");
						String s = input.nextLine();
						s = s.trim();
						String [] s2 = null;
						if (s.length() != 0)
						{
							s2 = s.split("\\s+");
						}
						else if (s.length() == 0)
						{
							System.out.println("");
							System.out.println("Invalid move.");
							continue;
						}
						if (s2.length != 2)
						{
							System.out.println("");
							System.out.println("Invalid move.");
							continue;
						}
						int x = 0;
						int y = 0;
						try{
							x = Integer.parseInt(s2[0]);
							y = Integer.parseInt(s2[1]);
						}
						catch(NumberFormatException ex){
							System.out.println("");
							System.out.println("Invalid move.");
							continue;
						}
						
						if ((x > 0 && x < 9) && (y > 0 && y < 9))
						{
							if (ReversiBoard.gameBoard[x-1][y-1] == 3)
							{
								p1.playTurn(x-1,y-1);
								r.syncBoards();
								r.gameBoardReset();
								p1Turn = false;
							}
							else
							{
								System.out.println("");
								System.out.println("Invalid move.");
							}
						}
						else
						{
							System.out.println("");
							System.out.println("Invalid move.");
						}
					}
					else 
						p1Turn = false;
				}
				else if (p1 instanceof RandomComputerPlayer)
				{
					int moves2 = 0;
					int moves3 = 0;
					for (int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							if(ReversiBoard.gameBoard[i][j] == 3)
							{
								moves2++;
							}
						}
					}
					String [] moves = new String[moves2];
					for (int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							if(ReversiBoard.gameBoard[i][j] == 3)
							{
								moves[moves3] = "" + i + " " + j;
								moves3++;
							}
						}
					}
					int y = 0;
					int x = 0;
					if (moves.length == 0)
					{
						r.gameBoardReset();
						p1Turn = false;
						continue;
					}
					else if (moves.length == 1)
					{
						String s = moves[0];
						String s1 = s.substring(0, 1);
						String s2 = s.substring(2);
						y = Integer.parseInt(s1);
						x = Integer.parseInt(s2);
					}
					else
					{
						Random rand = new Random();
						int m = rand.nextInt(moves.length);
						String s = moves[m];
						String s1 = s.substring(0,1);
						String s2 = s.substring(2);
						y = Integer.parseInt(s1);
						x = Integer.parseInt(s2);
					}
					/*try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}*/
					p1.playTurn(y, x);
					r.gameBoardReset();
					r.syncBoards();
					p1Turn = false;
				}
			}
			while (p2Turn)
			{
				p2.determineAvailableMoves(r.getGameBoard());
				p2isOver += r.isOver();
				r.syncPrint();
				
				if (p2 instanceof HumanPlayer)
				{
					if (p2isOver > 0)
					{
						Scanner input = new Scanner(System.in);
						System.out.print("Enter your move, " + p2.getColorXO() + " player: ");
						String s = input.nextLine();
						s = s.trim();
						String [] s2 = null;
						if (s.length() != 0)
						{
							s2 = s.split("\\s+");
						}
						else if (s.length() == 0)
						{
							System.out.println("");
							System.out.println("Invalid move.");
							continue;
						}
						if (s2.length != 2)
						{
							System.out.println("");
							System.out.println("Invalid move.");
							continue;
						}
						int x = 0;
						int y = 0;
						try{
							x = Integer.parseInt(s2[0]);
							y = Integer.parseInt(s2[1]);
						}
						catch(NumberFormatException ex){
							System.out.println("");
							System.out.println("Invalid move.");
							continue;
						}
						
						if ((x > 0 && x < 9) && (y > 0 && y < 9))
						{
							if (ReversiBoard.gameBoard[x-1][y-1] == 3)
							{
								p2.playTurn(x-1,y-1);
								r.syncBoards();
								r.gameBoardReset();
								p2Turn = false;
							}
							else
							{
								System.out.println("");
								System.out.println("Invalid move.");
							}
						}
						else
						{
							System.out.println("");
							System.out.println("Invalid move.");
						}
					}
					else
						p2Turn = false;
				}
				else if (p2 instanceof RandomComputerPlayer)
				{
					int moves2 = 0;
					int moves3 = 0;
					for (int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							if(ReversiBoard.gameBoard[i][j] == 3)
							{
								moves2++;
							}
						}
					}
					String [] moves = new String[moves2];
					for (int i = 0; i < 8; i++)
					{
						for (int j = 0; j < 8; j++)
						{
							if(ReversiBoard.gameBoard[i][j] == 3)
							{
								moves[moves3] = "" + i + " " + j;
								moves3++;
							}
						}
					}
					int y = 0;
					int x = 0;
					if (moves.length == 0)
					{
						r.gameBoardReset();
						p2Turn = false;
						continue;
					}
					else if (moves.length == 1)
					{
						String s = moves[0];
						String s1 = s.substring(0, 1);
						String s2 = s.substring(2);
						y = Integer.parseInt(s1);
						x = Integer.parseInt(s2);
					}
					else
					{
						Random rand = new Random();
						int m = rand.nextInt(moves.length);
						String s = moves[m];
						String s1 = s.substring(0,1);
						String s2 = s.substring(2);
						y = Integer.parseInt(s1);
						x = Integer.parseInt(s2);
					}
					/*try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}*/
					p2.playTurn(y, x);
					r.gameBoardReset();
					r.syncBoards();
					p2Turn = false;
				}
			}
			p1isOver = 0;
			p2isOver = 0;
			p1.determineAvailableMoves(r.getGameBoard());
			p1isOver += r.isOver();
			r.gameBoardReset();
			p2.determineAvailableMoves(r.getGameBoard());
			p2isOver += r.isOver();
			r.gameBoardReset();
			
			if (p1isOver == 0 && p2isOver == 0)
			{
				isOver = true;
				break;
			}
			else if (p1isOver == 0  & p2isOver > 0)
			{
				p2Turn = true;
				isOver = false;
			}
			else if (p2isOver == 0  & p1isOver > 0)
			{
				p1Turn = true;
				isOver = false;
			}
		}
		r.gameBoardReset();
		r.printBoard();
		int p1score = 0;
		int p2score = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (ReversiBoard.gameBoard[i][j] == p1.getColor())
					p1score++;
				else if (ReversiBoard.gameBoard[i][j] == p2.getColor())
					p2score++;
			}
		}
		System.out.println("Player X score: " + p1score);
		System.out.println("Player O score: " + p2score);
		System.out.println("");
		if (p1score > p2score)
			System.out.println("Player " + p1.getColorXO() + " wins!");
		else if (p2score > p1score)
			System.out.println("Player " + p2.getColorXO() + " wins!");
		else if (p1score == p2score)
			System.out.println("Tie game!");
		System.exit(0);
	}
}


		
		