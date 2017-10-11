package strategy;

public class StrategyCombo {

	
	private String dealerCard = "";
	
	private String playerHand = "";

	public String getDealerCard() {
		return dealerCard;
	}

	public void setDealerCard(String dealerCard) {
		this.dealerCard = dealerCard;
	}

	public String getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(String playerHand) {
		this.playerHand = playerHand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealerCard == null) ? 0 : dealerCard.hashCode());
		result = prime * result + ((playerHand == null) ? 0 : playerHand.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrategyCombo other = (StrategyCombo) obj;
		if (dealerCard == null) {
			if (other.dealerCard != null)
				return false;
		} else if (!dealerCard.equals(other.dealerCard))
			return false;
		if (playerHand == null) {
			if (other.playerHand != null)
				return false;
		} else if (!playerHand.equals(other.playerHand))
			return false;
		return true;
	}

	public StrategyCombo(String dealerCard, String playerHand) {
		super();
		this.dealerCard = dealerCard;
		this.playerHand = playerHand;
	}

	@Override
	public String toString() {
		return "StrategyCombo [dealerCard=" + dealerCard + ", playerHand=" + playerHand + "]";
	}
	
	
}
