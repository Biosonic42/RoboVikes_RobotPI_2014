package com.vandenrobotics.functionfirst.robotpi;

import java.util.ArrayList;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.TeleData;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TeleFragment extends Fragment {

	private Button buttonUndo;
	private Button buttonRedo;
	private TextView intakeTime;
	private Button buttonRecord;
	private Button buttonLowScoreDown;
	private Button buttonLowScoreUp;
	private EditText lowScore;
	private Button buttonTruss;
	private Button buttonCatch;
	private Button buttonGoal;
	private Button buttonGiveAssist;
	private Button buttonReceiveAssist;
	
	private boolean recording = false;
	private ArrayList<Double> intakeTimes = new ArrayList<Double>();
	
	private boolean viewsAssigned = false;
	
	private TeleData mTeleData;
	
	public static TeleFragment newInstance(TeleData teleData){
		TeleFragment tf = new TeleFragment();
		
		Bundle args = new Bundle();
		args.putParcelable("TeleData",teleData);
		
		tf.setArguments(args);
		
		return tf;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_scout_tele, container, false);
		
		Bundle args = getArguments();
		mTeleData = args.getParcelable("TeleData");
		
		if(viewsAssigned) loadData(mTeleData);
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		assignViews(view);
		loadData(mTeleData);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(!viewsAssigned);
		else if(isVisibleToUser)
		{
			assignViews(getView());
			loadData(mTeleData);
			
		}
		else if(!isVisibleToUser)
		{
			saveData(mTeleData);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		saveData(mTeleData);
		viewsAssigned=false;
	}
	
	private void loadData(final TeleData teleData){
		intakeTimes = teleData.intakeTimes;
		lowScore.setText(""+teleData.lowScore);
	}
	
	private void saveData(TeleData teleData){
		if(viewsAssigned) {
			teleData.intakeTimes = intakeTimes;
			int lowScoreValue = 0;
			try{
				lowScoreValue = Integer.parseInt(lowScore.getText().toString());
			} catch(NumberFormatException e){
				e.printStackTrace();
				lowScoreValue = 0;
			}
			teleData.lowScore = lowScoreValue;
		}
	}
	
	private void assignViews(View view){
		try{
			buttonUndo = (Button)view.findViewById(R.id.buttonUndo);
			buttonRedo = (Button)view.findViewById(R.id.buttonRedo);
			intakeTime = (TextView)view.findViewById(R.id.titleIntakeTime);
			buttonRecord = (Button)view.findViewById(R.id.buttonRecordIntake);
			buttonLowScoreDown = (Button)view.findViewById(R.id.buttonDownLowScore);
			buttonLowScoreUp = (Button)view.findViewById(R.id.buttonUpLowScore);
			lowScore = (EditText)view.findViewById(R.id.lowScore);
			buttonTruss = (Button)view.findViewById(R.id.buttonTruss);
			buttonCatch = (Button)view.findViewById(R.id.buttonCatch);
			buttonGoal = (Button)view.findViewById(R.id.buttonScore);
			buttonGiveAssist = (Button)view.findViewById(R.id.buttonGiveAssist);
			buttonReceiveAssist = (Button)view.findViewById(R.id.buttonReceiveAssist);
			
			if(recording)
				buttonRecord.setText(getResources().getString(R.string.button_recordStop));
			else
				buttonRecord.setText(getResources().getString(R.string.button_recordStart));
			if(intakeTimes.size()>0)
				intakeTime.setText(""+intakeTimes.get(intakeTimes.size()-1));
			
			buttonUndo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//undo last drawing action on Canvas
					
				}
				
			});
			
			buttonRedo.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					//redo last undone drawing action (up to x number of actions)
					
				}
				
			});
			
			buttonRecord.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					recording = !recording;
					final Handler handler = new Handler();
					Runnable runnable = new Runnable(){
						@Override
						public void run() {
							while(recording)
							{
								try{
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								handler.post(new Runnable() {
									@Override
									public void run() {
										double newValue = 0;
										try{
											newValue = Math.floor((Double.parseDouble(intakeTime.getText().toString())+0.10) * 1e2) / 1e2;
										} catch(NumberFormatException e) {
											e.printStackTrace();
											newValue = 0;
										}
										intakeTime.setText(""+newValue);
									}
								});
							}
						}
					};
					
					if(recording) {
						//before starting again, set the time to 0
						intakeTime.setText("0.0");
						
						//update the button text to reflect that it is now a stop button
						buttonRecord.setText(getResources().getString(R.string.button_recordStop));
						new Thread(runnable).start();
					}
					else {
						// update the buton text to reflect that is again a start button
						buttonRecord.setText(getResources().getString(R.string.button_recordStart));
						// update the time in the ArrayList
						double finalTime = 0;
						try{
							finalTime = Math.floor((Double.parseDouble(intakeTime.getText().toString())+0.1) * 1e2) / 1e2;
						} catch(NumberFormatException e){
							e.printStackTrace();
						}
						intakeTimes.add(finalTime);
					}
				}
				
			});
			
			buttonLowScoreDown.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					int newValue = 0;
					try{
						newValue = Integer.parseInt(lowScore.getText().toString())-1;
						if (newValue < 0)
							newValue = 0;
					} catch (NumberFormatException e){
						e.printStackTrace();
						newValue = 0;
					}
					lowScore.setText(""+newValue);
				}
				
			});
			
			buttonLowScoreUp.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					int newValue = 0;
					try{
						newValue = Integer.parseInt(lowScore.getText().toString())+1;
					} catch (NumberFormatException e){
						e.printStackTrace();
						newValue = 0;
					}
					lowScore.setText(""+newValue);
				}
				
			});
			
			buttonTruss.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});
			
			buttonCatch.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});
			
			buttonGoal.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});
			
			buttonGiveAssist.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});
			
			buttonReceiveAssist.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
				}
			});
			
			viewsAssigned=true;
		} catch (Exception e){
			e.printStackTrace();
			viewsAssigned=false;
		}
	}
}