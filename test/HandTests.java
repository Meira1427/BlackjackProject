import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.meirapentermann.blackjack.Hand;
import com.meirapentermann.cards.Card;
import com.meirapentermann.cards.Deck;

public class HandTests {
	Deck d;
	Hand h;
	Card c1, c2, c3;

	@Before
	public void setUp() throws Exception {
		d = new Deck();
		d.shuffleDeck();
		c1 = d.dealCard();
		c2 = d.dealCard();
		c3 = d.dealCard();
		h = new Hand();
		
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		h = null;
		c1 = null;
		c2 = null;
		c3 = null;
	}
	
	@Test
	public void test_adding_cards_to_hand() {
		h.addCard(c1);
		h.addCard(c2);
		System.out.println(h);
		assertEquals(2, h.getHand().size());
		
	}

}
