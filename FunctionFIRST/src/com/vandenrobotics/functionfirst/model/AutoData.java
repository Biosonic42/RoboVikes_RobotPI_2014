package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AutoData 
		implements Parcelable {
	public boolean hadAuto;
	public boolean mobilityBonus;
	public boolean goalieZone;
	
	public int highScore;
	public int lowScore;
	public int hotScore;
	
	public AutoData(){
		hadAuto = false;
		mobilityBonus = false;
		goalieZone = false;
		
		highScore = 0;
		lowScore = 0;
		hotScore = 0;
	}
	
	@Override
	public String toString(){
		int tempAuto = hadAuto? 1 : 0;
		int tempMobil = mobilityBonus? 1 : 0;
		int tempGoalie = goalieZone? 1 : 0;
		
		return tempAuto +"," +tempMobil+","+tempGoalie+","
			  +highScore+"," +lowScore +","+hotScore;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
