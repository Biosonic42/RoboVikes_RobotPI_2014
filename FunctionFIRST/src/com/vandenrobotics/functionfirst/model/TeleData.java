package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TeleData
		implements Parcelable {
	public int highScore;
	public int highAttempt;
	public int lowScore;
	public int lowAttempt;
	
	public int trussScore;
	public int catchScore;
	public int assistScore;
	
	public TeleData(){
		highScore = 0;
		highAttempt = 0;
		lowScore = 0;
		lowAttempt = 0;
		
		trussScore = 0;
		catchScore = 0;
		assistScore = 0;
	}
	
	@Override
	public String toString(){
		return highScore   +","+highAttempt+","+lowScore   +","+lowAttempt+","
			  +trussScore  +","+catchScore +","+assistScore;
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
