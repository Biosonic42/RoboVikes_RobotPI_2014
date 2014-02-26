package com.vandenrobotics.functionfirst.model;

public class CycleData {
	public int[] gridBoxScores;
	public int switchGoalsProgress;
	public int switchTCProgress;
	
	public CycleData(){
		gridBoxScores = new int[9];
		switchGoalsProgress = 1;
		switchTCProgress = 1;
		
	}
	
}
