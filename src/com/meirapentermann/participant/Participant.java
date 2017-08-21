package com.meirapentermann.participant;

import java.util.List;

import com.meirapentermann.blackjack.Hand;
import com.meirapentermann.cards.Card;

public abstract class Participant {
	private String name;
	private Hand hand;
	private Hand split;
	
	public abstract void displayHand(boolean bool);
	
	/*
	 * need a way to reach in and add a card
	 */
	public void updateHand(Card c) {
		this.hand.addCard(c);
	}
	
	public void updateHandSplit(Card c) {
		this.split.addCard(c);
	}
	
	/*
	 * Getters & Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Card> getHand() { // Note: returning this in List<Card> format, so we can choose
		return hand.getHand();	  //   to loop through the List of Cards. More versatile
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}
	
	public int handValue() {			// for easier grab at Player & Dealer level
		return this.hand.handValue();
	}
	
	/*
	 * 2nd hand as a split
	 */

	public List<Card> getSplit() {
		return split.getHand();
	}

	public void setSplit(Hand split) {
		this.split = split;
	}
	
	public int splitHandValue() {
		return this.split.handValue();
	}
	
	

}
