package pr1;

public class MoveResults {
	private boolean moved;
	private int points; //number of points in this move
	private int maxToken; //highest val after mov
	
	public MoveResults(){
		this.moved = false;
		this.points = 0;
		this.maxToken = 0;
	}
	public boolean hasMoved(){
		return moved;
	}
	public int getPoints(){
		return points;
	}
	public int getMax(){
		return maxToken;
	}
	public void setMoved(boolean moved){
		this.moved = moved;
	}
	public void addPoints(int points){
		this.points = this.points + points;
	}
	public void isMaxToken(int token){
		if(token > this.maxToken){
			this.maxToken = token;
		}
	}
	
	
}
