package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PostData 
		implements Parcelable {
	public int regFouls;
	public int techFouls;
	
	public boolean disabled;
	public boolean broken;
	public boolean yellowCard;
	public boolean redCard;
	public boolean defensive;
	
	public PostData(){
		regFouls = 0;
		techFouls = 0;
		
		disabled = false;
		broken = false;
		yellowCard = false;
		redCard = false;
		defensive = false;
	}
	
	@Override
	public String toString(){
		int tempDis = disabled? 1 : 0;
		int tempBro = broken? 1 : 0;
		int tempYel = yellowCard? 1 : 0;
		int tempRed = redCard? 1 : 0;
		int tempDef = defensive? 1 : 0;
		
		return regFouls+","+techFouls+","
		      +tempDis +","+tempBro  +","
		      +tempYel +","+tempRed  +","
		      +tempDef;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}
}
