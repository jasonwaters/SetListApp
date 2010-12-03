package com.waters.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String removeParentheses(String value) {
		Pattern p = Pattern.compile("\\([^()]*\\)");
		Matcher m = p.matcher(value);
		return m.replaceAll("");
	}

    public static String removeThe(String value) {
        Pattern p = Pattern.compile("^the", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		return m.replaceAll("");
    }

    public static String removeAnd(String value) {
        Pattern p = Pattern.compile("(and|&)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(value);
		return m.replaceAll("");
    }
}