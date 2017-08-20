import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.meirapentermann.blackjack.*;
import com.meirapentermann.cards.*;
import com.meirapentermann.participant.*;

public class AceTests {
	Scanner keyboard = new Scanner(System.in);
	Deck d;
	Hand h, h2;
	Card c1, c2, c3, c4, c5;
	BlackjackGame game = new BlackjackGame(keyboard);

	@Before
	public void setUp() throws Exception {
		d = new Deck();
		d.shuffleDeck();
		c1 = new Card(Suit.SPADES, Rank.ACE);
		c2 = new Card(Suit.CLUBS, Rank.JACK);
		c3 = new Card(Suit.CLUBS, Rank.TWO);
		c4 = new Card(Suit.HEARTS, Rank.ACE);
		c5 = new Card(Suit.DIAMONDS, Rank.EIGHT);
		h = new Hand();
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c1);
		h.addCard(c4);
		h.addCard(c5);
		h2 = new Hand();
		h2.addCard(c2);
		h2.addCard(c3);
		h2.addCard(c5);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		h = null;
		h2 = null;
		c1 = null;
		c2 = null;
		c3 = null;
		c4 = null;
		c5 = null;
		keyboard = null;
	}
	
	@Test
	public void test_Aces_true() {
		assertEquals(2, game.acesInHandCount(h.getHand()));
	}
	
	@Test
	public void test_Aces_false() {
		assertEquals(0, game.acesInHandCount(h2.getHand()));
	}
	
	@Test
	public void test_Aces_null() {
		assertNull(game.acesInHandLocation(h2.getHand()));
	}
	
	@Test
	public void test_Aces_Integer_ArrayList() {
		assertEquals(2, game.acesInHandLocation(h.getHand()).size());
	}
	
	@Test
	public void changing_Ace_to_One() {
		/* Can't do it this way unless we change whole structure
		 * It reaches in and changes value of all Aces
		 */
		System.out.println(h.handValue());
		game.changeAceToOne(h.getHand());
		System.out.println(h);
		System.out.println(h.handValue());
		System.out.println("Printing value: " + h.getHand().get(2).getRank().value);
		System.out.println("Printing value: " + h.getHand().get(3).getRank().value);
		Deck d2 = new Deck();
		System.out.println("Generic card " + d2.getDeck().get(0).getRank().value);
		System.out.println("Generic card " + d2.getDeck().get(1).getRank().value);
		System.out.println("Generic card " + d2.getDeck().get(2).getRank().value);
		System.out.println("Generic card " + d2.getDeck().get(3).getRank().value);
	}


}
