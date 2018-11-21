package pr1;

public class Position {
//A class whose instances represent positions on the board.
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public Position neighbour(Direction dir, int size){//returns the position to compare
		//in case it goes out of bounds
		if((dir == Direction.UP && this.y - 1 < 0)||(dir == Direction.DOWN && this.y + 1 >= size)||(dir == Direction.LEFT && this.x - 1 < 0)||(dir == Direction.RIGHT && this.x + 1 >= size)){
			return null;
		}else if(dir == Direction.UP) {
			return new Position(this.x,this.y - 1);
		}else if(dir == Direction.DOWN){
			return new Position(this.x,this.y + 1);
		}
		else if(dir == Direction.RIGHT){
			return new Position(x + 1,y);
		}
		else return new Position(x - 1,y);
		
	}
}
