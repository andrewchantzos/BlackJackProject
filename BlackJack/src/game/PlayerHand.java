package game;

import deck.Cards;

public class PlayerHand {

	
	private Cards[] playerHand = new Cards[10];
	private int numCardsInHand;
	
	
	public Cards[] getCardsInHand() {
		return this.playerHand;
	}
	
	public PlayerHand(){
		this.emptyHand();
	}
	
	public void emptyHand(){
		
		for(int hc=0; hc<10;hc++){
			
			this.playerHand[hc] = null;
		}
		
		this.numCardsInHand = 0;
		
	}
	
	
	public String evaluateHand(boolean splittable) {
		String res = "";
		if (numCardsInHand == 2 && splittable) {
			String firstCard = playerHand[0].getStringCardNumber();
			String secondCard = playerHand[1].getStringCardNumber();

			if (firstCard.equals(secondCard)) {
				res = firstCard + "." + firstCard;
			} else if (firstCard.equals("A")) {
				res = "A." + secondCard;
			} else if (secondCard.equals("A")) {
				res = "A." + firstCard;
			} else {
				Integer tmp = getPlayersHandTotal();
				res = tmp.toString();
			}
		}
		else if (numOfAces() == 1 && sumWithoutAces() < 10) {
			Integer tmp = sumWithoutAces();
			res = "A." + tmp;
		}
		else {
			Integer tmp = getPlayersHandTotal();
			res = tmp.toString();
		}
		return res;
	}
	
	public void removeLastCard() {
		playerHand[numCardsInHand -1] = null;
		numCardsInHand--;
	}
	
	private int numOfAces() {
		int aces = 0;
		for (int i = 0; i < numCardsInHand; i++) {
			if (playerHand[i].getCardNumber() == 1)
				aces ++;
		}
		return aces;
	}
	
	private int sumWithoutAces() {
		int handTotal = 0;
		for (int i = 0; i < numCardsInHand; i++) {
			if (playerHand[i].getCardNumber() != 1)
				handTotal += playerHand[i].getCardNumber();
		}
		return handTotal;
	}
	
	public boolean addCardToPlayersHand(Cards card){
		
		if(this.numCardsInHand == 10){
			return false;
		}
		
		this.playerHand[this.numCardsInHand] = card;
		this.numCardsInHand++;
		
		return (this.getPlayersHandTotal() <=21);
		
	}
	
	public int getPlayersHandTotal(){
		
		int handTotal = 0;
		int cardNum;
		int numAces = 0;
		
		//System.out.printf("getPlayersHandTotal :%d\n ", this.numCardsInHand);
		
		for(int c =0; c<this.numCardsInHand;c++){
			
			cardNum = this.playerHand[c].getCardNumber();
			//System.out.printf("getPlayersHandTotal: %s\n%d", this.playerHand[c], cardNum);
			
			if(cardNum == 1){ // Ace
				
				numAces++;
				handTotal += 11;
			}
			else if(cardNum >= 10){
				
				handTotal += 10;
			}
			else{
				
				handTotal += cardNum;
			}
		}
		
		while(handTotal > 21 && numAces > 0){
			
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

	public int getNumCardsInHand() {
		return numCardsInHand;
	}

	public void setNumCardsInHand(int numCardsInHand) {
		this.numCardsInHand = numCardsInHand;
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
	
	public boolean splitPossible(){
		
		if(this.numCardsInHand == 2 && (this.playerHand[0].getCardNumber() == this.playerHand[1].getCardNumber())){
			
			return true;		
		}
		else{
			
			return false;
		}
	}	
}
