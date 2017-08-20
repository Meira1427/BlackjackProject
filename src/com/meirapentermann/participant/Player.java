package com.meirapentermann.participant;

import java.util.List;

import com.meirapentermann.blackjack.Hand;
import com.meirapentermann.cards.Card;

public class Player extends Participant {
	
	public Player(String name) {
		Hand hand = new Hand();
		this.setHand(hand);
		this.setName(name);
	}

	@Override
	public void displayHand(boolean full) {
		List<Card> hand = getHand();
		System.out.println("* * * * * * * * * * * * * * * * *\n");
		System.out.println("\t" + this.getName() + "'s Hand\n");
		for(Card h : hand) {
			System.out.println(h);
		}
		System.out.println("Total Value: " + this.handValue());
		System.out.println("\n* * * * * * * * * * * * * * * * *\n");
	}

	





}
