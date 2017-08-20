package com.meirapentermann.blackjack;

import java.util.Scanner;

public class UserInput {
	private Scanner keyboard;
	
	/*
	 * Constructor requires a Scanner object
	 */
	public UserInput(Scanner sc) {
		this.keyboard = sc;
	}
	
	/*
	 * Returns an integer
	 */
	public int returnCleanInt(String prompt) { //saving this for future card games
		System.out.print(prompt);			   //may be required for requesting number of cards
		while(!this.keyboard.hasNextInt()) {
			System.out.print(prompt);
			this.keyboard.next();
		}
		return this.keyboard.nextInt();
	}
	
	/*
	 * Returns a String
	 */
	public String returnUserString(String prompt) {
		System.out.print(prompt);
		return this.keyboard.next();
	}
	
	/*
	 * Returns an "h" or a "s"
	 */
	public String hitOrStay() {
		String answer = "w";
		while(! (answer.equals("h") || answer.equals("s"))) {
			answer = returnUserString("Would you like to (h)it or (s)tay? ");
			answer = answer.toLowerCase();
		}
		return answer;
	}
	
	/*
	 * Returns a "y" or a "n"
	 */
	public String yesOrNo() {
		String answer = "w";
		while(! (answer.equals("y") || answer.equals("n"))) {
			answer = returnUserString("Would you like to play again? (y/n) ");
			answer = answer.toLowerCase();
		}
		return answer;
	}
	
	
	

}
