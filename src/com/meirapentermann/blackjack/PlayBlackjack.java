package com.meirapentermann.blackjack;

import java.util.Scanner;

public class PlayBlackjack {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		BlackjackGame game = new BlackjackGame(keyboard);
		game.fullGame();
		keyboard.close();
	}

}
