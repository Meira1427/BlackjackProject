import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.meirapentermann.blackjack.BlackjackGame;

public class GameTests {
	Scanner keyboard = new Scanner(System.in);
	BlackjackGame game;

	@Before
	public void setUp() throws Exception {
		game = new BlackjackGame(keyboard);
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

//	@Test
//	public void test_hands_dealt() {
//		game.initialSetUp();
//		assertEquals(2, game.getDealer().getHand().size());
//		assertEquals(2, game.getPlayer().getHand().size());
//	}
	
	@Test
	public void test_full_game() {
		game.fullGame();
	}

}
