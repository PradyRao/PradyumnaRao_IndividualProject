package core;

public class AIDealer extends Participants {

	public AIDealer(Deck deck) {
		super(deck);
		// TODO Auto-generated constructor stub
	}
	
	//constructor for testing
	public AIDealer() {
	  // TODO Auto-generated constructor stub
	  super();
	}

	public String printHand(boolean visible) {
		// TODO Auto-generated method stub
		if(visible == false) {
			return hand.getCards().get(0).toString();
		}
		return super.printHand();	
	}

	public boolean dealerHitHandler() {
		if(getHand().calcScoreWithAces() < 16) {
			return true;
		}
		//soft 17
		else if((getHand().calcScoreWithAces() == 17) && this.getHand().getIsAce()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void turnHandler(Deck deck) {
		// TODO Auto-generated method stub
		while(this.dealerHitHandler()) {
			this.hand.hitMe(deck);
		}
		if(this.getHand().calcScoreWithAces() > 17 && this.getHand().calcScoreWithAces() <= 21) {
			busted = false;
			stand = true;
		}
		else {
			busted = true;
			stand = false;
		}
	}
	
}
