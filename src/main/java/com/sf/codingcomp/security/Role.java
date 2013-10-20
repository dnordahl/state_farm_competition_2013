package com.sf.codingcomp.security;

public class Role {

	private String name;
	private boolean[][] actionaccess;

	public Role(String name) {
		this.name = name;
		this.actionaccess = new boolean[7][4];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAccess(Day day, Action action, boolean access){
		actionaccess[day.getValue()][action.getValue()] = access;
	}
	
	public boolean hasAccess(Day day, Action action){
		return actionaccess[day.getValue()][action.getValue()];
	}
}
