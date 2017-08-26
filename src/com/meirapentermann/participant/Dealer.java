package com.meirapentermann.participant;

import java.util.List;

import com.meirapentermann.blackjack.Hand;
import com.meirapentermann.cards.Card;

public class Dealer extends Participant {
	
	public Dealer () {
		this.setName("Dealer");
		Hand hand = new Hand(); //initialize a hand
		this.setHand(hand);
	}

	@Override
	public void displayHand(boolean bool) {
		List<Card> hand = getHandList();
		System.out.println("* * * * * * * * * * * * * * * * *\n");
		System.out.println("\t" + this.getName() + "'s Hand\n");
		if(bool) { // a true for dealer shows entire hand
			for(Card h : hand) {
				System.out.println(h);
			}
		}
		else { // a false means that first card is not visible
			System.out.print("[   " + '\u2660' + "   " + '\u2663' + "   "); // prints suits instead
			System.out.print('\u2665' + "   " + '\u2666' + "   " + '\u2660' + "   "); // more suits
			System.out.println('\u2663' + "   " + '\u2665' + "   " + '\u2666' + "   " + "]\n"); // more suits
			for(int i = 1; i<hand.size(); i++){
				System.out.println(hand.get(i)); //rest of hand; really only hand.get(1)
			}
		}
		if(bool) { // only show total when showing full hand at end
			System.out.println("Total Value: " + this.handValue());
		}
		System.out.println("\n* * * * * * * * * * * * * * * * *\n");
	}

}
