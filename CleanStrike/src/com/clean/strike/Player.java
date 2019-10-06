package com.clean.strike;

import lombok.Data;

@Data
public class Player {
	String name;
	int score = 0;
	int foul = 0;
	int notPocket = 0;
	boolean isWin;
	boolean isRedStriked;
	boolean isDraw;
	boolean isTurn;
}
