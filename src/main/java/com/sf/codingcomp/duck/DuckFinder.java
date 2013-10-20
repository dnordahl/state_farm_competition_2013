package com.sf.codingcomp.duck;

import java.lang.reflect.Method;

public class DuckFinder {

	public static <T> boolean isADuck(T objectToCheck) {

		Method[] methods = objectToCheck.getClass().getMethods();
		
		// find looksLikeADuck()
		boolean looks = false;
		for (Method m : methods) {
			if ("looksLikeADuck".equals(m.getName())) {
				looks = true;
				break;
			}
		}
		
		
		boolean quacks = false;
		for (Method m : methods) {
			if ("quacksLikeADuck".equals(m.getName())) {
				quacks = true;
				break;
			}
		}
		
		return looks && quacks;
	}

}
