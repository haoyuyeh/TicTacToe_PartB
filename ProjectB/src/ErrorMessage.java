/*
 * Author: Hao Yu Yeh
 * Date: 02/05/2016
 * Comment: project B(TicTacToe game) in COMP90041
 */

public class ErrorMessage {
	// for class TicTacToe
	public final static String WRONGARGUMENT = "The number of command's arguments is wrong." + 
											  "It should be less than 3.";
	public final static String WRONGCOMMAND = "The command is not existed.";
	public final static String WRONGINFO = "The format of user info should be " + 
										   "username,family_name,given_name.";
	public final static String WRONGPLAYER = "The format of user info should be " + 
			   							   "username1,username2.";
	public final static String WRONGUSER = "Player does not exist.";
	// for class PlayerManager
	public final static String EXCEEDMAX = "Already reach the limit of maximun players(" +
										   CommonConstant.MAXPLAYERS + ").";
	public final static String NOTEXIST = "The player does not exist.";
	public final static String EXISTALREADY = "The username has been used already.";	
	// for class GameManager
	public final static String OCCUPIEDCELL = "Invalid move. The cell has been occupied.";
	public final static String OUTOFBOUNDARY = "Invalid move. You must place at a cell within "
											   + "{0,1,2} {0,1,2}.";
}
