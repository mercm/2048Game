package pr1;

import java.util.Scanner;

public class Controller {
		private Game game;
		private Scanner in;
		
		public Controller(Game game, Scanner in){
			this.game = game;
			this.in = in;
		}
		public void Run(){
			String input;
			String[] command;
			Direction dir;
			Boolean lost;
			do{
				input = in.nextLine();
				input.toLowerCase();
				command = input.split(" ");
				if(command.length == 2 && command[0].equals("move")){
					dir = Direction.toDirection(command[1]);		
					if(dir != null){
						lost = game.Move(dir);
						System.out.println(game);//game.toString();//Prints the board
						if(lost){
							System.out.println("Oops, looks like you lost the game... \nHere, try again!\n");
							game.Reset();
							System.out.println(game);
						}
					}
					else System.out.println("Move has to be followed by: right, up, left or down");
				}
				else if(command.length == 1 && command[0].equals("reset")){
					game.Reset();
					System.out.println(game);//game.toString();//Prints the board
				}
				else if(command.length == 1 && command[0].equals("help")){
					System.out.println("Move <direction>: execute a move in one of the four directions, up, down, left, right\n" + 
							"Reset: start a new game\n" + 
							"Help: print this help message\n" + 
							"Exit: terminate the program\n\n");
				}
				else if(command.length != 1 && !command[0].equals("exit")){
					System.out.println("Command not recognized");
				}
				
			}while(!command.equals("exit"));
		
		}
		
		
}
