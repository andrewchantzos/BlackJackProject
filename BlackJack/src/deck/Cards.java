package deck;

/**
 * 
 * Cards suit and number
 * 
 * @author DANISH MOHD
 *
 */

public class Cards {
	
	private Suits cardSuit;
	private int cardNum;
	private String[] numString = {"A", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	/**
	 * Cards constructor
	 * @param sType
	 * @param sNum
	 */
	public Cards(Suits stype, int snum){
		 
		this.cardSuit = stype;
		
		if(snum >=1 && snum <= 13)
			this.cardNum  = snum;
		else{
			
			System.err.println(snum+" is not a valid card number\n");
			System.exit(1);
		}
	}
	
	public int getCardNumber(){
		
		return this.cardNum;
	}
	
	public String getStringCardNumber() {
		
		Integer tmp = this.cardNum;
		if (tmp == 1)
			return "A";
		if (tmp >= 10)
			return "T";
		return tmp.toString();
	}
	
	public String toString(){
		
		return this.numString[this.cardNum - 1]+" of "+this.cardSuit.toString();
	}
}
