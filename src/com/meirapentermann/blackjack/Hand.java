package com.meirapentermann.blackjack;

import java.util.List;

import com.meirapentermann.cards.Card;

public class Hand {
	private List<Card> hand;
	
	public Hand() {
	}

	public Hand(List<Card> hand) {
		this();
		this.hand = hand;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

}
