package com.vandenrobotics.functionfirst.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class TeleData
		implements Parcelable {
	
	public ArrayList<CycleData> cycles;
	
	public TeleData(){
		cycles = new ArrayList<CycleData>();
		cycles.add(new CycleData());
	}
	
	@Override
	public String toString(){
		String returnVal = "{" + cycles.size() + ",";
		for(int i = 0; i<cycles.size(); i++){
			returnVal += "[";
			for(int j = 0; j<=8; j++){
				if(cycles.get(i)!=null)
					returnVal += cycles.get(i).gridData[j].toString() + ",";
			}
			returnVal += cycles.get(i).goalsProgress + ",";
			returnVal += cycles.get(i).tcProgress;
			returnVal += "],";
		}
		returnVal += "}";
		
		return returnVal;
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
