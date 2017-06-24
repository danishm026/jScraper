package com.arc.jScraper.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtil {
	private static final String NON_NUMERIC_CHARACTERS = "[^0-9]+";
	private static final String WHITESPACE = "\\s+";
	private static final String SPACE = " ";

	public static String sanitize(String string) {
		string = string.trim();
		String words[] = string.split(WHITESPACE);
		for(int i=0; i<words.length; i++) {
			words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].substring(1).toLowerCase();
		}
		StringBuffer buffer = new StringBuffer();
		for(String word : words) {
			buffer.append(word + " ");
		}
		string = buffer.toString().trim();
		return string;
	}
	
	public static char getFirstLetterAsCaps(String string) {
		return Character.toUpperCase(string.trim().charAt(0));
	}

	public static List<Integer> getIntegersInString(String string) {
		string = string.replaceAll(NON_NUMERIC_CHARACTERS, SPACE);
		List<String> numbers = Arrays.asList(string.trim().split(WHITESPACE));
		List<Integer> integers = new ArrayList<>();
        for(String number : numbers) {
            integers.add(Integer.parseInt(number));
        }
        return integers;
	}
}