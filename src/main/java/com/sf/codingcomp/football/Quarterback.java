package com.sf.codingcomp.football;

import java.text.DecimalFormat;

public class Quarterback extends Player {

	public Quarterback(int touchdowns, int yardsGained, boolean active) {
		super(touchdowns, yardsGained, active);
	}

	@Override
	public double calculateTotalScore(boolean partialPointsAllowed) {
		double score = 0;
		if (!partialPointsAllowed){
			score = touchdowns * 5 + (int)(yardsGained/25);
		}
		else{
			score = touchdowns * 5 + (yardsGained/25.0);
		}
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(score));
	}

}
