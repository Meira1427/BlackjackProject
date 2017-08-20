package com.meirapentermann.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.meirapentermann.cards.Card;

public class Hand {
	private List<Card> hand;
	
	/*
	 * Constructors
	 */
	public Hand() {
		this.hand = new ArrayList<>();
	}

	public Hand(List<Card> hand) {
		this();
		this.hand = hand;
	}
	
	/*
	 * Adding a card to a hand
	 */
	public void addCard(Card c) {
		this.hand.add(c);
	}
	
	/*
	 * return handSize
	 */
	
	public int handSize() {
		return getHand().size();
	}
	
	public int handValue() {
		List<Card> cards = this.getHand();
		int value = 0;
		for(Card card : cards) {
			value += card.getRank().value;
		}
		return value;
	}
	
	/*
	 * Getters & Setters
	 */
	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	/*
	 * Displaying hand 
	 * new line for every card
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		List<Card> cards = this.getHand();
		for(Card card : cards) {
			sb.append(card);
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
