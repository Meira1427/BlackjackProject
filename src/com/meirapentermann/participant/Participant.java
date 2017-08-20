package com.meirapentermann.participant;

import java.util.List;

import com.meirapentermann.blackjack.Hand;
import com.meirapentermann.cards.Card;

public abstract class Participant {
	private String name;
	private Hand hand;
	
	public abstract void displayHand(boolean full);
	
	public void updateHand(Card c) {
		this.hand.addCard(c);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() {
		return hand.getHand();
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	public int handValue() {
		return this.hand.handValue();
	}

}
