package org.py.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static List<String> findMatches(String regexp, String input) {
		List<String> matches = new ArrayList<String>();
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find())
			matches.add(matcher.group());

		return matches;
	}
}