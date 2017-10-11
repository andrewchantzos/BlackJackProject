package game;

public class Streak {

	
	private int dealerWinningStreak = 0;
	
	private int maxNotBustedStreak = 0;
	
	private int maxLosingStreak = 0;
	
	private int dealerAverageLastFive = 0;
	
	private int numOfPlayers;
	
	
	private int[] playerLosingStreak;
	
	public Streak(int players) {
		this.numOfPlayers = players;
		playerLosingStreak = new int[numOfPlayers];
	}
	
	
	public boolean shouldBet(int i) {
		if (dealerWinningStreak > 3 && playerLosingStreak[i] > 5)
			return true;
		return false;
	}
	
	public void updateStreak(Stats stats) {
		if (stats.getM().equals("X")) {
			dealerWinningStreak = 0;
			dealerAverageLastFive = 0;
		}
		else {
			dealerWinningStreak++;
			dealerAverageLastFive += Integer.parseInt(stats.getM());
			if (maxNotBustedStreak < dealerWinningStreak)
				maxNotBustedStreak = dealerWinningStreak;
		}
		String[] results = stats.getP();
		for (int i = 0; i < results.length; i++) {
			if (results[i].contains("W"))
				playerLosingStreak[i] = 0;
			else if (results[i].equals("L")) 
				playerLosingStreak[i]++;
			
			if (maxLosingStreak < playerLosingStreak[i])
				maxLosingStreak = playerLosingStreak[i];
		}
	}
	
	
	public boolean shouldBetAverageGreater(int compareNum) {
		if (dealerAverageLastFive /5  >= compareNum)
			return true;
		return false;
	}
	
	public int getMaxLosingStreak() {
		return maxLosingStreak;
	}


	public void setMaxLosingStreak(int maxLosingStreak) {
		this.maxLosingStreak = maxLosingStreak;
	}


	public int getMaxNotBustedStreak() {
		return maxNotBustedStreak;
	}


	public void setMaxNotBustedStreak(int maxNotBustedStreak) {
		this.maxNotBustedStreak = maxNotBustedStreak;
	}
}
