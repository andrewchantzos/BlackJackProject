package game;

import java.util.List;

import deck.Deck;

public class Round {

	private Deck deck;
	private DealerHand dealer;
	private List<Player> players;
	private boolean dealerBlackJack = false;
	private Streak streak;


	public void start() {
		
		Stats stats = new Stats(players.size());
		deck = new Deck(4, true);
		dealer = new DealerHand();
		dealer.addCardToPlayersHand(deck.dealingNextCard());
		dealer.addCardToPlayersHand(deck.dealingNextCard());
		if (dealer.checkIfBlackJack())
			dealerBlackJack = true;

		for (Player player : players) {
			player.setRoundInitialBalance(player.getBalance());
			player.setSplits(0);
			player.setHand2(null);
			player.setHand3(null);
			player.setHand4(null);

			
			player.newHand();
			Hand hand = player.getHand();
			hand.setBet(player.getStaticBet());

			hand.setDealerHand(dealer);
			hand.setDeck(deck);
			hand.setDealerBlackJack(dealerBlackJack);
			hand.setPlayer(player);
			hand.play(false);
			
			// if more than one hands play accordingly
			if (player.getSplits() == 1) {
				Hand hand2 = player.getHand2();
				hand2.setDeck(deck);
				hand2.setDealerHand(dealer);
				hand2.play(true);
			}
			if (player.getSplits() == 2) {
				Hand hand3 = player.getHand3();
				hand3.setDeck(deck);
				hand3.setDealerHand(dealer);
				hand3.play(true);
			}		
			if (player.getSplits() == 3) {
				Hand hand4 = player.getHand4();
				hand4.setDeck(deck);
				hand4.setDealerHand(dealer);
				hand4.play(true);
			}
		}
		
		// dealer plays
		dealersPlay();
		
		
		// System.out.println("Dealer: ");
		//dealer.printCardsInHand(true);
		// System.out.println(dealer.getPlayersHandTotal());
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			Hand hand = player.getHand();
			//System.out.println("Player " + i + " :");
			//hand.getPlayerHand().printCardsInHand(true);
			//System.out.println(hand.getPlayerHand().getPlayersHandTotal());
			String winner = hand.evaluateHand();
			//System.out.println(winner);
			player.updateBalance(winner, hand.getBet());
			if (player.getHand2() != null) {
				Hand hand2 = player.getHand2();
				//System.out.println("Player " + i +": Hand 2");
				//hand2.getPlayerHand().printCardsInHand(true);
				//System.out.println(hand2.getPlayerHand().getPlayersHandTotal());
				String winner2 = hand2.evaluateHand();
				//System.out.println(winner2);
				player.updateBalance(winner2, hand2.getBet());
			}
			if (player.getHand3() != null) {
				Hand hand3 = player.getHand3();
				//System.out.println("Player " + i +": Hand 3");
				//hand3.getPlayerHand().printCardsInHand(true);
				//System.out.println(hand3.getPlayerHand().getPlayersHandTotal());
				String winner3 = hand3.evaluateHand();
				//System.out.println(winner3);
				player.updateBalance(winner3, hand3.getBet());
			}
			if (player.getHand4() != null) {
				Hand hand4 = player.getHand4();
				//System.out.println("Player " + i +": Hand 4");
				//hand4.getPlayerHand().printCardsInHand(true);
				//System.out.println(hand4.getPlayerHand().getPlayersHandTotal());
				String winner4 = hand4.evaluateHand();
				//System.out.println(winner4);
				player.updateBalance(winner4, hand4.getBet());
			}
		}
		
		for (int i = 0; i < players.size(); i++) {
			if (streak.shouldBet(i)) {
				GameMain.myStrat += players.get(i).getRoundResult();
			}
		}
		if (streak.shouldBetAverageGreater(19)) {
			GameMain.stratAverage += players.get(0).getRoundResult();
		}
		
		stats.fillDealer(dealer.getPlayersHandTotal());

		
		for (int i = 0; i < players.size(); i++) {
			stats.fillPlayer(players.get(i), i);
		}
		streak.updateStreak(stats);	
	
		

		
		// System.out.println(stats);
		GameMain.list.add(stats);
	}

	// Dealer's Play Turn
	private void dealersPlay() {
		while (dealer.getPlayersHandTotal() < 17) {
			dealer.addCardToPlayersHand(deck.dealingNextCard());
		}
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public DealerHand getDealer() {
		return dealer;
	}

	public void setDealer(DealerHand dealer) {
		this.dealer = dealer;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isDealerBlackJack() {
		return dealerBlackJack;
	}

	public void setDealerBlackJack(boolean dealerBlackJack) {
		this.dealerBlackJack = dealerBlackJack;
	}
	
	
	public Streak getStreak() {
		return streak;
	}

	public void setStreak(Streak streak) {
		this.streak = streak;
	}
}
