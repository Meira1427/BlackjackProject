import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.meirapentermann.blackjack.*;
import com.meirapentermann.cards.*;
import com.meirapentermann.participant.*;

public class HandTests {
	Deck d;
	Hand h;
	Card c1, c2, c3;
	Dealer dealer;
	Player player1;

	@Before
	public void setUp() throws Exception {
		d = new Deck();
		d.shuffleDeck();
		c1 = d.dealCard();
		c2 = d.dealCard();
		c3 = d.dealCard();
		h = new Hand();
		dealer = new Dealer();
		player1 = new Player("John");
		
	}

	@After
	public void tearDown() throws Exception {
		d = null;
		h = null;
		c1 = null;
		c2 = null;
		c3 = null;
		dealer = null;
		player1 = null;
	}
	
	@Test
	public void test_adding_cards_to_hand() {
		h.addCard(c1);
		h.addCard(c2);
		assertEquals(2, h.getHand().size());
		h.addCard(c3);
		//System.out.println(h);
		assertEquals(3, h.getHand().size());
	}
	
	@Test 
	public void test_dealers_hand() {
		h.addCard(c1);
		h.addCard(c2);
		dealer.setHand(h);
		dealer.displayHand(false);
		dealer.displayHand(true);
		assertEquals(2, dealer.getHand().size());
	}
	
	@Test 
	public void test_players_hand() {
		h.addCard(c1);
		h.addCard(c2);
		player1.setHand(h);
		player1.displayHand(false);
		assertEquals(2, player1.getHand().size());
	}

}
