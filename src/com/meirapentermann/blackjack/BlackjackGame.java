package com.meirapentermann.blackjack;

import com.meirapentermann.participant.*;

import java.util.Scanner;

import com.meirapentermann.cards.*;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private UserInput input;
	private Scanner keyboard;
	
	public BlackjackGame (Scanner keyboard) {
		player = new Player("Player");
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffleDeck();
		input = new UserInput(keyboard);
	}
	
	public void initialSetUp() {
		Card c1, c2;
		for(int i = 0; i< 2; i++) {
			c1 = deck.dealCard();
			player.updateHand(c1);
			c2 = deck.dealCard();
			dealer.updateHand(c2);
		}
		player.displayHand(true);
		dealer.displayHand(false);
	}
	
	public void expandPlayerHand() {
		String answer = input.hitOrStand();
		while (answer == "h") {
			player.updateHand(deck.dealCard());
			player.displayHand(true);
			answer = input.hitOrStand();
		}
	}
	
	public void explandDealerHand() {
		int total = dealer.handValue();
		while(total < 17) {
			dealer.updateHand(deck.dealCard());
			total = dealer.handValue();
		}
		dealer.displayHand(true);
	}
	
	public Participant calculateWin() {
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
			return dealer;
		}
		else if (bustPlayer) {
			System.out.println("Player busted. Dealer wins!");
			return dealer;
		}
		else if (bustDealer) {
			System.out.println("Dealer busted. Player wins!");
			return player;
		}
		else if (player.handValue() > dealer.handValue()) {
			System.out.println("Player wins!");
			return player;
		}
		else {
			System.out.println("Dealer wins :(");
			return dealer;
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
