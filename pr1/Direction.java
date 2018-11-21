package pr1;


public enum Direction{ 
	
	UP, DOWN, LEFT, RIGHT;
	
	public static Direction toDirection(String dir){
		if(dir.equals("up")) return Direction.UP;
		else if(dir.equals("down")) return Direction.DOWN;
		else if(dir.equals("left")) return Direction.LEFT;
		else if(dir.equals("right")) return Direction.RIGHT;
		else return null;
	}
}
	

