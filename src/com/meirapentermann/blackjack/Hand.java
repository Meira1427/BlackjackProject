package com.meirapentermann.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.meirapentermann.cards.Card;
import com.meirapentermann.cards.Rank;

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
	 * Remove card; only during split
	 */
	public Card removeCard() {
		Card c;
		if(hand.size() > 0) {
			c = this.hand.remove(0);
			return c;
		}
		else {
			return null;
		}
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
			value += card.getValue();
		}
		return value;
	}
	
	/*
	 * returns number of Aces in given hand
	 */
	public int acesInHandCount() {
		List<Card> cards = this.getHand();
		int count = 0;
		for(Card card: cards) {
			if (card.getRank() == Rank.ACE) {
				count++;
			}
		}
		return count;
	}
	
	/*
	 * Gives position of all Aces in hand
	 */
	public List<Integer> acesInHandLocation() {
		List<Card> cards = this.getHand();
		if (this.acesInHandCount()==0) {
			return null;
		}
		else {
			List<Integer> ints = new ArrayList<>();
			for(int i = 0; i<cards.size(); i++) {
				if (cards.get(i).getRank()==Rank.ACE) {
					ints.add(i);
				}
			}
			return ints;
		}
	}
	
	/*
	 * Returns true if manages to get hand less than or equal to 21
	 * return false if it doesn't work
	 */
	
	public void dealWithAces() {
		if(this.acesInHandCount() > 0) {
			List<Integer> list = this.acesInHandLocation();
			int count = list.size();
			while(( this.handValue() > 21) && count > 0 ) {
				this.getHand().get(list.get(count-1)).setValue(1);
				count--;
			}
		}
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
