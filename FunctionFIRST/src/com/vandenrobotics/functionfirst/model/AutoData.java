package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AutoData 
		implements Parcelable {
	public boolean hadAuto;
	public boolean mobilityBonus;
	public boolean goalieZone;
	public boolean[] highHot = new boolean[3];
	public boolean[] lowHot = new boolean[3];
	
	public int highScore;
	public int lowScore;
	
	public AutoData(){
		hadAuto = false;
		mobilityBonus = false;
		goalieZone = false;
		highHot[0] = false;
		highHot[1] = false;
		highHot[2] = false;
		lowHot[0] = false;
		lowHot[1] = false;
		lowHot[2] = false;
		
		highScore = 0;
		lowScore = 0;
	}
	
	@Override
	public String toString(){
		int tempAuto = hadAuto? 1 : 0;
		int tempMobil = mobilityBonus? 1 : 0;
		int tempGoalie = goalieZone? 1 : 0;
		int[] tempHH = new int[3];
		tempHH[0] = highHot[0]? 1 : 0;
		tempHH[1] = highHot[1]? 1 : 0;
		tempHH[2] = highHot[2]? 1 : 0;
		int[] tempLH = new int[3];
		tempLH[0] = lowHot[0]? 1 : 0;
		tempLH[1] = lowHot[1]? 1 : 0;
		tempLH[2] = lowHot[2]? 1 : 0;
		
		return tempAuto+","+tempMobil+","+tempGoalie+","+
		       highScore+","+tempHH[0]+","+tempHH[1]+","+tempHH[2]+","+
		       lowScore+","+tempLH[0]+","+tempLH[1]+","+tempLH[2];
	}
	
	public boolean fromString(String string){
		try{
			String[] dataString = string.split(",");
			int[] data = new int[dataString.length];
			
			try{
				for(int i = 0; i < data.length; i++)
					data[i] = Integer.parseInt(dataString[i]);
			} catch (NumberFormatException e){
				e.printStackTrace();
				return false;
			} catch (IndexOutOfBoundsException e){
				e.printStackTrace();
				return false;
			}
			
			hadAuto = (data[0]==1)? true : false;
			mobilityBonus = (data[1]==1)? true : false;
			goalieZone = (data[2]==1)? true : false;
			highScore = data[3];
			highHot[0] = (data[4]==1)? true : false;
			highHot[1] = (data[5]==1)? true : false;
			highHot[2] = (data[6]==1)? true : false;
			lowScore = data[7];
			lowHot[0] = (data[8]==1)? true : false;
			lowHot[1] = (data[9]==1)? true : false;
			lowHot[2] = (data[10]==1)? true : false;
			
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
		// only way it can get to this point is if there are no exceptions
		return true;
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
