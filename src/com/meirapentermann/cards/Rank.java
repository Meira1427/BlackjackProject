package com.meirapentermann.cards;

public enum Rank {
	  ACE("Ace of ", "A", 11)
	, TWO("Two of ", "2", 2)
	, THREE("Three of ", "3", 3)
	, FOUR("Four of ", "4", 4)
	, FIVE("Five of ", "5", 5)
	, SIX("Six of ", "6", 6)
	, SEVEN("Seven of ", "7", 7)
	, EIGHT("Eight of ", "8", 8)
	, NINE("Nine of ", "9", 9)
	, TEN("Ten of ", "10", 10)
	, JACK("Jack of ", "J", 10)
	, QUEEN("Queen of ", "Q", 10)
	, KING("King of ", "K", 10);
	
	public String displayText;
	public String displayShort;
	public int value;
	
	private Rank (String s, String c, int v) {
		this.displayText = s;
		this.displayShort = c;
		this.value = v;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	public String getDisplayShort() {
		return displayShort;
	}

	public void setDisplayShort(String displayShort) {
		this.displayShort = displayShort;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
