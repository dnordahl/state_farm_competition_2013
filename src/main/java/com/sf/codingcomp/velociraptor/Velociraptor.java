package com.sf.codingcomp.velociraptor;

public class Velociraptor {

	private static final int TOP_SPEED = 40; // in miles per hour

	public int catchPrey(Prey prey, int startingSpeed) {
		int count = 0;
		double subtraction = 0;
		if (prey.getTopSpeed() > TOP_SPEED)
			return -1;
		for (int i = startingSpeed; i<= TOP_SPEED; i++){
			if (prey.getCurrentLead() <= 0)
				return count;
			subtraction = (((prey.getTopSpeed()*(88.0/60))-(i*(88.0/60))));
			prey.setCurrentLead(prey.getCurrentLead() + subtraction);
			count++;
		}
		for(int i = TOP_SPEED-1; i>=0; i--){
			if (prey.getCurrentLead() <= 0)
				return count;
			count++;
			subtraction = (((prey.getTopSpeed()*(88.0/60))-(i*(88.0/60))));
			prey.setCurrentLead(prey.getCurrentLead() + subtraction);
		}
		return -1;
	}
}
