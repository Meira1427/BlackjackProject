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
	private boolean playerDisplay;
	
	public BlackjackGame (Scanner keyboard) {
		player = new Player("Player");
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffleDeck();
		input = new UserInput(keyboard);
		gameOver = false;
		playerBust = false;
		dealerBust = false;
		playerDisplay = false;
	}
	
	public void fullGame() {
		initialSetUp();
		determineNextSteps();
		if(!this.gameOver) {
			expandPlayerHand();
		}
		if(!this.gameOver) {
			expandDealerHand();
		}
		calculateWin();
	}
	
	public void initialSetUp() {
		Card c1, c2;
		for(int i = 0; i< 2; i++) {
			c1 = deck.dealCard();
			player.updateHand(c1);
			c2 = deck.dealCard();
			dealer.updateHand(c2);
		}
		player.displayHand(playerDisplay);
		dealer.displayHand(false); // false means not full hand is shown yet
	}

	public void determineNextSteps() {
		if (player.handValue() == 21) {
			System.out.println("Player wins!");
			this.gameOver = true;
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
			player.displayHand(playerDisplay);
			if(player.handValue()< 21)
				answer = input.hitOrStay();
			else if(player.handValue()==21) {
				System.out.println("Player reaches 21. . .");
				answer = "move on";
				this.gameOver = true;
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
	
	public void calculateWin() {
		if(player.handValue() == 21) {
			System.out.println("Player wins!");
			System.out.println("Is this true even if we don't see dealer?");
		}
		else if(this.playerBust && this.dealerBust) {
			System.out.println("Both Player and Dealer bust!");
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

}
