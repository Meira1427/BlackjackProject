package com.meirapentermann.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private int[] values = new int[13]; //optionally change values of enums;
	private List<Card> deck = new ArrayList<>();
	private int numDealt;
	
	public Deck () {
		this.numDealt = 0;
		this.deck = createDeck();
	}
	
	public Deck(int[] values) {
		//default values of rank are for blackjack
		//use this constructor if you need to pass in new values
		this();
		this.values = values;
	}
	
	/*
	 * Loops through both enums to produce a new deck
	 */
	private List<Card> createDeck() {
		List<Card> temp = new ArrayList<>();
		Card c;
		Suit[] suits = {Suit.HEARTS, Suit.SPADES, 
						Suit.CLUBS, Suit.DIAMONDS  };

		Rank[] ranks = {Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, 
						Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT,
						Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN,
						Rank.KING };
						
		for(Rank rank : ranks) {
			for(Suit suit : suits) {
				c = new Card(suit, rank);
				temp.add(c);
			}
		}
		return temp;
	}
	
	/*
	 * Shuffles deck
	 */
	public void shuffleDeck() {
		Collections.shuffle(this.deck);
	}
	
	/*
	 * Verify that deck isn't empty
	 * Then deal one card and remove it from deck
	 * if deck is empty, return null
	 */	
	public Card dealCard() {
		if (cardSLeft() > 0) {
			Card c = deck.remove(0);
			this.numDealt++;
			return c;
		}
		else {
			return null;
		}
	}
	
	/*
	 * returns # cards left in deck
	 */
	public int cardSLeft() {
		return this.deck.size();
	}

	/*
	 * Getters and Setters
	 */
	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public int getNumDealt() {
		return numDealt;
	}

	public void setNumDealt(int numDealt) {
		this.numDealt = numDealt;
	}

	/*
	 * Basic toString() method
	 */
	@Override
	public String toString() {
		return "Deck [" + deck + "]";
	}
	
	
}
