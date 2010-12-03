package com.waters.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String removeParentheses(String value) {
		Pattern p = Pattern.compile("\\([^()]*\\)");
		Matcher m = p.matcher(value);
		return m.replaceAll("");
	}

}
