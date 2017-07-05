/*
 * Author: Hao Yu Yeh
 * Date: 02/05/2016
 * Comment: project B(TicTacToe game) in COMP90041
 */

import java.util.Arrays;
import java.lang.String;
import java.lang.Math;

public class PlayerManager {
	private int newPlayerPos;
	public Player[] playerTable;

	public PlayerManager() {
		newPlayerPos = 0;
		playerTable = new Player[CommonConstant.MAXPLAYERS];
		for (int i = 0; i < CommonConstant.MAXPLAYERS; i++) {
			playerTable[i] = new Player();
		}
	}
	/*
	 * use for searching particular player if player exists, the function will
	 * return the position index. otherwise, it will return a negative value
	 */
	public int searchPlayer(String userName) {
		Player temp = new Player();
		temp.setUserName(userName);
		// searching in the existed players(the index of the last element is exclusive)
		int exist = Arrays.binarySearch(playerTable, 0, newPlayerPos, temp, 
										Player.playerUserNameComparator);
		return exist;
	}
	/*
	 * if the user name has not been used or the number of existed players
	 * doesn't exceed the limit of players, it would add the new player into
	 * playerTable.
	 */
	public void addPlayer(String userName, String familyName, String givenName) {
		int exist = searchPlayer(userName);
		// user does not exist
		if (exist < CommonConstant.NOTFOUND) {
			if (newPlayerPos < CommonConstant.MAXPLAYERS) {
				playerTable[newPlayerPos].setUserName(userName);
				playerTable[newPlayerPos].setFamilyName(familyName);
				playerTable[newPlayerPos].setGivenName(givenName);
				newPlayerPos++;
				// binary search should be applied in a sorted array
				Arrays.sort(playerTable, 0, newPlayerPos, Player.playerUserNameComparator);
			} else {
				System.out.println(ErrorMessage.EXCEEDMAX);
			}
		} else {
			System.out.println(ErrorMessage.EXISTALREADY);
		}
	}
	/*
	 * if the user name has been found, it would delete the user.
	 */
	public void removePlayer(String userName) {
		int exist = searchPlayer(userName);
		// not found in the array
		if (exist < CommonConstant.NOTFOUND) {
			System.out.println(ErrorMessage.NOTEXIST);
		} else {
			// move the last element to the position of the deleted element
			playerTable[exist] = playerTable[newPlayerPos - 1];
			newPlayerPos--;
			// binary search should be applied in a sorted array
			Arrays.sort(playerTable, 0, newPlayerPos, Player.playerUserNameComparator);
		}
	}
	/*
	 * it would remove all the users.
	 */
	public void removeAllPlayers() {
		newPlayerPos = 0;
	}
	/*
	 * if the user name has been found, it would modify the info of the user.
	 */
	public void editPlayer(String userName, String familyName, String givenName) {
		int exist = searchPlayer(userName);
		if (exist < CommonConstant.NOTFOUND) {
			System.out.println(ErrorMessage.NOTEXIST);
		} else {
			playerTable[exist].setFamilyName(familyName);
			playerTable[exist].setGivenName(givenName);
		}
	}
	/*
	 * if the user name has been found, it would reset the game stats of the
	 * user.
	 */
	public void resetPlayerStats(String userName) {
		int exist = searchPlayer(userName);
		if (exist < CommonConstant.NOTFOUND) {
			System.out.println(ErrorMessage.NOTEXIST);
		} else {
			playerTable[exist].setGamePlayed(CommonConstant.RESET);
			playerTable[exist].setGameWon(CommonConstant.RESET);
			playerTable[exist].setGameDrawn(CommonConstant.RESET);
		}
	}
	/*
	 * it would remove the game stats of all users.
	 */
	public void resetAllStats() {
		for (int i = 0; i < newPlayerPos; i++) {
			playerTable[i].setGamePlayed(CommonConstant.RESET);
			playerTable[i].setGameWon(CommonConstant.RESET);
			playerTable[i].setGameDrawn(CommonConstant.RESET);
		}
	}
	/*
	 * if the user name has been found, it would display the info of the user.
	 */
	public void displayPlayer(String userName) {
		int exist = searchPlayer(userName);
		if (exist < CommonConstant.NOTFOUND) {
			System.out.println(ErrorMessage.NOTEXIST);
		} else {
			System.out.println(playerTable[exist].getUserName() + "," + 
							   playerTable[exist].getFamilyName() + "," + 
							   playerTable[exist].getGivenName() + "," + 
							   playerTable[exist].getGamePlayed() + " games" + "," + 
							   playerTable[exist].getGameWon() + " wins" + "," + 
							   playerTable[exist].getGameDrawn() + " draws");
		}
	}
	/*
	 * it would display all users.
	 */
	public void displayAllPlayer() {
		for (int i = 0; i < newPlayerPos; i++) {
			System.out.println(playerTable[i].getUserName() + "," + 
							   playerTable[i].getFamilyName() + "," + 
							   playerTable[i].getGivenName() + "," + 
							   playerTable[i].getGamePlayed() + " games" + "," + 
							   playerTable[i].getGameWon() + " wins" + "," + 
							   playerTable[i].getGameDrawn() + " draws");
		}
	}
	/*
	 * display top MAXRANKINGPLAYERS players, if the number of players is
	 * greater or equal to MAXRANKINGPLAYERS. otherwise, display all players.
	 */
	public void displayRanking() {
		// calculating the win and draw rate
		for (int i = 0; i < newPlayerPos; i++) {
			if (playerTable[i].getGamePlayed() == 0) {
				playerTable[i].setWinRate(0);
				playerTable[i].setDrawRate(0);
			} else {
				// convert to the format of percentage
				playerTable[i].setWinRate(100.0 * (double)playerTable[i].getGameWon() / 
										  (double)playerTable[i].getGamePlayed());
				playerTable[i].setDrawRate(100.0 * (double)playerTable[i].getGameDrawn() / 
										   (double)playerTable[i].getGamePlayed());
			}
		}
		/*
		 * after the three sorting, it can break ties first by draw and then by
		 * username alphabetically
		 */
		Arrays.sort(playerTable, 0, newPlayerPos, Player.playerUserNameComparator);
		Arrays.sort(playerTable, 0, newPlayerPos, Player.playerDrawRateComparator);
		Arrays.sort(playerTable, 0, newPlayerPos, Player.playerWinRateComparator);
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		if (newPlayerPos >= CommonConstant.MAXRANKINGPLAYERS) {
			for (int i = 0; i < CommonConstant.MAXRANKINGPLAYERS; i++) {
				System.out.println(String.format(" %3d%% | %3d%% | %2d   | %s", 
												 Math.round(playerTable[i].getWinRate()),
												 Math.round(playerTable[i].getDrawRate()), 
												 playerTable[i].getGamePlayed(),
												 playerTable[i].getUserName()));
			}
		} else {
			for (int i = 0; i < newPlayerPos; i++) {
				System.out.println(String.format(" %3d%% | %3d%% | %2d   | %s", 
												 Math.round(playerTable[i].getWinRate()),
												 Math.round(playerTable[i].getDrawRate()), 
												 playerTable[i].getGamePlayed(),
												 playerTable[i].getUserName()));
			}
		}
		// other functions need an array sorted by user name
		Arrays.sort(playerTable, 0, newPlayerPos, Player.playerUserNameComparator);
	}
}