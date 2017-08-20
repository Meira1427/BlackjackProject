package com.meirapentermann.blackjack;

import com.meirapentermann.participant.*;
import com.meirapentermann.cards.*;
import java.util.Scanner;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private UserInput input;
	private boolean gameOver;
	private boolean playerBust;
	private boolean dealerBust;
	private boolean split;
	
	/*
	 * Constructor requires a keyboard to set u the UserInput class
	 * Then everything is initialized.
	 */
	public BlackjackGame (Scanner keyboard) {
		player = new Player("Player");
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffleDeck();
		input = new UserInput(keyboard);
		gameOver = false;
		playerBust = false;
		dealerBust = false;
		split = false;
	}
	
	/*
	 * full game combines all of the game logic methods
	 * watches for early game over
	 */
	public void fullGame() {
		initialSetUp();
		determineNextSteps();
		if(!(player.handValue() == 21 || this.gameOver)) {
			expandPlayerHand();
		}
		if(!this.gameOver) {
			expandDealerHand();
		}
		calculateWin();
	}
	
	/*
	 * initial set-up deals 2 cards each, starting with player
	 * then it displays hands, full hand for player, half hand for dealer
	 */
	public void initialSetUp() {
		Card c1, c2;
		for(int i = 0; i< 2; i++) {
			c1 = deck.dealCard();
			player.updateHand(c1);
			c2 = deck.dealCard();
			dealer.updateHand(c2);
		}
		player.displayHand(split); // starts out as false for split; display normally
		if (dealer.handValue() == 21) {
			dealer.displayHand(true); //dealer has natural BlackJack; print full hand
		}
		else {
			dealer.displayHand(false); // false means not full hand is shown yet
		}
	}
	
	/*
	 * to watch for instant win and possible split
	 */
	public void determineNextSteps() {
		if (dealer.handValue() == 21 && player.handValue() == 21) {
			gameOver = true;
		}
		else if (dealer.handValue() == 21) {
			gameOver = true;		  //if dealer has natural, but player does not; gameOver
		}
		else if (player.handValue() == 21) {
			gameOver = true;		  //if dealer doesn't have natural BlackJack; gameOver
			dealer.displayHand(true); //display dealer's hand because his loop will be skipped
		}
//		Rank r0 = player.getHand().get(0).getRank();
//		Rank r1 = player.getHand().get(1).getRank();
//		if(
//			/*
//			 * add split hand here
//			 * 
//			 */
//		}
	}
	
	public void expandPlayerHand() {
		String answer = input.hitOrStay();
		while (answer.equals("h")) {
			player.updateHand(deck.dealCard());
			player.displayHand(split);
			if(player.handValue()< 21)
				answer = input.hitOrStay();
			else if(player.handValue()==21) {
				answer = "move on";
			}
			else {
				answer = "bust";
				this.gameOver = true;
				this.playerBust = true;
			}
		} 
	}
	
	public void expandDealerHand() {
		int total = dealer.handValue();
		while(total < 17) {
			dealer.updateHand(deck.dealCard());
			total = dealer.handValue();
		}
		dealer.displayHand(true);
		if(dealer.handValue() > 21) {
			this.dealerBust = true;
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
		else if (player.handValue() > dealer.handValue()) {
			System.out.println("Player wins!");
		}
		else {
			System.out.println("Dealer wins.");
		}

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
