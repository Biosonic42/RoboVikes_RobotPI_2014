package com.vandenrobotics.functionfirst.robotpi;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.PostData;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.NumberPicker;

public class PostFragment extends Fragment {
	
	private CheckBox postDefensive;
	private CheckBox postAggressive;
	private CheckBox postYellowCard;
	private CheckBox postRedCard;
	private CheckBox postDisabled;
	private CheckBox postNoShow;
	
	private NumberPicker postRegFouls;
	private NumberPicker postTechFouls;

	
	private boolean viewsAssigned = false;
	
	private PostData mPostData;
	
	public static PostFragment newInstance(PostData postData){
		PostFragment pf = new PostFragment();
		
		Bundle args = new Bundle();
		args.putParcelable("PostData",postData);
		
		pf.setArguments(args);
		
		return pf;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_scout_post, container, false);
		
		Bundle args = getArguments();
		mPostData = args.getParcelable("PostData");
		
		if(viewsAssigned) loadData(mPostData);
		
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		assignViews(view);
		loadData(mPostData);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if(!viewsAssigned);
		else if(isVisibleToUser)
		{
			assignViews(getView());
			loadData(mPostData);
		}
		else if(!isVisibleToUser)
		{
			saveData(mPostData);
		}
	}
	
	@Override
	public void onPause(){
		super.onPause();
		saveData(mPostData);
		viewsAssigned=false;
	}
	
	private void loadData(final PostData postData){
		
		postDisabled.setChecked(postData.disabled);
		postYellowCard.setChecked(postData.yellowCard);
		postRedCard.setChecked(postData.redCard);
		postDefensive.setChecked(postData.defensive);
		postAggressive.setChecked(postData.aggressive);
		postNoShow.setChecked(postData.noShow);
		
		postRegFouls.setValue(postData.regFouls);
		postTechFouls.setValue(postData.techFouls);
	}
	
	private void saveData(PostData postData){
		if(viewsAssigned){
			postData.disabled = postDisabled.isChecked();
			postData.noShow = postNoShow.isChecked();
			postData.yellowCard = postYellowCard.isChecked();
			postData.redCard = postRedCard.isChecked();
			postData.defensive = postDefensive.isChecked();
			postData.aggressive = postAggressive.isChecked();
			postData.regFouls = postRegFouls.getValue();
			postData.techFouls = postTechFouls.getValue();
		}
	}
	
	private void assignViews(View view){
		postDisabled = (CheckBox)view.findViewById(R.id.postDisabled);
		postNoShow = (CheckBox)view.findViewById(R.id.postNoShow);
		postAggressive = (CheckBox)view.findViewById(R.id.postAggressive);
		postYellowCard = (CheckBox)view.findViewById(R.id.postYellowCard);
		postRedCard = (CheckBox)view.findViewById(R.id.postRedCard);
		postDefensive = (CheckBox)view.findViewById(R.id.postDefensive);
		postRegFouls = (NumberPicker)view.findViewById(R.id.postRegFouls);
		postRegFouls.setMinValue(0);
		postRegFouls.setMaxValue(9);
		postTechFouls = (NumberPicker)view.findViewById(R.id.postTechFouls);
		postTechFouls.setMinValue(0);
		postTechFouls.setMaxValue(9);
		viewsAssigned=true;
	}
}