package com.clean.strike;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Constants {
	STRIKE(1, "STRIKE"), MULTISTRIKE(2, "MULTISTRIKE"), RED_STRIKE(3, "RED_STRIKE"),
	STRIKER_STRIKE(4, "STRIKER_STRIKE"), DEFUNCT_COIN(5, "DEFECT_COIN"), EMPTY(6, "EMPTY");

	private final int code;
	private final String text;

	private static final Map<Integer, Constants> STATUS_MAP = Arrays.stream(Constants.values())
			.collect(Collectors.toMap(acoount -> acoount.getCode(), Function.identity()));

	Constants(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public static Constants getByCode(int code) {
		return STATUS_MAP.get(code);
	}

}
