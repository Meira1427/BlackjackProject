package com.meirapentermann.cards;

public class Card {
	private Suit suit;
	private Rank rank;
	
	public Card() {
	}
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append(suit.displayChar);
		sb.append(" ");
		sb.append(rank.displayText);
		sb.append(suit.displayText);
		sb.append(" ");
		sb.append(suit.displayChar);
		sb.append(" ]\n");
		return sb.toString();
	}

}
