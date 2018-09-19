package core;
import java.util.Scanner;

import org.junit.Test;
import junit.framework.TestCase;
public class GameController_Test extends TestCase {
	UI View = new UI();
	Scanner sc = new Scanner(System.in);
	@Test
	public void testInputTypes() {
		GameController game = new GameController();
		
		View.outputGamePrompt();
		game.setInputType(sc.nextLine());

		System.out.println("Please print c and then f");
		assertEquals("c", game.getInputType());
		
		View.outputGamePrompt();
		game.setInputType(sc.nextLine());
		assertEquals("f", game.getInputType());

	}
	@Test
	public void testInitialize() {
		GameController game = new GameController();
		Deck deck = new Deck();
		
		game.initialize(deck);
		
		assertEquals(2, game.dealer.getHand().getSize());
		assertEquals(2, game.human.getHand().getSize());
	}
	@Test
	public void testGameWinner() {
		GameController game = new GameController();

		game.dealer = new AIDealer();
		game.human = new HumanPlayer();

		game.dealer.getHand().addCard(new Card("H", "5", 5));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("H", "7", 7));
		game.human.getHand().addCard(new Card("S", "10", 10));
		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		//test can be changed for initial win too, change some score to 21
		game.initialBJWinner();
		//check winner, in this case it should be dealer
		game.selectWinner();
		
		assertEquals(false, game.dealerWin); //this does work, but needs to be changed to account for bestScore for player
		
		
	}
	@Test
	public void testFilePlay() {
		/*GameController game = new GameController();

		game.dealer = new AIDealer();
		game.human = new HumanPlayer();

		//instead of manual add, it reads from file and adds cards and chooses winner
		game.dealer.getHand().addCard(new Card("H", "5", 5));
		game.dealer.getHand().addCard(new Card("S", "10", 10));
		
		game.human.getHand().addCard(new Card("H", "7", 7));
		game.human.getHand().addCard(new Card("S", "10", 10));
		
		//test can be changed for initial win too, change some score to 21
		game.initialBJWinner();
		//check winner, in this case it should be dealer
		game.selectWinner();
		
		//dummy case
		assertEquals(false, false);*/
	}
	
	@Test
	public void testSplittingGame() {
		GameController game = new GameController();
		System.out.println("Split Test starts here");
		game.dealer = new AIDealer();
		game.human = new HumanPlayer();
		
		game.human.getHand().addCard(new Card("H", "K", 10));
		game.dealer.getHand().addCard(new Card("S", "7", 7));
		
		game.human.getHand().addCard(new Card("D", "K", 10));
		game.dealer.getHand().addCard(new Card("C", "5", 5));
		
		View.displayHand("human", game.human.printHand());
		View.displayHand("dealer", game.dealer.printHand());
		
		game.consolePlay();
	}
	
	@Test
	public void testBestScore() {
		GameController game = new GameController();
		
		game.human = new HumanPlayer();
		game.human.hasSplit = true;
		//game.human2 = new HumanPlayer();
		game.human.split[0] = new Hand();
		game.human.split[1] = new Hand();
		
		
		game.human.split[0].addCard(new Card("H", "K", 10));
		game.human.split[0].addCard(new Card("S", "7", 7));
		
		game.human.split[1].addCard(new Card("D", "K", 10));
		game.human.split[1].addCard(new Card("C", "5", 5));

		
		
		System.out.println(game.getBestScore(game.human));
		
		assertEquals(17, game.getBestScore(game.human));
		
	}

}
