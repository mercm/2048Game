package pr1;

import java.util.Random;
import java.util.Scanner;

public class Game2048 {
		//creates game & controller and runs
	public static void main(String Args[]){
		Scanner in = new Scanner(System.in);
		Integer size = Integer.parseInt(Args[0]);
		Integer initCells = Integer.parseInt(Args[1]);
		long seed;
		if(Args.length <= 2){
			seed = new Random().nextInt(1000);
		} else{
			seed = Long.parseLong(Args[2]); 
		}
		Random rand = new Random(seed);
		Game game = new Game(size, initCells, rand);
		Controller controller = new Controller(game, in);
		controller.Run();
		in.close();
	}
}




