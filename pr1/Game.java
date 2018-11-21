package pr1;

import java.util.Random;

import util.ArrayAsList;

public class Game {
		private Board board;
		private int size;//Board is size*size(number of cells per row/column)
		private int initCells;
		private int score, maxToken;
		private Random myRandom;
		private ArrayAsList freePos;
		private MoveResults results;	
		private boolean lost;
		
		public Game(int size, int initCells, Random myRandom){
			this.size = size;
			this.initCells = initCells;
			this.myRandom = myRandom;
			this.freePos = new ArrayAsList(this.size);
			Reset();
			System.out.println(toString());
		}
		
		public boolean Move(Direction dir){
			this.results = board.executeMove(dir);
			if(this.results.getMax() > this.maxToken){
				this.maxToken = this.results.getMax();
			}
			this.score = this.score + this.results.getPoints();
			if(results.hasMoved()){
				createCell(1);
			}
			hasLost();
			return this.lost;
			
		}
		
		public void Reset(){
			results = new MoveResults();
			//reset the board
			this.board = new Board(this.size);
			this.score = 0;
			maxToken = 0;
			this.lost = false;
			//fill init cells
			createCell(initCells);
		}
		
		public void createCell(int x){
			Position free; 
			int value, nextInt;
			for(int i = 0; i < x; i++){
				freePositions();
				ArrayAsList.shuffle(this.freePos, this.myRandom);
				free = (Position)ArrayAsList.choice(this.freePos, this.myRandom);
				nextInt = myRandom.nextInt(10);
				if(nextInt == 0){
					value = 4;
				}
				else{
					value = 2;
				}
				this.board.setCell(free,value);
				if(value > this.maxToken){
					this.maxToken = value;
				}
			}
		}
		
		public String toString(){
			boolean won = false;
			String res = new String();
			// string del board + string con las scores
			res = this.board + "\n Highest: " + this.maxToken + "     Score: " + this.score;
			if(this.results.getMax() >= 2048 && !won){
				res = res + "\nCongratulations, you have won";
				won = true;
			}
			return res;
		}
		
		//to fill array as list with empty positions
		public void freePositions(){
			this.freePos.resetIndex();
			for(int i = 0; i < this.size; i++){
				for(int j = 0; j < this.size; j++){
					if(this.board.getCell(i,j) == 0){
						this.freePos.addPosition(new Position(i,j));
					}
				}
			}
		}
		
		//to check if the game is lost
		public void hasLost(){
			freePositions();
			if(this.freePos.isEmpty()){//if there are no more free positions, we check there are no possible merges
				this.lost = true;      
				for(int i = 0; i < this.size; i++){
					for(int j = 0; j < this.size; j++){
						if((i + 1 < this.size && this.board.getCell(i, j) == this.board.getCell(i + 1, j)) || (i - 1 >= 0 && this.board.getCell(i, j) == this.board.getCell(i - 1, j)) ||( j - 1 >= 0 && this.board.getCell(i, j) == this.board.getCell(i, j - 1))||(j + 1 < this.size && this.board.getCell(i, j) == this.board.getCell(i, j + 1))){
							this.lost = false; //if there is a possible merge, then the game is not lost
						}
					}
				}
			}
		}
}
