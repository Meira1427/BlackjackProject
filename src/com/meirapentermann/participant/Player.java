package com.meirapentermann.participant;

import java.util.List;

import com.meirapentermann.blackjack.Hand;
import com.meirapentermann.cards.Card;

public class Player extends Participant {
	
	public Player(String name) {
		Hand hand = new Hand();
		Hand aSplit = new Hand();
		this.setHand(hand); //initialize a hand
		this.setSplit(aSplit);
		this.setName(name);
	}

	@Override
	public void displayHand(boolean bool) {
		List<Card> hand = getHand();
		if(bool) { // a true for player means hand is split
			List<Card> aSplit = getSplit();
			System.out.print("* * * * * * * * * * * * * * * * *\t");
			System.out.println("* * * * * * * * * * * * * * * * *\n");
			System.out.println("\t" + "Split\t\t\t" + this.getName() + "'s Hand\t\tSplit");
			int count = Math.max(hand.size(), aSplit.size());
			for(int i = 0; i < count; i++) {
				if(!(i >=hand.size())) {
					System.out.print(hand.get(i));
				}
				System.out.print("\t\t\t\t\t");
				if(!(i >=aSplit.size())) {
					System.out.print(aSplit.get(i));
				}
				System.out.println();
			}
			System.out.print("Total Value: " + this.handValue());
			System.out.println("\t\t\t\tTotal Value: " + this.splitHandValue());
			System.out.print("\n* * * * * * * * * * * * * * * * *\t");
			System.out.println("* * * * * * * * * * * * * * * * *\n");
			
		}
		else { // a false is a single hand
			System.out.println("* * * * * * * * * * * * * * * * *\n");
			System.out.println("\t" + this.getName() + "'s Hand\n");
			for(Card h : hand) {
				System.out.println(h);
			}
			System.out.println("Total Value: " + this.handValue());
			System.out.println("\n* * * * * * * * * * * * * * * * *\n");
		}
	}

	





}
