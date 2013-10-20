package com.sf.codingcomp.football;

import java.text.DecimalFormat;

public class RunningBack extends Player {

	public RunningBack(int touchdowns, int yardsGained, boolean active) {
		super(touchdowns, yardsGained, active);
	}

	@Override
	public double calculateTotalScore(boolean partialPointsAllowed) {
		double score = 0;
		if (!partialPointsAllowed){
			score = touchdowns * 6 + (int)(yardsGained/10);
		}
		else{
			score = touchdowns * 6 + (yardsGained/10.0);
		}
		DecimalFormat df = new DecimalFormat("#.00");
		return Double.valueOf(df.format(score));
	}

}
