import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.meirapentermann.blackjack.BlackjackGame;
import com.meirapentermann.cards.Card;
import com.meirapentermann.cards.Rank;
import com.meirapentermann.cards.Suit;

public class GameTests {
	Scanner keyboard = new Scanner(System.in);
	BlackjackGame game;
	Card cA1, cA2, cK, cQ, cJ, c2, c10;
	

	@Before
	public void setUp() throws Exception {
		game = new BlackjackGame(keyboard);
		cA1 = new Card(Suit.SPADES, Rank.ACE, 11);
		cA2 = new Card(Suit.HEARTS, Rank.ACE, 11);
		cK = new Card(Suit.HEARTS, Rank.KING, 10);
		cQ = new Card(Suit.DIAMONDS, Rank.QUEEN, 10);
		cJ = new Card(Suit.CLUBS, Rank.JACK, 10);
		c2 = new Card(Suit.CLUBS, Rank.TWO, 2);
		c10 = new Card(Suit.DIAMONDS, Rank.TEN, 10);
	}

	@After
	public void tearDown() throws Exception {
		game = null;
		cA1 = null;
		cA2 = null;
		cK = null;
		cQ = null;
		cJ = null;
		c2 = null;
		c10 = null;
	}

	@Test
	public void test_hands_dealt() {
		game.initialSetUp();
		assertEquals(2, game.getDealer().getHand().size());
		assertEquals(2, game.getPlayer().getHand().size());
	}
	
//	@Test
//	public void test_full_game_generic_watch_output() {
//		game.fullGame();
//	}
	
	@Test
	public void test_player_21_first_draw_dealer_too() {
		game.getPlayer().updateHand(cA1);
		game.getPlayer().updateHand(cK);
		game.getPlayer().displayHand(false);
		game.getDealer().updateHand(cA2);
		game.getDealer().updateHand(cQ);
		if (game.getDealer().handValue() == 21) {
			game.getDealer().displayHand(true); //dealer has natural BlackJack
		}
		else {
			game.getDealer().displayHand(false); // false means not full hand is shown yet
		}
		game.determineNextSteps();
		if(!(game.getPlayer().handValue() == 21 || game.isGameOver()))  {
			System.out.println("Should not reach here 21 Both - First");
			game.expandPlayerHand();
		}
		if(!game.isGameOver()) {
			System.out.println("Okay to reach here, so Dealer's Hand Prints");
			game.expandDealerHand();
		}
		game.calculateWin();
	}
	
	@Test
	public void test_player_21_first_draw() {
		game.getPlayer().updateHand(cA1);
		game.getPlayer().updateHand(cK);
		game.getPlayer().displayHand(false);
		game.getDealer().updateHand(c2);
		game.getDealer().updateHand(c10);
		if (game.getDealer().handValue() == 21) {
			game.getDealer().displayHand(true); //dealer has natural BlackJack
		}
		else {
			game.getDealer().displayHand(false); // false means not full hand is shown yet
		}
		game.determineNextSteps();
		if(!(game.getPlayer().handValue() == 21 || game.isGameOver()))  {
			System.out.println("Should not reach here - 21 Player - First");
			game.expandPlayerHand();
		}
		if(!game.isGameOver()) {
			System.out.println("Should not reach here - 21 Player - Second");
			game.expandDealerHand();
		}
		game.calculateWin();
	}
	
	@Test
	public void test_dealer_immediate_BlackJack() {
		game.getPlayer().updateHand(game.getDeck().dealCard());
		game.getPlayer().updateHand(game.getDeck().dealCard());
		game.getPlayer().displayHand(false);
		game.getDealer().updateHand(cA1);
		game.getDealer().updateHand(cQ);
		if (game.getDealer().handValue() == 21) {
			game.getDealer().displayHand(true); //dealer has natural BlackJack
		}
		else {
			game.getDealer().displayHand(false); // false means not full hand is shown yet
		}
		game.determineNextSteps();
		if(!(game.getPlayer().handValue() == 21 || game.isGameOver()))  {
			System.out.println("Should not reach here. Dealer has immediate BlackJack");
			game.expandPlayerHand();
		}
		if(!game.isGameOver()) {
			System.out.println("Should not reach here either. Second");
			game.expandDealerHand();
		}
		game.calculateWin();
	}

}
