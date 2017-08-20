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
	
	public void expandPlayerHand(Participant p) {
		String answer = input.hitOrStand();
		while (answer == "h") {
			
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
