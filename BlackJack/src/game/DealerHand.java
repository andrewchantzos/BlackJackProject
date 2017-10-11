package game;

import deck.Cards;

public class DealerHand {

	private Cards[] playerHand = new Cards[10];
	private int numCardsInHand;

	public Cards[] getCardsInHand() {
		return this.playerHand;
	}

	public DealerHand(){
		this.emptyHand();
	}

	/*
	 * Return first card from Hand;
	 */
	public Cards getFirstCard() {
		return playerHand[0];
	}
	
	/*
	 * Empties hand
	 */
	public void emptyHand() {
		for (int hc = 0; hc < 10; hc++) {

			this.playerHand[hc] = null;
		}

		this.numCardsInHand = 0;

	}

	
	public boolean addCardToPlayersHand(Cards card) {
		if (this.numCardsInHand == 10) {
			return false;
		}

		this.playerHand[this.numCardsInHand] = card;
		this.numCardsInHand++;

		return (this.getPlayersHandTotal() <= 21);

	}

	
	/*
	 * Returns sum of cards in Hand
	 */
	public int getPlayersHandTotal() {

		int handTotal = 0;
		int cardNum;
		int numAces = 0;

		for (int c = 0; c < this.numCardsInHand; c++) {

			cardNum = this.playerHand[c].getCardNumber();
			// System.out.printf("getPlayersHandTotal: %s\n%d",
			// this.playerHand[c], cardNum);

			if (cardNum == 1) { // Ace

				numAces++;
				handTotal += 11;
			} else if (cardNum >= 10) {

				handTotal += 10;
			} else {

				handTotal += cardNum;
			}
		}

		while (handTotal > 21 && numAces > 0) {

			handTotal -= 10;
			numAces--;
		}

		return handTotal;
	}

	public boolean checkIfBlackJack() {

		boolean blackJack = false;

		if (getPlayersHandTotal() == 21) {
			blackJack = true;
		}
		return blackJack;
	}

	public void printCardsInHand(boolean showFirstCard){
		
		for(int c=0; c<this.numCardsInHand;c++){
			if(!showFirstCard && c==0){
				
				System.out.printf("\t[hidden]\n");
			}
			else{
				
				System.out.printf("\t%s\n\n", this.playerHand[c]);
			}	
		}
	}
}