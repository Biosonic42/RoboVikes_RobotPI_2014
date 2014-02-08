package com.vandenrobotics.functionfirst.model;

public class InitData {
	public int teamNumber;
	public int matchNumber;
	public int allianceColor;
	
	public InitData(){
		teamNumber = 0;
		matchNumber = 0;
		allianceColor = 0;
	}
	
	@Override
	public String toString(){
		return matchNumber+","+teamNumber+","+allianceColor;
	}
}
