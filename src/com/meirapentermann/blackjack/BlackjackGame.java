package com.meirapentermann.blackjack;

import com.meirapentermann.participant.*;

import java.util.Scanner;

import com.meirapentermann.cards.*;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private UserInput input;
	private boolean gameOver;
	private boolean playerDisplay;
	
	public BlackjackGame (Scanner keyboard) {
		player = new Player("Player");
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffleDeck();
		input = new UserInput(keyboard);
		gameOver = false;
		playerDisplay = false;
	}
	
	public void fullGame() {
		initialSetUp();
		determineNextSteps();
		if(!this.gameOver) {
			expandPlayerHand();
			expandDealerHand();
			calculateWin();
		}
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
		dealer.displayHand(false);
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
		while (answer == "h") {
			player.updateHand(deck.dealCard());
			player.displayHand(playerDisplay);
			answer = input.hitOrStay();
		}
	}
	
	public void expandDealerHand() {
		int total = dealer.handValue();
		while(total < 17) {
			dealer.updateHand(deck.dealCard());
			total = dealer.handValue();
		}
		dealer.displayHand(true);
	}
	
	public void calculateWin() {
		boolean bustPlayer = false;
		boolean bustDealer = false;
		if(player.handValue() > 21) {
			bustPlayer = true;
		}
		if(dealer.handValue() > 21) {
			bustDealer = true;
		}
		if(bustPlayer && bustDealer) {
			System.out.println("Both Player and Dealer bust!");
		}
		else if (bustPlayer) {
			System.out.println("Player busted. Dealer wins!");
		}
		else if (bustDealer) {
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
