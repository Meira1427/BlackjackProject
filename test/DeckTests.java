import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.meirapentermann.cards.Deck;

public class DeckTests {
	Deck d;

	@Before
	public void setUp() throws Exception {
		d = new Deck();
	}

	@After
	public void tearDown() throws Exception {
		d = null;
	}

	@Test
	public void test_new_deck_has_52_cards() {
		assertEquals(52, d.getDeck().size());
	}
	
	public void test_two_cards_dealt_has_50_cards() {
		d.dealCard();
		d.dealCard();
		assertEquals(50, d.getDeck().size());
		assertEquals((52-d.getNumDealt()), d.getDeck().size() );
		assertEquals(50, d.cardSLeft());
	}
	
	public void test_null_if_all_cards_dealt () {
		for(int i = 0; i<52; i++) {
			d.dealCard();
		}
		assertEquals(0, d.getDeck().size());
		assertEquals(0, d.cardSLeft());
		assertNull(d.dealCard());
	}

}
