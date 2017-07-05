/*
 * Author: Hao Yu Yeh
 * Date: 02/05/2016
 * Comment: project B(TicTacToe game) in COMP90041
 */

import java.util.Scanner;

public class TicTacToe {
	public static Scanner keyboard;
	private PlayerManager currentPlayers;
	private GameManager currentGame;
	
	public TicTacToe(){
		keyboard = new Scanner(System.in);
		currentPlayers = new PlayerManager();
		currentGame = new GameManager();
	}
	// start a game system
	public void run(){
		System.out.println("Welcome to Tic Tac Toe!");
		while( true ){
			incomingCommand();
			processCommand();
		}		
	}
	// receiving commands
	private void incomingCommand(){
		System.out.println();
		System.out.print(">");
	}
	// processing received commands
	private void processCommand(){
		String commandLine = "";
		commandLine = keyboard.nextLine();
		String[] commands;
		commands = commandLine.split(" ");
		int length = commands.length;
		// only contain maximum 2 arguments in all kinds of command  
		if( ( length > 0 ) && ( length <= CommonConstant.MAXARGUMENTS ) ){
			switch( commands[0] ){
			case Commands.ADDPLAYER: 
				if( length == 1 ){
					System.out.println( ErrorMessage.WRONGARGUMENT );
				}else{
					addPlayer( commands[1] );
				}				
				break;
			case Commands.DELETEPLAYER: 
				if( length == 1 ){
					deletePlayer( "" );
				}else{
					deletePlayer( commands[1] );
				}				
				break;
			case Commands.EDITPLAYER: 
				if( length == 1 ){
					System.out.println( ErrorMessage.WRONGARGUMENT );
				}else{
					editPlayer( commands[1] );
				}				
				break;
			case Commands.RESETSTATS:
				if( length == 1 ){
					resetStats( "" );
				}else{
					resetStats( commands[1] );
				}				
				break;
			case Commands.DISPLAYPLAYER:
				if( length == 1 ){
					displayPlayer( "" );
				}else{
					displayPlayer( commands[1] );
				}				
				break;
			case Commands.SHOWRANKING:
				if( length == 1 ){
					showRanking();
				}else{
					System.out.println( ErrorMessage.WRONGARGUMENT );
				}				
				break;
			case Commands.PLAY:
				if( length == 1 ){
					System.out.println( ErrorMessage.WRONGARGUMENT );
				}else{
					play( commands[1] );
				}				
				break;
			case Commands.EXIT:
				if( length == 1 ){
					exit();
				}else{
					System.out.println( ErrorMessage.WRONGARGUMENT );
				}				
				break;
			default:
				System.out.println( ErrorMessage.WRONGCOMMAND );
				break;
			}			
		}else{
			System.out.println( ErrorMessage.WRONGARGUMENT );
		}
	}
	// adding a new user
	private void addPlayer( String remainedCommand ){
		String[] commands;
		// format of remained command is username,family_name,given_name
		commands = remainedCommand.split(",");		
		if( commands.length == 3 ){
			currentPlayers.addPlayer( commands[0], commands[1], commands[2] );
		}else{
			System.out.println( ErrorMessage.WRONGINFO );
		}
	}
	// edit the name of user
	private void editPlayer( String remainedCommand ){
		String[] commands;
		// format of remained command is username,new_family_name,new_given_name
		commands = remainedCommand.split(",");		
		if( commands.length == 3 ){
			currentPlayers.editPlayer( commands[0], commands[1], commands[2] );
		}else{
			System.out.println( ErrorMessage.WRONGINFO );
		}
	}
	// delete players from game system
	private void deletePlayer( String remainedCommand ){
		if( remainedCommand == "" ){
			System.out.println( "Are you sure you want to remove all players? (y/n)" );
			if( keyboard.nextLine().compareTo("y") == 0 ){
				currentPlayers.removeAllPlayers();
			}
		}else{
			currentPlayers.removePlayer( remainedCommand );
		}
	}
	// clear the statistics of players
	private void resetStats( String remainedCommand ){
		if( remainedCommand == "" ){
			System.out.println( "Are you sure you want to reset all player statistics? (y/n)" );
			if( keyboard.nextLine().compareTo("y") == 0 ){
				currentPlayers.resetAllStats();
			}
		}else{
			currentPlayers.resetPlayerStats( remainedCommand );
		}
	}
	// display the info of players
	private void displayPlayer( String remainedCommand ){
		if( remainedCommand == "" ){
			currentPlayers.displayAllPlayer();
		}else{
			currentPlayers.displayPlayer( remainedCommand );
		}
	}
	// show the ranking of existed users
	private void showRanking(){
		currentPlayers.displayRanking();
	}
	// play a Tic Tac Toe game
	private void play( String remainedCommand ){
		String[] commands;
		// format of remained command is username1,username2
		commands = remainedCommand.split(",");		
		if( commands.length == 2 ){
			/* 
			 * checking the validation of the usernames.
			 * if the users don't exist, the search function will return a negative value
			 */
			int p1Pos = currentPlayers.searchPlayer( commands[0] ), 
				p2Pos = currentPlayers.searchPlayer( commands[1] );
			if( ( p1Pos >= 0 ) && ( p2Pos >= 0 ) ){
				
				currentGame.playGame( currentPlayers.playerTable[p1Pos], 
									  currentPlayers.playerTable[p2Pos]);
			}else{
				System.out.println( ErrorMessage.WRONGUSER );
			}
		}else{
			System.out.println( ErrorMessage.WRONGPLAYER );
		}
	}
	// exiting the game system
	private void exit(){
		System.out.println();
		System.exit(0);
	}	
	public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();
	}
}