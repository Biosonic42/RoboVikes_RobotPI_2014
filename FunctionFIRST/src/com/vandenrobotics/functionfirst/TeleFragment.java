package com.vandenrobotics.functionfirst;

import com.vandenrobotics.functionfirst.model.TeleData;
import com.vandenrobotics.functionfirst.views.CycleLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

public class TeleFragment extends Fragment {

	private CycleLayout cycleLayout;
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
			cycleLayout.cycleGrid.gridBox.setVisibility(SurfaceView.VISIBLE);
			loadData(mTeleData);
			cycleLayout.loadData();
			cycleLayout.cycleGrid.gridBox.drawLines();
			
		}
		else if(!isVisibleToUser)
		{
			saveData(mTeleData);
			cycleLayout.cycleGrid.gridBox.setVisibility(SurfaceView.INVISIBLE);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		saveData(mTeleData);
		viewsAssigned=false;
	}
	
	private void loadData(final TeleData teleData){
		cycleLayout.cycles = teleData.cycles;
		if(viewsAssigned)
			cycleLayout.loadData();
	}
	
	private void saveData(TeleData teleData){
		if(viewsAssigned) {
			cycleLayout.saveData();
			teleData.cycles = cycleLayout.cycles;
		}
	}
	
	private void assignViews(View view){
		cycleLayout = (CycleLayout)view.findViewById(R.id.cycleLayout);
		
		viewsAssigned=true;
	}
}