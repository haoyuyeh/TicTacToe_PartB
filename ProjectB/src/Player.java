/*
 * Author: Hao Yu Yeh
 * Date: 02/05/2016
 * Comment: project B(TicTacToe game) in COMP90041
 */

import java.util.Comparator;

public class Player {
	private String userName, familyName, givenName;
	private int gamePlayed, gameWon, gameDrawn;
	private double winRate, drawRate;

	public Player() {
		userName = "N/A";
		familyName = "Doe";
		givenName = "John";
		gamePlayed = gameWon = gameDrawn = 0;
		winRate = drawRate = 0.0;
	}
	/*
	 * adding comparators to make sorting an array which consists of objects of
	 * Player by the attributes of class Player possible
	 */
	// alphabetically ascending order
	public static Comparator<Player> playerUserNameComparator = new Comparator<Player>() {
		public int compare(Player p1, Player p2) {
			return p1.getUserName().compareTo(p2.getUserName());
		}
	};
	// descending order
	public static Comparator<Player> playerWinRateComparator = new Comparator<Player>() {
		public int compare(Player p1, Player p2) {
			return Double.compare(p2.getWinRate(), p1.getWinRate());
		}
	};
	// descending order
	public static Comparator<Player> playerDrawRateComparator = new Comparator<Player>() {
		public int compare(Player p1, Player p2) {
			return Double.compare(p2.getDrawRate(), p1.getDrawRate());
		}
	};
	/*
	 * setting the user name
	 */
	public void setUserName(String name) {
		userName = name;
	}
	/*
	 * getting the user name
	 */
	public String getUserName() {
		return userName;
	}
	/*
	 * setting the family name
	 */
	public void setFamilyName(String name) {
		familyName = name;
	}
	/*
	 * getting the family name
	 */
	public String getFamilyName() {
		return familyName;
	}
	/*
	 * setting the given name
	 */
	public void setGivenName(String name) {
		givenName = name;
	}
	/*
	 * getting the given name
	 */
	public String getGivenName() {
		return givenName;
	}
	/*
	 * counting how many games the player has played. num: 0(clear the record)
	 * or 1
	 */
	public void setGamePlayed(int num) {
		if (num == CommonConstant.CLEAR) {
			gamePlayed = CommonConstant.CLEAR;
		} else {
			gamePlayed += num;
		}
	}
	/*
	 * getting the number of game played
	 */
	public int getGamePlayed() {
		return gamePlayed;
	}
	/*
	 * counting how many games the player has won. num: 0(clear the record) or 1
	 */
	public void setGameWon(int num) {
		if (num == CommonConstant.CLEAR) {
			gameWon = CommonConstant.CLEAR;
		} else {
			gameWon += num;
		}
	}
	/*
	 * getting the number of wins
	 */
	public int getGameWon() {
		return gameWon;
	}
	/*
	 * counting how many draws the player has. num: 0(clear the record) or 1
	 */
	public void setGameDrawn(int num) {
		if (num == CommonConstant.CLEAR) {
			gameDrawn = CommonConstant.CLEAR;
		} else {
			gameDrawn += num;
		}
	}
	/*
	 * getting the number of draws
	 */
	public int getGameDrawn() {
		return gameDrawn;
	}
	/*
	 * setting the rate of winning
	 */
	public void setWinRate(double num) {
		winRate = num;
	}
	/*
	 * getting the rate of winning
	 */
	public double getWinRate() {
		return winRate;
	}
	/*
	 * setting the rate of draws
	 */
	public void setDrawRate(double num) {
		drawRate = num;
	}
	/*
	 * getting the rate of draw
	 */
	public double getDrawRate() {
		return drawRate;
	}
}
