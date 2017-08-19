package com.meirapentermann.cards;

public enum Suit {
	  HEARTS("Hearts", 	'\u2661') 		//try also 2661 2665
	, SPADES("Spades", 	'\u2664') 		//try also 2664 2660
	, CLUBS("Clubs", 	'\u2667') 		//try also 2667 2663
	, DIAMONDS("Diamonds", '\u2662'); 	//try also 2662 2666
	
	public String displayText;
	public char displayChar;
	
	private Suit(String s, char c) {
		this.displayText = s;
		this.displayChar = c;
	}

	public String getDisplayText() {
		return displayText;
	}

	public char getDisplayChar() {
		return displayChar;
	}

}
