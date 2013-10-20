package com.sf.codingcomp.security;

public enum Day {
	MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);
	
	public static Day[] WORK_WEEK() {
		return new Day[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY};
	}
	
	public static Day[] WEEKEND() {
		return new Day[]{SATURDAY, SUNDAY};
	}
	
	private final int value;
	private Day(int day){
		value = day;
	}
	public int getValue(){
		return value;
	}
}
