package com.vandenrobotics.functionfirst.views;

import com.vandenrobotics.functionfirst.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class RadioSwitch extends RelativeLayout {

	public SeekBar switchGoals;
	public SeekBar switchTC;
	
	public RadioSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public RadioSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RadioSwitch(Context context) {
        super(context);
        initView();
    }
    
    private void initView(){
		View view = inflate(getContext(), R.layout.radio_switch, null);
		addView(view);
		switchGoals = (SeekBar)view.findViewById(R.id.switchGoals);
		switchTC = (SeekBar)view.findViewById(R.id.switchTC);
	}
}
