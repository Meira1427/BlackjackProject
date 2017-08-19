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
		StringBuilder sb = new StringBuilder("");
		sb.append("[ ");
		sb.append(printChars(rank));
		sb.append("  ");
		sb.append(rank.displayText);
		sb.append(suit.displayText);
		sb.append("  ");
		sb.append(printChars(rank));
		sb.append(" ]\n");
		return sb.toString();
	}
	
	public String printChars(Rank rank) {
		StringBuilder sb = new StringBuilder("");
		switch(rank) {
		case ACE:
			sb.append(suit.displayChar);
			sb.append(" A ");
			sb.append(suit.displayChar);
			break;
		case JACK:
			sb.append(suit.displayChar);
			sb.append(" J ");
			sb.append(suit.displayChar);
			break;
		case QUEEN:
			sb.append(suit.displayChar);
			sb.append(" Q ");
			sb.append(suit.displayChar);
			break;
		case KING:
			sb.append(suit.displayChar);
			sb.append(" K ");
			sb.append(suit.displayChar);
			break;
		default:
			for(int i = 0; i< rank.value; i++) {
				sb.append(suit.displayChar);
			}
		}
		return sb.toString();
	}
}
