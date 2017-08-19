package com.meirapentermann.cards;

public enum Rank {
	  ACE("Ace of ", 11)
	, TWO("Two of ", 2)
	, THREE("Three of ", 3)
	, FOUR("Four of ", 4)
	, FIVE("Five of ", 5)
	, SIX("Six of ", 6)
	, SEVEN("Seven of ", 7)
	, EIGHT("Eight of ", 8)
	, NINE("Nine of ", 9)
	, TEN("Ten of ", 10)
	, JACK("Jack of ", 10)
	, QUEEN("Queen of ", 10)
	, KING("King of ", 10);
	
	public String displayText;
	public int value;
	
	private Rank (String s, int v) {
		this.displayText = s;
		this.value = v;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
