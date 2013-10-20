package com.sf.codingcomp.security;

public enum Action {
	CREATE(0), READ(1), UPDATE(2), DELETE(3);
	private final int val;
	private Action(int value){
		val = value;
	}
	public int getValue(){
		return val;
	}
}
