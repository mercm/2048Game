package pr1;

import util.ArrayAsList;
import util.MyStringUtiles;

public class Board {
		private Cell[][] board;
		private int boardSize;
	
		
		public Board(int boardSize){
			this.boardSize = boardSize;
			board = new Cell[boardSize][boardSize];
			for(int i = 0; i < this.boardSize; i++){
				for(int j = 0; j < this.boardSize; j++){
					board[i][j] = new Cell();
				}
			}
		}
		
		public void setCell(Position pos, int value){
			int x = pos.getX();
			int y = pos.getY();
			board[x][y].setValue(value);
		}
		
		public int getCell(int x, int y){
			return board[x][y].getValue();
		}
		
		public MoveResults executeMove(Direction dir){
			MoveResults results = new MoveResults();
			if(dir.equals(Direction.UP)){
				for(int j = 1; j < this.boardSize; j++){
					for(int i = 0; i < this.boardSize; i++){
						if(board[i][j].getValue() != 0){ 
							displaceCell(dir, new Position(i,j), results);
							results.isMaxToken(board[i][j].getValue());//to check if its the maxtoken
						}
						
					}
				}
			} 
			else if(dir.equals(Direction.DOWN)){
				for(int j = this.boardSize - 2; j >= 0; j--){
					for(int i = 0; i < this.boardSize; i++){
						if(board[i][j].getValue() != 0){
							displaceCell(dir, new Position(i,j), results);
							results.isMaxToken(board[i][j].getValue());
						}
					}
				}
			}
			else if(dir.equals(Direction.LEFT)){
				for(int i = 1; i < this.boardSize; i++){
					for(int j = 0; j < this.boardSize; j++){
						if(board[i][j].getValue() != 0){
							displaceCell(dir, new Position(i,j), results);
							results.isMaxToken(board[i][j].getValue());
						}
					}
				}
			}
			else if(dir.equals(Direction.RIGHT)){
				for(int i = this.boardSize - 2; i >= 0; i--){
					for(int j = 0; j < this.boardSize; j++){
						if(board[i][j].getValue() != 0){
							displaceCell(dir, new Position(i,j), results);
							results.isMaxToken(board[i][j].getValue());
						}
					}
				}
			}
			return results;
		}
		
		//to move & merge cells in a column/row
		
		public void displaceCell(Direction dir, Position pos, MoveResults results){
			Position neighbour;
			int cellPoints;
			Boolean merged = false;
			neighbour = pos.neighbour(dir, this.boardSize);
			while(neighbour != null && !merged){
				
				if(this.board[neighbour.getX()][neighbour.getY()].getValue() == 0){ //if the neighbour is empty a move is performed
					this.board[neighbour.getX()][neighbour.getY()].setValue(this.board[pos.getX()][pos.getY()].getValue());
					this.board[pos.getX()][pos.getY()].setValue(0);
					pos = neighbour;
					results.setMoved(true);
				} else {         //if the neighbour is not empty, we try to merge them
					cellPoints = this.board[pos.getX()][pos.getY()].getValue();
					merged = this.board[pos.getX()][pos.getY()].doMerge(this.board[neighbour.getX()][neighbour.getY()]);
					if(merged){
						results.setMoved(true);  //updating the move results
						results.addPoints(cellPoints * 2); 
						results.isMaxToken(cellPoints * 2);
					} else{
						merged = true; //this is to exit the loop(after encountering other cell, even if the merge did not happen, there is no possible move)
					}
				}
				neighbour = pos.neighbour(dir, this.boardSize);
			}
		}
		
		//to print the board
		public String toString(){
			int cellSize = 7, num;
			String space = " ";
			String vDelimiter = "|";
			String hDelimiter = "-";
			
			String res = "";
			
			num = this.boardSize * cellSize + (this.boardSize - 1); 
			for(int j = 0; j < this.boardSize; j++) {
				res = res + space + MyStringUtiles.repeat(hDelimiter, num) + space + "\n";//Returns the intermediate rows of the board
				for(int i = 0; i < this.boardSize; i++) {
					res = res + vDelimiter + MyStringUtiles.centre(Integer.toString(board[i][j].getValue()), cellSize);//Returns the rows where the cells are
				}
				res = res +vDelimiter + "\n";
			}
			res = res + space + MyStringUtiles.repeat(hDelimiter, num) + space + "\n";
			
			return res;
				
		} 
		
}
