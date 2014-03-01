package com.vandenrobotics.functionfirst.model;

public class CycleData {
	
	public GridData[] gridData;
	public int goalsProgress;
	public int tcProgress;
	
	public CycleData(){
		gridData = new GridData[9];
		for (int i = 0; i < 9; i++)
			gridData[i] = new GridData();
		goalsProgress = 1;
		tcProgress = 1;
	}

}
