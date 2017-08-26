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
	int winMain, winSplit;
	

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

//	@Test
//	public void test_hands_dealt() {
//		game.initialSetUp();
//		assertEquals(2, game.getDealer().getHand().size());
//		assertEquals(2, game.getPlayer().getHand().size());
//	}
//
//	@Test
//	public void test_player_21_first_draw_dealer_too() {
//		game.getPlayer().updateHand(cA1);
//		game.getPlayer().updateHand(cK);
//		//game.getPlayer().displayHand(false);
//		game.getDealer().updateHand(cA2);
//		game.getDealer().updateHand(cQ);
//		game.determineNextSteps();
//		winMain = game.calculateWin(game.getPlayer().getHandHand(), "");
//		assertEquals(0, winMain);
//	}
//	
//	@Test
//	public void test_player_21_first_draw() {
//		game.getPlayer().updateHand(cA1);
//		game.getPlayer().updateHand(cK);
//		//game.getPlayer().displayHand(false);
//		game.getDealer().updateHand(c2);
//		game.getDealer().updateHand(c10);
//		winMain = game.calculateWin(game.getPlayer().getHandHand(), "");
//		assertEquals(1, winMain);
//	}
//	
//	@Test
//	public void test_dealer_immediate_BlackJack() {
//		game.getPlayer().updateHand(game.getDeck().dealCard());
//		game.getPlayer().updateHand(game.getDeck().dealCard());
//		//game.getPlayer().displayHand(false);
//		game.getDealer().updateHand(cA1);
//		game.getDealer().updateHand(cQ);
//		winMain = game.calculateWin(game.getPlayer().getHandHand(), "");
//		assertEquals(-1, winMain);
//	}
	
	@Test
	public void test_split_possibility1() {
		int winMain = 0;
		int winSplit = 0;
		game.getPlayer().updateHand(cQ);
		game.getPlayer().updateHand(cK);
		game.getPlayer().displayHand(false);
		game.getDealer().updateHand(game.getDeck().dealCard());
		game.getDealer().updateHand(game.getDeck().dealCard());
		game.determineNextSteps();
		if(!game.isGameOver()) {
			game.expandPlayerHand();
		}
		if(!game.isGameOver()) {
			game.expandDealerHand();
			game.getDealer().displayHand(true);
		}
		if(!game.isSplit()) {
			String d = "";
			winMain = game.calculateWin(game.getPlayer().getHandHand(), d);
		}
		else {
			String d = " main hand";
			winMain = game.calculateWin(game.getPlayer().getHandHand(), d);
			d = " split hand";
			winSplit = game.calculateWin(game.getPlayer().getSplitHand(), d);
		}
	}
	
	@Test
	public void switching_Aces_Player() {
		game.getPlayer().updateHand(cA1);
		game.getPlayer().updateHand(cA2);
		game.getPlayer().updateHand(c2);
		game.getPlayer().getHandHand().dealWithAces();
		assertEquals(14, game.getPlayer().handValue());
		
	}
	
	@Test
	public void switching_Aces_Dealer() {
		game.getDealer().updateHand(cA1);
		game.getDealer().updateHand(cA2);
		game.getDealer().updateHand(c2);
		game.getDealer().getHandHand().dealWithAces();
		assertEquals(14, game.getDealer().handValue());
	}
	
	/* Desired Behavior for above test. Just need less printouts.
	 * * * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

Total Value: 24

* * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

Total Value: 24

* * * * * * * * * * * * * * * * *

Hand with Aces Adjusted
* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

Total Value: 14

* * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

Total Value: 25

* * * * * * * * * * * * * * * * *

Hand with Aces Adjusted
* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

Total Value: 15

* * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ Q ♧    Queen of Clubs  ♧ Q ♧ ]

Total Value: 25

* * * * * * * * * * * * * * * * *

Hand with Aces Adjusted
* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ Q ♧    Queen of Clubs  ♧ Q ♧ ]

Total Value: 15

* * * * * * * * * * * * * * * * *

* * * * * * * * * * * * * * * * *

	Dealer's Hand

[ ♤ A ♤    Ace of Spades  ♤ A ♤ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ 2 ♧    Two of Clubs  ♧ 2 ♧ ]

[ ♡ A ♡    Ace of Hearts  ♡ A ♡ ]

[ ♧ Q ♧    Queen of Clubs  ♧ Q ♧ ]

[ ♤ 2 ♤    Two of Spades  ♤ 2 ♤ ]

Total Value: 17

* * * * * * * * * * * * * * * * *
	 */

}
