
public class HumanPlayer extends Player {
	int col = super.getGameBoard()[0].length;
	int row = super.getGameBoard().length;
	int color;
	int otherColor;
	
	/** HumanPlayer constructor
	 * Sets color based on what the other player is.
	 * 
	 */
	public HumanPlayer(){
		if(players == 0)
		{
			color = setColor("Black");
			otherColor = 2;
		}
		else
		{
			color = setColor("White");
			otherColor = 1;
		}
		players++;
	}//HumanPlayer
	
	/** Flips the game pieces to the players color if the y and x entered
	 * are a valid move (gameBoard[y][x] == 3).
	 * 
	 * @param int color
	 * @param int y
	 * @param int x
	 */
	public void flipPieces(int color, int y, int x){

		//Nested for
		for(int pos=1; pos<4; pos++){
			for(int ang=0; ang<3; ang++){
				int x2 = x;
				int y2 = y;
				//Create temp array of gameboard
				int[][] temp = new int[8][8];
				for(int i=0;i<row;i++){
					for(int j=0;j<col;j++){
						temp[i][j] = gameBoard[i][j];
					}
				}
				gameBoard[y][x] = color;
				boolean foundPath = false;
				int count = 0;
				//Top
				recursive:
				while(count<9){
					count++;
					if(pos == 1){
						if(ang == 0){
							if(y2-1>=0 && x2-1>=0){
								if(gameBoard[y2-1][x2-1] == otherColor){
									temp[y2-1][x2-1] = color;
									x2 = x2-1;
									y2 = y2-1;
									continue recursive;
									
								}
								else if(gameBoard[y2-1][x2-1] == color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
								
							}
						}
						else if(ang == 1){
							if(y2-1>=0){
								if(gameBoard[y2-1][x2] == otherColor){
									temp[y2-1][x2] = color;
									y2 = y2-1;
									continue recursive;
								}
								else if(gameBoard[y2-1][x2]== color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
						else{
							if(y2-1>=0 && x2+1<col){
								if(gameBoard[y2-1][x2+1] == otherColor){
									temp[y2-1][x2+1] = color;
									x2 = x2+1;
									y2 = y2 -1;
									continue recursive;
								}
								else if(gameBoard[y2-1][x2+1] == color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
					}
					
					//Middle
					else if(pos == 2){
						if(ang == 0){
							if(x2-1>=0){
								if(gameBoard[y2][x2-1] == otherColor){
									temp[y2][x2-1] = color;
									x2 = x2-1;
									continue recursive;
								}
								else if(gameBoard[y2][x2-1] == color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
						else if(ang == 2){
							if(x2+1<col){
								if(gameBoard[y2][x2+1] == otherColor){
									temp[y2][x2+1] = color;
									x2 = x2+1;
									continue recursive;
								}
								else if(gameBoard[y2][x2+1] == color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
					}
					
					//Bottom
					else{
						if(ang == 0){
							if(y2+1>=0 && y2+1<row && x2-1>=0){
								if(gameBoard[y2+1][x2-1] == otherColor){
									temp[y2+1][x2-1] = color;
									x2 = x2-1;
									y2 = y2+1;
									continue recursive;
								}
								else if(gameBoard[y2+1][x2-1] == color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
						else if(ang == 1){
							if(y2+1>=0 && y2+1<row){
								if(gameBoard[y2+1][x2] == otherColor){
									temp[y2+1][x2] = color;
									y2 = y2+1;
									continue recursive;
								}
								else if(gameBoard[y2+1][x2]== color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
						else{
							if(y2+1>=0 && y2+1<row && x2+1<col){
								if(gameBoard[y2+1][x2+1] == otherColor){
									temp[y2+1][x2+1] = color;
									x2 = x2+1;
									y2 = y2+1;
									continue recursive;
								}
								else if(gameBoard[y2+1][x2+1] == color){
									foundPath = true;
									break;
								}
								else{
									break;
								}
							}
						}
					}// End of Recursion
				}// While
				
				if(foundPath){
					for(int i=0;i<row;i++){
						for(int j=0;j<col;j++){
							gameBoard[i][j] = temp[i][j];
						}
					}
				}
				
				
				
				
				
			}
		}
		
	}// flipPieces
	
	/** Takes in the parameters and calculates and sets the available moves
	 * 
	 * @param int y
	 * @param int x
	 * @param int pos
	 * @param int ang
	 */
	public void recursiveMoveCheck(int y, int x, int pos, int ang){
		//Top
		if(pos == 1){
			if(ang == 0){
				if(y-1>=0 && x-1>=0){
					if(gameBoard[y-1][x-1] == 0){
						gameBoard[y-1][x-1] = 3;
					}
					else if(gameBoard[y-1][x-1] == otherColor){
						this.recursiveMoveCheck(y-1, x-1, 1, 0);
					}
				}
			}
			else if(ang == 1){
				if(y-1>=0){
					if(gameBoard[y-1][x] == 0){
						gameBoard[y-1][x] =3;
					}
					else if(gameBoard[y-1][x]== otherColor){
						this.recursiveMoveCheck(y-1, x, 1, 1);
					}
				}
			}
			else{
				if(y-1>=0 && x+1<col){
					if(gameBoard[y-1][x+1] == 0){
						gameBoard[y-1][x+1] =3;
					}
					else if(gameBoard[y-1][x+1] == otherColor){
						this.recursiveMoveCheck(y-1, x+1, 1, 2);
					}
				}
			}
		}
		
		//Middle
		else if(pos == 2){
			if(ang == 0){
				if(x-1>=0){
					if(gameBoard[y][x-1] == 0){
						gameBoard[y][x-1] = 3;
					}
					else if(gameBoard[y][x-1] == otherColor){
						this.recursiveMoveCheck(y, x-1, 2, 0);
					}
				}
			}
			else if(ang == 2){
				if(x+1<col){
					if(gameBoard[y][x+1] == 0){
						gameBoard[y][x+1] = 3;
					}
					else if(gameBoard[y][x+1] == otherColor){
						this.recursiveMoveCheck(y, x+1, 2, 2);
					}
				}
			}
		}
		
		//Bottom
		else{
			if(ang == 0){
				if(y+1>=0 && y+1<row && x-1>=0){
					if(gameBoard[y+1][x-1] == 0){
						gameBoard[y+1][x-1] = 3;
					}
					else if(gameBoard[y+1][x-1] == otherColor){
						this.recursiveMoveCheck(y+1, x-1, 3, 0);
					}
				}
			}
			else if(ang == 1){
				if(y+1>=0 && y+1<row){
					if(gameBoard[y+1][x] == 0){
						gameBoard[y+1][x] =3;
					}
					else if(gameBoard[y+1][x]== otherColor){
						this.recursiveMoveCheck(y+1, x, 3, 1);
					}
				}
			}
			else{
				if(y+1>=0 && y+1<row && x+1<col){
					if(gameBoard[y+1][x+1] == 0){
						gameBoard[y+1][x+1] =3;
					}
					else if(gameBoard[y+1][x+1] == otherColor){
						this.recursiveMoveCheck(y+1, x+1, 3, 2);
					}
				}
			}
		}
	}// recursiveMoveCheck
	

	@Override
	public void calculateMoves(int x, int y){
		if (color == 1)
			otherColor = 2;	
		else if(color == 2)
			otherColor = 1;

		
		//Bottom
		for(int k=0; k<3; k++){
    		if(y+1<row && x-1+k<col && x-1+k>=0){
    			if(gameBoard[y+1][x-1+k] == otherColor){
    				recursiveMoveCheck(y+1, x-1+k, 3, k);
    			}
    			
    		}
    	}
		//Middle
    	for(int j=0; j<3; j++){
    		if(x-1+j>=0 && x-1+j<col && j!=1){
    			if(gameBoard[y][x-1+j] == otherColor){
    				recursiveMoveCheck(y, x-1+j, 2, j);
    			}
    			
    		}
    	}
    	//Top
    	for(int l=0; l<3; l++) {
    		if(y-1>=0 && x-1+l>=0 && x-1+l<col){
    			if(gameBoard[y-1][x-1+l] == otherColor){
    				recursiveMoveCheck(y-1, x-1+l, 1, l);
    			}
    			
    		}
    	}

	}
	
	
	@Override
	public void determineAvailableMoves(int[][] x){
		for (int i = 0; i < x.length; i++)
		{
			for (int j = 0; j < x[0].length; j++)
			{
				if(gameBoard[i][j] == color){
					calculateMoves(j, i);
				}
			}
		}// for
		
	}//determineAvailableMoves
	
	@Override
	public void playTurn(int y, int x){
			flipPieces(color, y, x);
		
	}
}

