package com.sf.codingcomp.duck;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class DuckFinder {

	public static <T> boolean isADuck(T objectToCheck) {

		boolean quacks = false, looksIt = false;
		Method[] methods = objectToCheck.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (Modifier.isPublic(method.getModifiers())) {
				if (method.getName().equals("quacksLikeADuck")) quacks = true;
				if (method.getName().equals("looksLikeADuck"))	looksIt = true;
			}
		}

		return quacks && looksIt;
	}

}
