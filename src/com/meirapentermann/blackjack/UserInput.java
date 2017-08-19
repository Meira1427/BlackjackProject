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
	public int returnCleanInt(String prompt) {
		System.out.print(prompt);
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
	public String hitOrStand() {
		String answer = "w";
		while(! (answer.equals("h") || answer.equals("s"))) {
			answer = returnUserString("Would you like to (h)it or (s)tand? ");
			answer = answer.toLowerCase();
		}
		return answer;
	}
	

}
