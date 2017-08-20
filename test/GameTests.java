import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.meirapentermann.blackjack.BlackjackGame;

public class GameTests {
	
	BlackjackGame game;

	@Before
	public void setUp() throws Exception {
		game = new BlackjackGame();
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void test_hands_dealt() {
		game.initialSetUp();
		assertEquals(2, game.getDealer().getHand().size());
		assertEquals(2, game.getPlayer().getHand().size());
	}

}
