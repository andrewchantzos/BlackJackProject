package game;

import strategy.Strategy;

public class Player {

	private String playerName;

	private Strategy strategy;
	
	private int staticBet;
		
	private int balance;
	
	private int roundInitialBalance;
	
	private int splits = 0;
	
	private Hand hand;
	private Hand hand2;
	private Hand hand3;
	private Hand hand4;

	
	public void updateBalance(String winner, double bet) {
		if (winner.equals("WIN")) {
			balance += bet;
		}
		if (winner.equals("LOSE"))
			balance -= bet;
	}
	
	public double getRoundResult() {
		return balance - roundInitialBalance;
	}
	
	public void newHand() {
		this.hand = new Hand();
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Hand getHand4() {
		return hand4;
	}

	public void setHand4(Hand hand4) {
		this.hand4 = hand4;
	}

	public Hand getHand3() {
		return hand3;
	}

	public void setHand3(Hand hand3) {
		this.hand3 = hand3;
	}

	public Hand getHand2() {
		return hand2;
	}

	public void setHand2(Hand hand2) {
		this.hand2 = hand2;
	}

	public int getSplits() {
		return splits;
	}

	public void setSplits(int splits) {
		this.splits = splits;
	}

	public int getStaticBet() {
		return staticBet;
	}

	public void setStaticBet(int staticBet) {
		this.staticBet = staticBet;
	}

	public int getRoundInitialBalance() {
		return roundInitialBalance;
	}

	public void setRoundInitialBalance(int roundInitialBalance) {
		this.roundInitialBalance = roundInitialBalance;
	}


}
