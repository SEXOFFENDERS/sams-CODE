package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		boolean[][] board = new boolean[50][50];
		GameOfLife game = new GameOfLife(board);

		//set up a "c"
		game.setAlive(25, 25, true);
		game.setAlive(26, 25, true);
		game.setAlive(27, 25, true);
		game.setAlive(25, 26, true);
		game.setAlive(25, 27, true);
		game.setAlive(26, 27, true);
		game.setAlive(27, 27, true);
		System.out.println(game.isAlive(26, 24));
		game.print();
		for (int i = 1; i <= 175; i++) {
			game.calculateNextGeneration();
			System.out.println("generation:" + i);
			game.print();
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
			}
		}
	

	}

}
