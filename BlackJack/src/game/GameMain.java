package game;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.ExcelReader;
import io.WriteExcel;
import strategy.Strategy;

public class GameMain {
	
	public static List<Stats> list = new ArrayList<>();
	public static final int balance = 1000000;
	public static final int bet = 10;
	public static final int numOfPlayers = 5;
	public static final int numOfRounds = 100000;
	public static final String strategyInput = "input/blackJackStrat.xlsx";
	
	public static double myStrat = 0;
	
	public static double stratAverage = 0;

	/*
	 * Excel file output for Windows
	 */
	public static String FILE_PATH = System.getProperty("user.home") + "/Documents/BlackJackDocs/";

	
	private static String FILE_EXTENSION = ".xlsx";

	
	public static void main(String args[]) {
		ExcelReader reader = new ExcelReader();
		Strategy strat = new Strategy();

		strat.setStategyMap(reader.strategyExcelReader(strategyInput));
		
		List<Player> players = new ArrayList<>(numOfPlayers);
		
		for (int i = 0; i < numOfPlayers; i++) {
			Player player = new Player();
			player.setBalance(balance);
			player.setStaticBet(bet);
			player.setStrategy(strat);
			players.add(player);
		}

		Streak streak = new Streak(numOfPlayers);
		
		for (int i = 0; i < numOfRounds; i++) {
			
			Round round = new Round();
			round.setPlayers(players);
			round.setStreak(streak);
			round.start();

		}

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			System.out.println("Player " + (i+1) + ":");
			System.out.println("New Balance: " + player.getBalance());
			System.out.println("Win/Loss: " + (player.getBalance() - 1000000));
		}
		
		
		
		// Set fileName
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
		String filename = FILE_PATH + df.format(new Date()) + "." + FILE_EXTENSION;	
		
		
		//WriteExcel excel = new WriteExcel(filename);
		//excel.writeExcelData(list);
		
		System.out.println(myStrat);
		System.out.println("Dealer Max Not Busted Streak: " + streak.getMaxNotBustedStreak());
		
		System.out.println("Max Losing Streak: " + streak.getMaxLosingStreak());
		
		System.out.println(stratAverage);

	}
}