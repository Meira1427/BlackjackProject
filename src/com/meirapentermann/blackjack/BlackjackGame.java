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
			printSuitBar();
			initialSetUp();
			determineNextSteps();
			if(!this.gameOver) {
				expandPlayerHand();
			}
			if(!this.gameOver) {
				expandDealerHand();
				dealer.displayHand(true);
			}
			if(!isSplit()) {
				calculateWin();
			}
			else {
				calculateWinSplit();
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
			Rank r0 = player.getHand().get(0).getRank();
			Rank r1 = player.getHand().get(1).getRank();
			String toSplit = "";
			if(   (r0 == Rank.KING || r0 == Rank.QUEEN || r0 == Rank.JACK || r0 == Rank.TEN) 
			   && (r1 == Rank.KING || r1 == Rank.QUEEN || r1 == Rank.JACK || r1 == Rank.TEN)){
				toSplit = input.yesOrNo("Would you like to split? (y/n) ");
			}
			else if(r0 == Rank.ACE && r1 == Rank.ACE) {
				toSplit = input.yesOrNo("Would you like to split? (y/n) ");
			}
			if (toSplit.equals("y")) {
				this.split = true;
				player.getSplit().add(player.getHand().remove(0));
			}
		}		
	}
	
	/*
	 * returns number of Aces in given hand
	 */
	public int acesInHandCount(List<Card> cards) {
		int count = 0;
		for(Card card: cards) {
			if (card.getRank() == Rank.ACE) {
				count++;
			}
		}
		return count;
	}
	
	/*
	 * Gives position of all Aces in hand
	 */
	public List<Integer> acesInHandLocation(List<Card> cards) {
		if (acesInHandCount(cards)==0) {
			return null;
		}
		else {
			List<Integer> ints = new ArrayList<>();
			for(int i = 0; i<cards.size(); i++) {
				if (cards.get(i).getRank()==Rank.ACE) {
					ints.add(i);
				}
			}
			return ints;
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
			if(player.handValue()==21) {
				answer = "s";
			}
			if(isSplit() && player.splitHandValue()==21) {
				splitAnswer = "s";
			}
			if(answer.equals("s") && splitAnswer.equals("s")) {
				break;
			}	
			if( (player.handValue() > 21 && acesInHandCount(player.getHand()) > 0) //try to fix Ace situation
				|| (isSplit() && player.splitHandValue() > 21 && acesInHandCount(player.getSplit()) > 0)) { //if Aces need to be switched
				if (player.handValue() > 21 && acesInHandCount(player.getHand()) > 0) { //main hand branch
					dealWithAcesMainHand();
				}
				if (isSplit() && player.splitHandValue() > 21 && acesInHandCount(player.getSplit()) > 0) {
					dealWithAcesSplitHand();
				}
			}
			if(player.handValue() > 21 || (isSplit() && player.splitHandValue() > 21)) { //if still over 21
				if(player.handValue() > 21) { // over 21 main hand
					answer = "bust";
					this.gameOver = true;
					this.playerBust = true;
				}
				if(isSplit() && player.splitHandValue() > 21) { // over 21 split hand
					splitAnswer = "bust";
					this.splitBust = true;
				}
			}
		} //end while loop 
	}
	
	public void expandDealerHand() {
		int total = dealer.handValue();
		while(total < 17) {
			dealer.updateHand(deck.dealCard());
			total = dealer.handValue();
		}
		if( (dealer.handValue()> 21) && (acesInHandCount(dealer.getHand()) > 0)) {
			List<Integer> list = acesInHandLocation(dealer.getHand());
			int count = list.size();
			while(( dealer.handValue() > 21) && count > 0 ) {
				dealer.getHand().get(list.get(count-1)).setValue(1);
				count--;
			}
		}
		if(dealer.handValue() < 17) {
			expandDealerHand();       //recursive call back to itself, until dealer hand over 17
		}
		if(dealer.handValue() > 21) {
			this.dealerBust = true;
		}
	}
	
	public void dealWithAcesMainHand() {
		if(acesInHandCount(player.getHand()) > 0) {
			List<Integer> list = acesInHandLocation(player.getHand());
			int count = list.size();
			while(( player.handValue() > 21) && count > 0 ) {
				player.getHand().get(list.get(count-1)).setValue(1);
				count--;
			}
			System.out.println("Hand with Aces Adjusted");
			player.displayHand(split);
			if(player.handValue()<21) {
				answer = input.hitOrStay();
			}
			else {
				answer = "bust";
				this.playerBust = true;
			}
		}
	}
	
	public void dealWithAcesSplitHand() {
		if(acesInHandCount(player.getSplit()) > 0) {
			List<Integer> list = acesInHandLocation(player.getSplit());
			int count = list.size();
			while(( player.splitHandValue() > 21) && count > 0 ) {
				player.getSplit().get(list.get(count-1)).setValue(1);
				count--;
			}
			System.out.println("Hand with Aces Adjusted");
			player.displayHand(split);
			if(player.splitHandValue()<21) {
				System.out.print("For 2nd Hand: ");
				splitAnswer = input.hitOrStay();
			}
			else {
				answer = "bust";
				this.splitBust = true;
			}
		}
	}
	
	/*
	 * Starts with most unlikely case of a natural BlackJack for both dealer & player
	 * Moves on in order of elimination of conditions of cards
	 */
	public void calculateWin() {
		if(player.handValue() == 21 && player.getHand().size()==2 
		 & dealer.handValue() == 21 & dealer.getHand().size()==2) {
			System.out.println("Natural BlackJack! Both Player & Dealer. It's a Tie!");
		}
		else if (player.handValue() == 21 && player.getHand().size()==2) {
			System.out.println("Natural BlackJack! Player wins!");
		}
		else if (dealer.handValue() == 21 && dealer.getHand().size()==2) {
			System.out.println("Natural BlackJack! Dealer wins!");
		}
		else if (player.handValue() == 21 && dealer.handValue() == 21) {
			System.out.println("BlackJack! Both Player & Dealer. It's a Tie!");
		}
		else if(this.playerBust && this.dealerBust) {
			System.out.println("Both Player and Dealer bust!");
			//I think this is unreachable code. Dealer will not deal if player busts
		}
		else if (this.playerBust) {
			System.out.println("Player busted. Dealer wins!");
		}
		else if (this.dealerBust) {
			System.out.println("Dealer busted. Player wins!");
		}
		else if (player.handValue() == dealer.handValue()) {
			System.out.println("It's a Tie!");
		}
		else if (player.handValue() > dealer.handValue()) {
			System.out.println("Player wins!");
		}
		else {
			System.out.println("Dealer wins.");
		}
	}
	
	/*
	 * Gets convoluted if there is a split. Wanted to walk through different method
	 * even if similar to other method. Will save from asking isSplit()? every lien
	 */
	public void calculateWinSplit() {
		if ( (playerBust && !splitBust) || (splitBust && !playerBust) ) {
			System.out.println("Player bust one hand.");
		}
		if(((player.handValue() == 21 && player.getHand().size()==2) 
				|| (player.splitHandValue() == 21 && player.getSplit().size()==2))
				&& (dealer.handValue() == 21 & dealer.getHand().size()==2) ) {
			System.out.println("Natural BlackJack! Both Player & Dealer. It's a Tie!");
		}
		else if ((player.handValue() == 21 && player.getHand().size()==2) 
				|| (player.splitHandValue() == 21 && player.getSplit().size()==2)){
			System.out.println("Natural BlackJack! Player wins!");
		}
		else if (dealer.handValue() == 21 && dealer.getHand().size()==2) {
			System.out.println("Natural BlackJack! Dealer wins!");
		}
		else if ((player.handValue() == 21 || player.splitHandValue()== 21) && dealer.handValue() == 21) {
			System.out.println("BlackJack! Both Player & Dealer. It's a Tie!");
		}
		else if (this.playerBust && this.splitBust) {
			System.out.println("Player busted both hands. Dealer wins!");
		}
		else if (this.dealerBust) {
			if(!playerBust && !splitBust) {
				System.out.println("Dealer busted. Player wins two hands!");
			}
			else if(playerBust || splitBust) {
				System.out.println("Dealer busted. Player wins one hand!");
			}
			else {
				System.out.println("Everyone busted.");
			}
		}
		else if ( ( (player.handValue() > dealer.handValue()) && !playerBust)
				|| ((player.splitHandValue() > dealer.handValue()) && !splitBust))  {
			
			if ((player.handValue() > dealer.handValue()) && (player.splitHandValue() > dealer.handValue()) 
					&& !playerBust && !splitBust) {
				System.out.println("Player wins two hands!");
			}
			else {
				System.out.println("Player wins one hand!");
			}
		}
		else if ((player.handValue() == dealer.handValue()) || (player.splitHandValue() == dealer.handValue())) {
			System.out.println("It's a Tie!");
		}
		else {
			System.out.println("Dealer wins.");
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
