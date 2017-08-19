package com.meirapentermann.blackjack;

import java.util.Scanner;

import com.meirapentermann.cards.Card;
import com.meirapentermann.cards.Deck;
import com.meirapentermann.cards.Rank;
import com.meirapentermann.cards.Suit;

public class PlayBlackjack {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		UserInput input = new UserInput(keyboard);
		
		Card c = new Card(Suit.HEARTS, Rank.ACE);
		
		System.out.println(c);
		
		Deck d = new Deck();
		d.shuffleDeck();
		
		for(int i = 0; i<52; i++) {
			System.out.println(d.dealCard());
		}
		
		System.out.println(d.cardSLeft());
		System.out.println(d.getNumDealt());
		String temp = input.hitOrStand();
		
		keyboard.close();

	}

}
