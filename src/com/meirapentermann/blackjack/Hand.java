package com.meirapentermann.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.meirapentermann.cards.Card;

public class Hand {
	private List<Card> hand;
	
	public Hand() {
		this.hand = new ArrayList<>();
	}

	public Hand(List<Card> hand) {
		this();
		this.hand = hand;
	}
	
	public void addCard(Card c) {
		this.hand.add(c);
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("* * * * * * * * * * * * * * * * * * * * * * *\n\n");
		List<Card> cards = this.getHand();
		for(Card card : cards) {
			sb.append(card);
			sb.append("\n");
		}
		sb.append("* * * * * * * * * * * * * * * * * * * * * * *\n");
		return sb.toString();
	}
	
	

}
