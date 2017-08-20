import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.meirapentermann.cards.Card;
import com.meirapentermann.cards.Deck;
import com.meirapentermann.cards.Rank;
import com.meirapentermann.cards.Suit;

public class DeckTests {
	Deck d;
	Card c;

	@Before
	public void setUp() throws Exception {
		d = new Deck();
		c = new Card(Suit.CLUBS, Rank.EIGHT, 8);
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		c = null;
	}

	@Test
	public void test_new_deck_has_52_cards() {
		assertEquals(52, d.getDeck().size());
	}
	
	@Test
	public void test_two_cards_dealt_has_50_cards() {
		d.dealCard();
		d.dealCard();
		assertEquals(50, d.getDeck().size());
		assertEquals((52-d.getNumDealt()), d.getDeck().size() );
		assertEquals(50, d.cardSLeft());
	}
	
	@Test
	public void test_null_if_all_cards_dealt () {
		for(int i = 0; i<52; i++) {
			d.dealCard();
		}
		assertEquals(0, d.getDeck().size());
		assertEquals(0, d.cardSLeft());
		assertNull(d.dealCard());
	}
	
	@Test
	public void test_full_deck_to_print_and_examine() {
		for(int i = 0; i<52; i++) {
			c = d.dealCard();
			System.out.println(c + ": " + c.getValue());
		}
	}
	
}

