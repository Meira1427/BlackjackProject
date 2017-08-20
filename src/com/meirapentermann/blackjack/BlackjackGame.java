package com.meirapentermann.blackjack;

import com.meirapentermann.participant.*;
import com.meirapentermann.cards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
	private Player player;
	private Dealer dealer;
	private Deck deck;
	private UserInput input;
	private boolean gameOver;
	private boolean playerBust;
	private boolean dealerBust;
	private boolean split;
	String playAgain;
	
	/*
	 * Constructor requires a keyboard to set u the UserInput class
	 * Then everything is initialized.
	 */
	public BlackjackGame (Scanner keyboard) {
		player = new Player("Player");
		dealer = new Dealer();
		deck = new Deck();
		deck.shuffleDeck();
		input = new UserInput(keyboard);
		gameOver = false;
		playerBust = false;
		dealerBust = false;
		split = false;
		playAgain = "y";
	}
	
	/*
	 * full game combines all of the game logic methods
	 * watches for early game over
	 * loops for player to playAgain
	 */
	public void fullGame() {
		while(playAgain.equals("y")) {
			System.out.println();
			initialSetUp();
			determineNextSteps();
			if(!this.gameOver) {
				expandPlayerHand();
			}
			if(!this.gameOver) {
				expandDealerHand();
				dealer.displayHand(true);
			}
			calculateWin();
			System.out.println();
			tearDown();
			this.playAgain = input.yesOrNo();
		}
	}
	
	/*
	 * initial set-up deals 2 cards each, starting with player
	 * then it displays hands, full hand for player, half hand for dealer
	 */
	public void initialSetUp() {
		Card c1, c2;
		for(int i = 0; i< 2; i++) {
			c1 = deck.dealCard();
			player.updateHand(c1);
			//System.out.println("1. deck has " + deck.cardSLeft() + " cards left");
			c2 = deck.dealCard();
			dealer.updateHand(c2);
			//System.out.println("2. deck has " + deck.cardSLeft() + " cards left");
		}
		player.displayHand(split); // starts out as false for split; display normally
		if (dealer.handValue() == 21 || player.handValue() == 21) {
			dealer.displayHand(true); //dealer has natural BlackJack; print full hand
		}
		else {
			dealer.displayHand(false); // false means not full hand is shown yet
		}
	}
	
	/*
	 * to watch for instant win and possible split
	 */
	public void determineNextSteps() {
		if (dealer.handValue() == 21 || player.handValue() == 21) {
			gameOver = true;
		}
//		Rank r0 = player.getHand().get(0).getRank();
//		Rank r1 = player.getHand().get(1).getRank();
//		if(r0 ==
//			/*
//			 * add split hand here
//			 * 
//			 */
//		}
	}
	
	public int acesInHandCount(List<Card> cards) {
		int count = 0;
		for(Card card: cards) {
			if (card.getRank() == Rank.ACE) {
				count++;
			}
		}
		return count;
	}
	
	/*
	 * This may not be useful. If I reach in and change value of Rank,
	 * it changes all Aces in deck
	 */
	public List<Integer> acesInHandLocation(List<Card> cards) {
		if (acesInHandCount(cards)==0) {
			return null;
		}
		else {
			List<Integer> ints = new ArrayList<>();
			for(int i = 0; i<cards.size(); i++) {
				if (cards.get(i).getRank()==Rank.ACE) {
					ints.add(i);
				}
			}
			return ints;
		}
	}
	
	public void expandPlayerHand() {
		String answer = input.hitOrStay();
		while (answer.equals("h")) {
			player.updateHand(deck.dealCard());
			player.displayHand(split);
			if(player.handValue() < 21)
				answer = input.hitOrStay();
			else if(player.handValue()==21) {
				answer = "move on";
			}
			else if(acesInHandCount(player.getHand()) > 0) {
				List<Integer> list = acesInHandLocation(player.getHand());
				int count = list.size();
				while(( player.handValue() > 21) && count > 0 ) {
					player.getHand().get(list.get(count-1)).setValue(1);
					count--;
				}
				System.out.println("Hand with Aces Adjusted");
				player.displayHand(split);
				if(player.handValue()<21) {
					answer = input.hitOrStay();
				}
				else {
					answer = "bust";
					this.gameOver = true;
					this.playerBust = true;
				}
			}
			else {
				answer = "bust";
				this.gameOver = true;
				this.playerBust = true;
			}
		} 
	}
	
	public void expandDealerHand() {
		int total = dealer.handValue();
		while(total < 17) {
			dealer.updateHand(deck.dealCard());
			total = dealer.handValue();
		}
		if( (dealer.handValue()> 21) && (acesInHandCount(dealer.getHand()) > 0)) {
			List<Integer> list = acesInHandLocation(dealer.getHand());
			int count = list.size();
			while(( dealer.handValue() > 21) && count > 0 ) {
				dealer.getHand().get(list.get(count-1)).setValue(1);
				count--;
			}
		}
		if(dealer.handValue() < 17) {
			expandDealerHand();
		}
		if(dealer.handValue() > 21) {
			this.dealerBust = true;
		}
	}
	
	/*
	 * Starts with most unlikely case of a natural BlackJack for both dealer & player
	 * Moves on in order of elimination of conditions of cards
	 */
	public void calculateWin() {
		if(player.handValue() == 21 && player.getHand().size()==2 
		 & dealer.handValue() == 21 & dealer.getHand().size()==2) {
			System.out.println("Natural BlackJack! Both Player & Dealer. It's a Tie!");
		}
		else if (player.handValue() == 21 && player.getHand().size()==2) {
			System.out.println("Natural BlackJack! Player wins!");
		}
		else if (dealer.handValue() == 21 && dealer.getHand().size()==2) {
			System.out.println("Natural BlackJack! Dealer wins!");
		}
		else if (player.handValue() == 21 && dealer.handValue() == 21) {
			System.out.println("BlackJack! Both Player & Dealer. It's a Tie!");
		}
		else if(this.playerBust && this.dealerBust) {
			System.out.println("Both Player and Dealer bust!");
			//I think this is unreachable code. Dealer will not deal if player busts
		}
		else if (this.playerBust) {
			System.out.println("Player busted. Dealer wins!");
		}
		else if (this.dealerBust) {
			System.out.println("Dealer busted. Player wins!");
		}
		else if (player.handValue() == dealer.handValue()) {
			System.out.println("It's a Tie!");
		}
		else if (player.handValue() > dealer.handValue()) {
			System.out.println("Player wins!");
		}
		else {
			System.out.println("Dealer wins.");
		}
	}
		
	public void tearDown()	{
		this.deck = new Deck();
		this.deck.shuffleDeck();
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();
		this.getPlayer().setHand(hand1);
		this.getDealer().setHand(hand2);
		gameOver = false;
		playerBust = false;
		dealerBust = false;
		split = false;
	}

	
	/*
	 * Getters & Setters
	 */

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public UserInput getInput() {
		return input;
	}

	public void setInput(UserInput input) {
		this.input = input;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isPlayerBust() {
		return playerBust;
	}

	public void setPlayerBust(boolean playerBust) {
		this.playerBust = playerBust;
	}

	public boolean isDealerBust() {
		return dealerBust;
	}

	public void setDealerBust(boolean dealerBust) {
		this.dealerBust = dealerBust;
	}

	public boolean isSplit() {
		return split;
	}

	public void setSplit(boolean split) {
		this.split = split;
	}
}
