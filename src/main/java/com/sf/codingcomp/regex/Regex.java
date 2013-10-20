package com.sf.codingcomp.regex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Regex {
	/**
	 * 10 numbers.
	 * 
	 * @param ssn
	 * @return
	 */
	public static boolean isFullPhoneNumber(String phone) {
		Pattern phoneRegex = Pattern.compile("\\d{3}(-| ){0,1}\\d{3}(-| ){0,1}\\d{4}");
		Matcher phoneMatch = phoneRegex.matcher(phone);
		return phoneMatch.matches();
	}

	/**
	 * It is an address if it contains all 3 elements: Address (e.g. street &
	 * number or PO box number) City & State Zip code (5 or 9)
	 * 
	 * @param address
	 * @return
	 */
	public static boolean isAmericanAddress(String address) {
		String street = "((\\d+ \\w+( \\w+)*( \\d+){0,1})|(PO Box \\d+))";
		String city = "(\\w+( \\w+)*)";
		String state = "[A-Z]{2}";
		String zip = " \\d{5}(\\-\\d{4}|\\d{4}){0,1}";
		
		String delimiter = ",{0,1} +";
		
		Pattern addressRegex = Pattern.compile(street + delimiter + city + delimiter + state + zip);
		Matcher addressMatch = addressRegex.matcher(address);
		return addressMatch.matches();
	}

	public static boolean isIPv4Address(String ip) {
		// pattern from http://www.regular-expressions.info/examples.html
		Pattern ipRegex = Pattern.compile("(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
		Matcher ipMatch = ipRegex.matcher(ip);
		return ipMatch.matches();
	}
}
