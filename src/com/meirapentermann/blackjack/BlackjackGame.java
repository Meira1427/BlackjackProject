package com.meirapentermann.blackjack;

import com.meirapentermann.participant.*;
import com.meirapentermann.cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private UserInput input;
	private boolean gameOver;
	private boolean playerBust;
	private boolean splitBust;
	private boolean dealerBust;
	private boolean split;
	String playAgain;
	String answer;
	String splitAnswer;
	
	/*
	 * Constructor requires a keyboard to set u the UserInput class
	 * Then everything is initialized.
	 */
	public BlackjackGame (Scanner keyboard) {
		player = new Player("Player"); 	// initialize player
		dealer = new Dealer();			// initialize dealer
		deck = new Deck();	//create new deck
		deck.shuffleDeck();	//shuffle deck
		input = new UserInput(keyboard); //set up class that returns clean user input
		gameOver = false;	//booleans initialized to false
		playerBust = false;	//booleans initialized to false
		splitBust = false;	//booleans initialized to false
		dealerBust = false;	//booleans initialized to false
		split = false;		//booleans initialized to false
		playAgain = "y";	//to enter while loop
		answer = "h";		//initialize hit or stay variables; will be automatic if split
		splitAnswer = "x";	//only deal with this one if isSplit
	}
	
	/*
	 * full game combines all of the game logic methods
	 * watches for early game over
	 * loops for player to playAgain
	 */
	public void fullGame() {
		while(playAgain.equals("y")) {
			int winMain = 0;
			int winSplit = 0;
			printSuitBar();
			initialSetUp();
			determineNextSteps();
			if(!isGameOver()) {
				expandPlayerHand();
			}
			if(!isGameOver()) {
				expandDealerHand();
				getDealer().displayHand(true);
			}
			if(!isSplit()) {
				String d = "";
				winMain = calculateWin(getPlayer().getHandHand(), d);
			}
			else {
				String d = " main hand";
				winMain = calculateWin(getPlayer().getHandHand(), d);
				d = " split hand";
				winSplit = calculateWin(getPlayer().getSplitHand(), d);
			}
			tearDown();
			this.playAgain = input.yesOrNo("Would you like to play again? (y/n) ");
		}
	}
	
	/*
	 * initial set-up deals 2 cards each, starting with player
	 * then it displays hands, full hand for player, half hand for dealer
	 */
	public void initialSetUp() {
		Card c1, c2;
		for(int i = 0; i< 2; i++) { // 2 cards each; player & dealer
			c1 = deck.dealCard();
			player.updateHand(c1);
			c2 = deck.dealCard();
			dealer.updateHand(c2);
		}
		player.displayHand(isSplit()); // starts out as false for split; display normally
		if (dealer.handValue() == 21 || player.handValue() == 21) {
			dealer.displayHand(true); //if dealer or player has natural BlackJack; print full dealer hand
		}
		else {
			dealer.displayHand(false); // false means full hand is not shown yet
		}
	}
	
	/*
	 * to watch for instant win and possible split
	 */
	public void determineNextSteps() {
		if (dealer.handValue() == 21 || player.handValue() == 21) {
			gameOver = true;
		}
		if(!(dealer.handValue()==21)) {
			Rank r0 = player.getHandList().get(0).getRank();
			Rank r1 = player.getHandList().get(1).getRank();
			String toSplit = "";
			if(   (r0 == Rank.KING || r0 == Rank.QUEEN || r0 == Rank.JACK || r0 == Rank.TEN) 
			   && (r1 == Rank.KING || r1 == Rank.QUEEN || r1 == Rank.JACK || r1 == Rank.TEN)){
				toSplit = input.yesOrNo("Would you like to split? (y/n) ");
			}
			else if(r0 == r1) {
				toSplit = input.yesOrNo("Would you like to split? (y/n) ");
			}
			if (toSplit.equals("y")) {
				this.split = true;
				player.getSplitList().add(player.getHandList().remove(0));
			}
		}		
	}
	
	public void expandPlayerHand() {
		if(!isSplit()) {
			answer = input.hitOrStay(); //if not split, need to prompt user
		}
		else {
			splitAnswer = "h";			//if split, change splitAnswer to "h" for first round
		}
		while (answer.equals("h") || splitAnswer.equals("h")) {
			if(answer.equals("h"))
				player.updateHand(deck.dealCard());		//new card for main hand
			if (isSplit() && splitAnswer.equals("h")) {
				player.updateHandSplit(deck.dealCard());//new card for split hand
			}
			player.displayHand(isSplit()); //show updated hand - if isSplit use split format printing
			if(player.handValue() < 21 || (isSplit() && player.splitHandValue() < 21)) { // 2nd half only fires if split
				if(isSplit()) { //need to clarify which hand to hit if both might need it
					if(player.handValue() < 21 ) {
						System.out.print("For 1st Hand: ");
						answer = input.hitOrStay();
					}
					if(player.splitHandValue() < 21) {
						System.out.print("For 2nd Hand: ");
						splitAnswer = input.hitOrStay();
					}
				}
				else if (player.handValue() < 21) { //get here when there is no split
					answer = input.hitOrStay();
				}
			}
			if( (player.handValue() > 21 && player.getHandHand().acesInHandCount() > 0) ) { //try to fix Ace situation
				player.getHandHand().dealWithAces();
				System.out.println("Hand with Aces Adjusted");
				player.displayHand(isSplit()); //show updated hand - if isSplit use split format printing
			}
			if (isSplit() && player.splitHandValue() > 21 && player.getSplitHand().acesInHandCount() > 0) { //if Aces need to be switched
				player.getSplitHand().dealWithAces();
				System.out.println("Hand with Aces Adjusted");
				player.displayHand(isSplit()); //show updated hand - if isSplit use split format printing
			}
			if(player.handValue()==21) {
				answer = "s";
			}
			if(isSplit() && player.splitHandValue()==21) {
				splitAnswer = "s";
			}
			if(answer.equals("s") && splitAnswer.equals("s")) {
				break;
			}	
			if(isSplit() && player.splitHandValue() > 21) { // over 21 split hand
				//System.out.println("Setting splitBust to true");
				splitAnswer = "bust";
				this.splitBust = true;
			}
			if(player.handValue() > 21) { // over 21 main hand
				//System.out.println("Setting playerBust to true");
				answer = "bust";
				this.playerBust = true;
				if(!isSplit()) {
					this.gameOver = true;
				}
			}
			if(isPlayerBust() && isSplitBust()) {
				this.gameOver = true;
			}
		} //end while loop 
	} // end explandPlayerHand
	
	public void expandDealerHand() {
		int total = dealer.handValue();
		while(total < 17) {
			dealer.updateHand(deck.dealCard());
			total = dealer.handValue();
		}
		if( (dealer.handValue()> 21) && (dealer.getHandHand().acesInHandCount() > 0)) {
			//List<Integer> list = dealer.getHandHand().acesInHandLocation();
			dealer.getHandHand().dealWithAces();
		}
		if(dealer.handValue() < 17) {
			expandDealerHand();       //recursive call back to itself, until dealer hand over 17
		}
		if(dealer.handValue() > 21) {
			this.dealerBust = true;
		}
	}
	
	/*
	 * Starts with most unlikely case of a natural BlackJack for both dealer & player
	 * Moves on in order of elimination of conditions of cards
	 */
	public int calculateWin(Hand H, String description) {
		if( (H.handValue() == 21 && H.getHandList().size()==2) 
		 && (dealer.handValue() == 21 && dealer.getHandList().size()==2)) {
			System.out.println("Natural BlackJack! Both Player" + description + " & Dealer. It's a Tie!");
			return 0;
		}
		else if (H.handValue() == 21 && H.getHandList().size()==2){
			System.out.println("Natural BlackJack" + description + "! Player wins!");
			return 1;
		}
		else if (dealer.handValue() == 21 && dealer.getHandList().size()==2) {
			System.out.println("Natural BlackJack! Dealer wins" + description + "!");
			return -1;
		}
		else if (H.handValue() == 21 && dealer.handValue() == 21) {
			System.out.println("BlackJack! Both Player" + description + " & Dealer. It's a Tie!");
			return 0;
		}
		else if (isPlayerBust() && H==getPlayer().getHandHand()) {
			System.out.println("Player busted" + description + ". Dealer wins!");
			return -1;
		}
		else if (isSplit() && isSplitBust() && H==getPlayer().getSplitHand()) {
			System.out.println("Player busted" + description + ". Dealer wins!");
			return -1;
		}
		else if (isDealerBust()) {
			System.out.println("Dealer busted. Player wins" + description + "!");
			return 1;
		}
		else if (H.handValue() == dealer.handValue()) {
			System.out.println("It's a Tie" + description + "!");
			return 0;
		}
		else if (H.handValue() > dealer.handValue()) {
			System.out.println("Player wins" + description + "!");
			return 1;
		}
		else {
			System.out.println("Dealer wins" + description + ".");
			return -1;
		}
	}
		
	public void tearDown()	{
		this.deck = new Deck();
		this.deck.shuffleDeck();
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		Hand hand3 = new Hand();
		this.getPlayer().setHand(hand1);
		this.getDealer().setHand(hand2);
		this.getPlayer().setSplit(hand3);
		gameOver = false;
		playerBust = false;
		dealerBust = false;
		splitBust = false;
		split = false;
		answer = "h";	
		splitAnswer = "x";
	}
	
	public void printSuitBar() {
		System.out.print("\n" + '\u2665' + "   " + '\u2660' + "   ");  
		System.out.print('\u2663' + "   " + '\u2666' + "   "); 
		System.out.print('\u2665' + "   " + '\u2660' + "   ");  
		System.out.print('\u2663' + "   " + '\u2666' + "   "); 
		System.out.print('\u2665' + "   " + '\u2660' + "   ");  
		System.out.print('\u2663' + "   " + '\u2666' + "   "); 
		System.out.print('\u2665' + "   " + '\u2660' + "   ");  
		System.out.print('\u2663' + "   " + '\u2666' + "   "); 
		System.out.print('\u2665' + "   " + '\u2660' + "   ");  
		System.out.print('\u2663' + "   " + '\u2666' + "   "); 
		System.out.print('\u2665' + "   " + '\u2660' + "   ");  
		System.out.print('\u2663' + "   " + '\u2666' + "   \n\n"); 
	}

	
	/*
	 * Getters & Setters
	 */

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public UserInput getInput() {
		return input;
	}

	public void setInput(UserInput input) {
		this.input = input;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isPlayerBust() {
		return playerBust;
	}

	public void setPlayerBust(boolean playerBust) {
		this.playerBust = playerBust;
	}
	
	public boolean isSplitBust() {
		return splitBust;
	}

	public void setSplitBust(boolean splitBust) {
		this.splitBust = splitBust;
	}

	public boolean isDealerBust() {
		return dealerBust;
	}

	public void setDealerBust(boolean dealerBust) {
		this.dealerBust = dealerBust;
	}

	public boolean isSplit() {
		return split;
	}

	public void setSplit(boolean split) {
		this.split = split;
	}
}
