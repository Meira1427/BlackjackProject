package com.meirapentermann.blackjack;

import java.util.Scanner;

import com.meirapentermann.cards.*;
import com.meirapentermann.participant.*;


public class PlayBlackjack {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		UserInput input = new UserInput(keyboard);
		
		Card c = new Card(Suit.HEARTS, Rank.ACE);
		
		System.out.println(c);
		
		Deck d = new Deck();
		d.shuffleDeck();
		
		System.out.print("[ " + '\u2660' + "   " + '\u2663' + "   ");
		System.out.print('\u2665' + "   " + '\u2666' + "   " + '\u2660' + "   ");
		System.out.println('\u2663' + "   " + '\u2665' + " ]");
		
		keyboard.close();

	}

}
