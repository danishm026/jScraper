package com.arc.jScraper.util;

public class StringUtil {
	public static String sanitize(String string) {
		string = string.trim();
		String words[] = string.split("\\s+");
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
}