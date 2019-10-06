package com.clean.strike;

import java.util.Scanner;

public class CleanStrike {
	public static void main(String[] args) {
		int rounds = 0;
		boolean isGameOn = true;
		CleanStrike cleanStrike = new CleanStrike();
		System.out.println("Welcome to Clean Strike Game\r\n It is a 2 Player Game");
		Player playerOne = new Player();
		playerOne.setName("Palyer One");
		Player playerTwo = new Player();
		playerTwo.setName("Player Two");
		CarromBoard carromBoard = new CarromBoard();
		while (isGameOn) {
			Player currentPlayer = cleanStrike.checkPlayer(rounds, playerOne, playerTwo, true);
			Player oppponentPlayer = cleanStrike.checkPlayer(rounds, playerOne, playerTwo, false);
			System.out.println(currentPlayer.getName() + "\n");
			System.out.println(" Choose an outcome from the list below\r\n" + "1. Strike\r\n" + "2. Multi Strike\r\n"
					+ "3. Red strike\r\n" + "4. Striker strike\r\n" + "5. Defunct coin\r\n" + "6. None");
			int playerInput = new Scanner(System.in).nextInt();
			setPlayerCount(Constants.getByCode(playerInput), currentPlayer, carromBoard);
			isGameOn = checkGameStillOn(currentPlayer, oppponentPlayer, carromBoard);
			if (!currentPlayer.isRedStriked)
				rounds++;
		}
		if (playerOne.isDraw)
			System.out.println("The Match Ended in Draw");
		else if (playerOne.isWin)
			System.out.println("Player One wins " + playerOne.score + " > " + playerTwo.score);
		else
			System.out.println("Player Two wins " + playerTwo.score + " > " + playerOne.score);
	}

	private static void setPlayerCount(Constants palyerStrike, Player currentPlayer, CarromBoard carromBoard) {
		switch (palyerStrike) {
		case STRIKE:
			if (currentPlayer.isRedStriked) {
				currentPlayer.score = currentPlayer.getScore() + 4;
				carromBoard.coins = carromBoard.getCoins() - 2;
				currentPlayer.isRedStriked = false;
			} else {
				currentPlayer.score++;
				carromBoard.coins--;
			}
			break;
		case MULTISTRIKE:
			if (currentPlayer.isRedStriked) {
				currentPlayer.score = currentPlayer.getScore() + 5;
				carromBoard.coins = carromBoard.getCoins() - 3;
				currentPlayer.isRedStriked = false;
			} else {
				carromBoard.coins = carromBoard.getCoins() - 2;
				currentPlayer.score = currentPlayer.getScore() + 2;
			}
			break;
		case RED_STRIKE:
			if (currentPlayer.isRedStriked) {
				System.out.println("Already red potted, choose another strike");
			}
			else {
				currentPlayer.isRedStriked = true;
				System.out.println("Strike another coin to get red coin");
			}
			break;
		case STRIKER_STRIKE:
			currentPlayer.score--;
			currentPlayer.isRedStriked = false;
			break;
		case DEFUNCT_COIN:
			currentPlayer.isRedStriked = false;
			currentPlayer.score = currentPlayer.getScore() - 2;
			carromBoard.coins--;
			currentPlayer.foul++;
			if (currentPlayer.getFoul() == 3) {
				currentPlayer.score--;
				currentPlayer.foul = 0;
			}
			break;
		case EMPTY:
			currentPlayer.isRedStriked = false;
			currentPlayer.notPocket++;
			if (currentPlayer.getNotPocket() == 3) {
				currentPlayer.score--;
				currentPlayer.notPocket = 0;
			}
			break;
		default:
			System.out.println("Wrong Input");
			break;
		}

	}

	private Player checkPlayer(int rounds, Player playerOne, Player playerTwo, boolean isPlayer) {
		if (rounds % 2 == 0)
			return isPlayer ? playerOne : playerTwo;
		else
			return isPlayer ? playerTwo : playerOne;
	}

	public static boolean checkGameStillOn(Player currentPlayer, Player opponenPlayer, CarromBoard carromBoard) {
		boolean returnValue = true;
		if (currentPlayer.getScore() >= 5 && currentPlayer.getScore() == opponenPlayer.getScore() + 3) {
			currentPlayer.setWin(true);
			returnValue = false;
		}

		else if (carromBoard.getCoins() <= 0) {
			if (currentPlayer.getScore() == opponenPlayer.getScore() + 3) {
				currentPlayer.setWin(true);
				returnValue = false;
			} else {
				currentPlayer.isDraw = true;
				opponenPlayer.isDraw = true;
				return false;
			}
		}
		return returnValue;
	}

}