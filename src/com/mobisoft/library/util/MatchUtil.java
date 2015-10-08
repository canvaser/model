package com.mobisoft.library.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchUtil {


	public boolean match(String regex, String str) {
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(str);
    	return matcher.matches();
    }
}
