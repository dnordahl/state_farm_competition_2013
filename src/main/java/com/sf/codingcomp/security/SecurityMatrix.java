package com.sf.codingcomp.security;

import java.util.ArrayList;
import java.util.List;

public class SecurityMatrix {

	private static final Role ADMIN = new Role("ADMIN");
	private static final Role DEV = new Role("DEV");
	private static final Role TEST = new Role("TEST");
	private static final Role READONLY = new Role("READONLY");
	
	private static List<Role> roles = new ArrayList<Role>();
	
	static {
		try {
			createRole(ADMIN);
			createRole(DEV);
			createRole(TEST);
			createRole(READONLY);
			grantAccess(READONLY, Action.READ);
			grantAccess(DEV, Action.READ, Day.WEEKEND());
			grantAccess(TEST, Action.READ);
			grantAccess(TEST, Action.CREATE, Day.WORK_WEEK());
			grantAccess(TEST, Action.UPDATE, Day.WORK_WEEK());
			for (Action action : Action.values()) {
				grantAccess(ADMIN, action, Day.values());
				grantAccess(DEV, action, Day.WORK_WEEK());
			}
		} catch (RoleAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	public static void createRole(Role role) throws RoleAlreadyExistsException {
		for (Role r : roles)
			if (r.getName().equalsIgnoreCase(role.getName()))
				throw new RoleAlreadyExistsException();
		for (int i = 0; i < roles.size(); i++){
			if (role.getName().toLowerCase().compareTo(roles.get(i).getName().toLowerCase()) < 0){
				roles.add(i, role);
				return;
			}
		}
		roles.add(role);
	}
	public static boolean hasAccess(Role role, Day day, Action action) {
		for (Role r : roles){
			if (role.getName().equalsIgnoreCase(r.getName()))
				return r.hasAccess(day, action);
		}
		return false;
	}

	public static List<Day> findDays(Role role, Action action) {
		List<Day> days = new ArrayList<Day>();
		for (Day day : Day.values()){
			if (role.hasAccess(day, action))
				days.add(day);
		}
		return days;
	}

	public static List<Action> findActions(Role role, Day day) {
		List<Action> actions = new ArrayList<Action>();
		for (Action action : Action.values()){
			if (role.hasAccess(day, action))
				actions.add(action);
		}
		return actions;
	}

	public static List<Role> findRoles(Day day, Action action) {
		List<Role> rolesToReturn = new ArrayList<Role>();
		for (Role role : roles){
			if (role.hasAccess(day, action))
				rolesToReturn.add(role);
		}
		return rolesToReturn;
	}

	public static void grantAccess(Role role, Action action, Day... days) {
		for (Day day : days)
			role.setAccess(day, action, true);
	}

	public static void revokeAccess(Role role, Action action, Day... days) {
		for (Day day : days)
			role.setAccess(day, action, false);
	}

}
