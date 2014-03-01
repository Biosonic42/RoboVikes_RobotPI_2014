package com.vandenrobotics.functionfirst.model;

public class GridData {
	public boolean passed;
	public int where;
	
	public GridData(){
		passed = false;
		where = -1;
	}
	
	@Override
	public String toString(){
		int tempPass = passed? 1 : 0;
		
		return tempPass+","+where;
	}
}
