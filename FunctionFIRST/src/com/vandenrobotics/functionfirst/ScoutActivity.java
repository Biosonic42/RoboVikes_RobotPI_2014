package com.vandenrobotics.functionfirst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.vandenrobotics.functionfirst.model.MatchData;
import com.vandenrobotics.functionfirst.model.TeamList;

public class ScoutActivity extends Activity {


	public static int deviceNumber = readDevice();
	public static int mCurMatch = readMatch();
	// eventually call readMatches to read the matchlist and create a TeamList map
	public static TeamList[] mTeamList = new TeamList[200]; //readMatches();
	public static MatchData[] mMatchResults = readData();
	public static int maxMatches = readMatches();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scout);
	}
	
	@Override
	protected void onResume(){
		mCurMatch = readMatch();
		Intent intent = new Intent(this, MatchActivity.class);
		intent.putExtra("matchNumber", mCurMatch);
		intent.putExtra("teamNumber",mTeamList[mCurMatch-1].teams[deviceNumber-1]);
		intent.putExtra("deviceNumber", deviceNumber);
		startActivity(intent);
		onDestroy();
	}
	
	public static Intent goToMatch(Context context, int curMatch, int newMatch, int dNum, MatchData mMatchData){
		
		// write old stuff
		mMatchResults[curMatch-1] = mMatchData;
		writeData();

		mCurMatch = (newMatch>0 && newMatch<=maxMatches)? newMatch : curMatch;
		writeMatch(mCurMatch);
		
		// setup new device and data
		deviceNumber = dNum;
		writeDevice(deviceNumber);
		mMatchResults = readData();	 // update data to ensure that device is the same or if it has changed to load new data
		writeMatch(mCurMatch);
		// start the new MatchActivity
		Intent intent = new Intent(context,MatchActivity.class);
		intent.putExtra("matchNumber", mCurMatch);
		intent.putExtra("teamNumber",mTeamList[mCurMatch-1].teams[deviceNumber-1]);
		intent.putExtra("deviceNumber",deviceNumber);
    	return intent;
	}
	
	public static MatchData[] readData(){
		MatchData[] mMD = new MatchData[200];
		
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath()+ "/ScoutData/device-" + deviceNumber);
		dir.mkdirs();
		System.out.println("DEVICE NUMBER: " + deviceNumber);
		
		File file = new File(dir,"data.txt");
		try{
			FileInputStream f = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(f));
			String line;
			while ((line = br.readLine())!=null){
				String[] data_text = line.split(",");
				int[] data = new int[data_text.length];
				
				try {
					for(int i = 0; i < data.length; i++){
						data[i] = Integer.parseInt(data_text[i]);
					}
					
					int match = data[0]-1;
					
					mMD[match] = new MatchData();
					
					// initData
					mMD[match].initData.matchNumber = data[0];
					mMD[match].initData.teamNumber = data[1];
					mMD[match].initData.allianceColor = data[2];
					
					// autoData
					mMD[match].autoData.hadAuto = (data[3]==1);
					mMD[match].autoData.mobilityBonus = (data[4]==1);
					mMD[match].autoData.goalieZone = (data[5]==1);
					mMD[match].autoData.highScore = data[6];
					mMD[match].autoData.lowScore = data[7];
					mMD[match].autoData.hotScore = data[8];
					
					// teleData
					mMD[match].teleData.highScore = data[9];
					mMD[match].teleData.highAttempt = data[10];
					mMD[match].teleData.lowScore = data[11];
					mMD[match].teleData.lowAttempt = data[12];
					mMD[match].teleData.trussScore = data[13];
					mMD[match].teleData.catchScore = data[14];
					mMD[match].teleData.assistScore = data[15];
					
					// postData
					mMD[match].postData.regFouls = data[16];
					mMD[match].postData.techFouls = data[17];
					mMD[match].postData.disabled = (data[18]==1);
					mMD[match].postData.broken = (data[19]==1);
					mMD[match].postData.yellowCard = (data[20]==1);
					mMD[match].postData.redCard = (data[21]==1);
					mMD[match].postData.defensive = (data[22]==1);
					
				} catch (NumberFormatException e){
					e.printStackTrace();
				}
			} 
			br.close();
			f.close();
		} catch (FileNotFoundException e){
				e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return mMD;
	}
	
	public static void writeData(){
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath()+ "/ScoutData/device-" + deviceNumber);
		dir.mkdirs();
		
		File file = new File(dir,"data.txt");
		try{
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			for(int i=0;
				i<mMatchResults.length; i++) {
					if(mMatchResults[i]!=null)
						pw.println(mMatchResults[i].toString()+"\n\r");
			}
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static int readMatches(){
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/ScoutData");
		dir.mkdirs();
		File file = new File(dir,"matches.txt");
		int i = 0;
		try{	
			FileInputStream f = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(f));
			String line;
			while ((line = br.readLine())!=null){
				String[] teams = line.split(",");
				mTeamList[i] = new TeamList();
				mTeamList[i].matchNumber = Integer.parseInt(teams[0]);
				mTeamList[i].teams[0] = Integer.parseInt(teams[1]);
				mTeamList[i].teams[1] = Integer.parseInt(teams[2]);
				mTeamList[i].teams[2] = Integer.parseInt(teams[3]);
				mTeamList[i].teams[3] = Integer.parseInt(teams[4]);
				mTeamList[i].teams[4] = Integer.parseInt(teams[5]);
				mTeamList[i].teams[5] = Integer.parseInt(teams[6]);
				i++;	
			}
			br.close();
			f.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static void writeDevice(int dNum){
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/ScoutData");
		dir.mkdirs();
		File file = new File(dir,"device.txt");
		
		try{
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println(dNum);
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static int readDevice(){
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/ScoutData");
		dir.mkdirs();
		File file = new File(dir,"device.txt");
		int dNum = 0;
		try{	
			FileInputStream f = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(f));
			dNum = Integer.parseInt(br.readLine());
			br.close();
			f.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch (NumberFormatException e){
			e.printStackTrace();
			dNum = 1;
		}
		return (dNum>0 && dNum<7)? dNum : 1;
	}
	
	public static void writeMatch(int match){
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/ScoutData/device-" + deviceNumber);
		dir.mkdirs();
		File file = new File(dir,"savedmatch.txt");
		
		try{
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println(match);
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public static int readMatch(){
		File root = Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/ScoutData/device-" + deviceNumber);
		dir.mkdirs();
		File file = new File(dir,"savedmatch.txt");
		int mNum = 0;
		try{	
			FileInputStream f = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(f));
			mNum = Integer.parseInt(br.readLine());
			br.close();
			f.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			mNum = 1;
		} catch (IOException e){
			e.printStackTrace();
			mNum = 1;
		} catch (NumberFormatException e){
			e.printStackTrace();
			mNum = 1;
		}
		return (mNum>0 && mNum<=maxMatches)? mNum : 1;
	}
}
