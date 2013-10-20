package com.sf.codingcomp.velociraptor;

public class Velociraptor {

	private static final int TOP_SPEED = 40; // in miles per hour

	public int catchPrey(Prey prey, int startingSpeed) {
		int count = 0;
		double subtraction = 0;
		if (prey.getTopSpeed() > TOP_SPEED)
			return -1;
		for (int i = startingSpeed; i<= TOP_SPEED; i++){
			if (prey.getCurrentLead() <= 0){
				System.out.println(count);
				return count;
			}
			subtraction = (((prey.getTopSpeed()*(88.0/60))-(i*(88.0/60))));
			System.out.println(subtraction);
			prey.setCurrentLead(prey.getCurrentLead() + subtraction);
			count++;
			System.out.println("count: " +count + " " + prey.getCurrentLead());
		}
		for(int i = TOP_SPEED-1; i>=0; i--){
			if (prey.getCurrentLead() <= 0){
				System.out.println(count);
				return count;
			}
			count++;
			subtraction = (((prey.getTopSpeed()*(88.0/60))-(i*(88.0/60))));
			System.out.println(subtraction);
			prey.setCurrentLead(prey.getCurrentLead() + subtraction);
			System.out.println("count: " +count + " " + prey.getCurrentLead());
		}
		return -1;
	}
}
