package com.vandenrobotics.functionfirst.model;

public class CycleData {
	
	public GridData[] gridData;
	public int goalsProgress;
	public int tcProgress;
	
	public CycleData(){
		gridData = new GridData[9];
		goalsProgress = 1;
		tcProgress = 1;
	}

}
