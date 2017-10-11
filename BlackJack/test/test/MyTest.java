package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import deck.Cards;
import deck.Deck;
import deck.Suits;
import game.Player;
import game.PlayerHand;
import game.Round;
import io.ExcelReader;
import strategy.Strategy;

public class MyTest {

	@Test
	public void test() {
		ExcelReader reader = new ExcelReader();
		Strategy strat = new Strategy();
		
		strat.setStategyMap(reader.strategyExcelReader("input/blackJackStrat.xlsx"));
		
		Player player = new Player();
		player.setBalance(1000000);
		player.setStaticBet(10);
		player.setStrategy(strat);
		
		Round round = new Round();
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		round.setPlayers(players);
		
		round.start();
	}

	
	@Test
	public void test1() {
		ExcelReader reader = new ExcelReader();
		Strategy strat = new Strategy();
		
		strat.setStategyMap(reader.strategyExcelReader("input/blackJackStrat.xlsx"));
		
		PlayerHand hand = new PlayerHand();
		Cards card1 = new Cards(Suits.Clubs, 1);
		Cards card2 = new Cards(Suits.Clubs, 2);
		Cards card3 = new Cards(Suits.Clubs, 4);
		
		hand.addCardToPlayersHand(card1);
		hand.addCardToPlayersHand(card2);
		hand.addCardToPlayersHand(card3);
		hand.setNumCardsInHand(3);
		System.out.println(hand.evaluateHand(true));

	}
	
	
	@Test
	public void test2() {
		ExcelReader reader = new ExcelReader();
		Strategy strat = new Strategy();
		
		strat.setStategyMap(reader.strategyExcelReader("input/blackJackStrat.xlsx"));
		
		Player player = new Player();
		player.setBalance(1000000);
		player.setStaticBet(10);
		player.setStrategy(strat);
		
		Round round = new Round();
		List<Player> players = new ArrayList<>();
		players.add(player);
		
		round.setPlayers(players);
		
		PlayerHand hand = new PlayerHand();
		Cards card1 = new Cards(Suits.Clubs, 8);
		Cards card2 = new Cards(Suits.Clubs, 10);
		Cards card3 = new Cards(Suits.Clubs, 1);
		Cards card4 = new Cards(Suits.Clubs, 10);
		Cards card5 = new Cards(Suits.Clubs, 1);
		Cards card6 = new Cards(Suits.Clubs, 8);
		Cards card7 = new Cards(Suits.Clubs, 10);
		Cards card8 = new Cards(Suits.Clubs, 10);
		Cards card9 = new Cards(Suits.Clubs, 10);

		Cards[] cardsInDeck = new Cards[9];
		cardsInDeck[0] = card1;
		cardsInDeck[1] = card2;
		cardsInDeck[2] = card3;
		cardsInDeck[3] = card4;
		cardsInDeck[4] = card5;
		cardsInDeck[5] = card6;
		cardsInDeck[6] = card7;
		cardsInDeck[7] = card8;
		cardsInDeck[8] = card9;

		Deck deck = new Deck(3, true);
		deck.setNumOfCardsInDeck(9);
		deck.setCardsInDeck(cardsInDeck);
		round.setDeck(deck);
		round.start();
		System.out.println(player.getBalance());
	}
}
