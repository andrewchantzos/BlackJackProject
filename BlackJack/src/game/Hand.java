package game;

import java.util.Map;

import deck.Cards;
import deck.Deck;
import strategy.Choice;
import strategy.StrategyCombo;

public class Hand {

	private PlayerHand playerHand;
	private DealerHand dealerHand;
	private Deck deck;
	private boolean dealerBlackJack = false;
	private boolean gameDone = false;
	private Player player;
	private boolean splittable = true;
	private double bet;
	
	private String playerWins = "";

	private Map<StrategyCombo, Choice> strategyMap;

	
	public void play(boolean splitHand) {
		if (!splitHand)
			playerHand = new PlayerHand();

		if (!splitHand) {
			playerHand.addCardToPlayersHand(deck.dealingNextCard());
		}
		playerHand.addCardToPlayersHand(deck.dealingNextCard());

		// Check for blackJacks
		if (blackJackTest()) {
			bet = bet*1.5;
			return;
		}
	
		// Get dealer card
		String firstDealerCard = dealerHand.getFirstCard().getStringCardNumber();
		strategyMap = player.getStrategy().getStategyMap();

		while (!gameDone) {
			// Evaluate hands through strategy map and choose a move
			String myHandScore = playerHand.evaluateHand(splittable);
			StrategyCombo combo = new StrategyCombo(firstDealerCard, myHandScore);
			Choice choice = strategyMap.get(combo);

			if (choice == null) {
				System.out.println(combo);
				playerHand.printCardsInHand(true);
			}
			// double
			if (choice.name().equals("D") && playerHand.getNumCardsInHand() == 2) {
				doubleDown();
			} else if (choice.name().equals("D") && playerHand.getNumCardsInHand() > 2) {
				hit();
			} else if (choice.name().equals("S")) { //stay
				stay();
			} else if (choice.name().equals("H")) { //hit
				hit();
			} else if (choice.name().equals("P") && player.getSplits() < 3) {
				split();
				if (blackJackTest())
					bet = bet*1.5;
					return;
			}
			if (player.getSplits() == 3 && choice.name().equals("P")) {
				splittable = false;
			}
		}
	}

	public String evaluateHand() {
		if (playerWins.equals("")) {

			int dealerTotal = dealerHand.getPlayersHandTotal();
			int playerTotal = playerHand.getPlayersHandTotal();
			if (playerTotal > 21) {
				playerWins = "LOSE";
			}
			else if (dealerTotal > 21) {
				playerWins = "WIN";
			} else if (dealerTotal > playerTotal) {
				playerWins = "LOSE";
			} else if (dealerTotal == playerTotal) {
				playerWins = "TIE";
			} else if (dealerTotal < playerTotal) {
				playerWins = "WIN";
			}
		}
		return playerWins;
	}

	// Player's Hit
	private void hit() {
		boolean t = playerHand.addCardToPlayersHand(deck.dealingNextCard());
		if (!t) {
			stay();
			return;
		}
		if (playerHand.getPlayersHandTotal() > 21) {
			gameDone = true;
			playerWins = "LOSE";
		}
	}

	// Player's Stay
	private void stay() {
		gameDone = true;
	}

	// Player's Double Down
	private void doubleDown() {
		bet *= 2;
		playerHand.addCardToPlayersHand(deck.dealingNextCard());

		if (playerHand.getPlayersHandTotal() > 21) {
			gameDone = true;
			playerWins = "LOSE";
		}
	}

	private void split() {
		int splits = player.getSplits();
		player.setSplits(splits + 1);
		Cards card = playerHand.getCardsInHand()[0];
		Cards splitCard = playerHand.getCardsInHand()[1];
		playerHand.removeLastCard();
		hit();
		if (splits == 0) {
			Hand hand2 = new Hand();
			hand2.setBet(player.getStaticBet());
			hand2.setPlayer(player);
			hand2.setDealerHand(dealerHand);
			hand2.setPlayerHand(new PlayerHand());
			player.setHand2(hand2);
			player.getHand2().getPlayerHand().addCardToPlayersHand(splitCard);
		}
		if (splits == 1) {
			Hand hand3 = new Hand();
			hand3.setBet(player.getStaticBet());
			hand3.setPlayer(player);
			hand3.setPlayerHand(new PlayerHand());
			hand3.setDealerHand(dealerHand);

			player.setHand3(hand3);
			player.getHand3().getPlayerHand().addCardToPlayersHand(splitCard);
		}
		if (splits == 2) {
			Hand hand4 = new Hand();
			hand4.setBet(player.getStaticBet());
			hand4.setPlayer(player);
			hand4.setPlayerHand(new PlayerHand());
			hand4.setDealerHand(dealerHand);

			player.setHand4(hand4);
			player.getHand4().getPlayerHand().addCardToPlayersHand(splitCard);
		}
	}

	public String getPlayerWins() {
		return playerWins;
	}

	public void setPlayerWins(String playerWins) {
		this.playerWins = playerWins;
	}

	public boolean blackJackTest() {
		if (playerHand.checkIfBlackJack()) {
			if (dealerBlackJack) {
				playerWins = "TIE";
				return true;
			}
			playerWins = "WIN";
			return true;
		}
		if (dealerBlackJack) {
			playerWins = "LOSE";
			return true;
		}
		return false;
	}

	public DealerHand getDealerHand() {
		return dealerHand;
	}

	public void setDealerHand(DealerHand dealerHand) {
		this.dealerHand = dealerHand;
	}

	public PlayerHand getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(PlayerHand playerHand) {
		this.playerHand = playerHand;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public boolean isDealerBlackJack() {
		return dealerBlackJack;
	}

	public void setDealerBlackJack(boolean dealerBlackJack) {
		this.dealerBlackJack = dealerBlackJack;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	
	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}
}
