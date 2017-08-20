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
		c1 = new Card(Suit.SPADES, Rank.ACE, 11);
		c2 = new Card(Suit.CLUBS, Rank.JACK, 10);
		c3 = new Card(Suit.CLUBS, Rank.TWO, 2);
		c4 = new Card(Suit.HEARTS, Rank.ACE, 11);
		c5 = new Card(Suit.DIAMONDS, Rank.EIGHT, 8);
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
		assertEquals(42, h.handValue());
	}


}
