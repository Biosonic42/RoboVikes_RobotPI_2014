package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TeleData
		implements Parcelable {
	
	public int goalStatus;
	public int TCStatus;
	
	public TeleData(){
		goalStatus = 1;
		TCStatus = 1;
	}
	
	@Override
	public String toString(){
		return goalStatus + "," + TCStatus;
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
