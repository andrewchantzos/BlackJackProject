package game;

import java.util.Arrays;

public class Stats {

	private String m = "";
	
	private String[] p;

	public String getM() {
		return m;
	}
	
	public Stats(int numOfPlayers) {
		p = new String[numOfPlayers];
	}

	public void setM(String m) {
		this.m = m;
	}

	public void fillDealer(int playersHandTotal) {
		if (playersHandTotal > 21) {
			m = "X";
		}
		else {
			m = String.valueOf(playersHandTotal);
		}
	}
	
	public String handResult(Hand hand) {
		String res = hand.getPlayerWins();
		String st = "";
		if (res.equals("WIN")) {
			st = "W";
		}
		else if (res.equals("TIE")) {
			st = "T";
		}
		else if (res.equals("LOSE")) {
			st = "L";
		}
		return st;
	}
	
	public void fillPlayer(Player player, int index) {
		Hand hand = player.getHand();

		
		if (player.getSplits() == 0) {
			p[index] = handResult(hand);
		}
		else if (player.getSplits() == 1) {
			Hand hand2 = player.getHand2();

			p[index] = handResult(hand);
			p[index] += handResult(hand2);

		}
		else if (player.getSplits() == 2) {
			Hand hand2 = player.getHand2();
			Hand hand3 = player.getHand3();

			p[index] = handResult(hand);
			p[index] += handResult(hand2);
			p[index] += handResult(hand3);
		}
		else if (player.getSplits() == 3) {
			Hand hand2 = player.getHand2();
			Hand hand3 = player.getHand3();
			Hand hand4 = player.getHand4();

			p[index] = handResult(hand);
			p[index] += handResult(hand2);
			p[index] += handResult(hand3);
			p[index] += handResult(hand4);
		}
	}

	public String[] getP() {
		return p;
	}

	public void setP(String[] p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Stats [m=" + m + ", p=" + Arrays.toString(p) + "]";
	}
}
