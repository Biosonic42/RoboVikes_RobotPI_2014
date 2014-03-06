package com.vandenrobotics.functionfirst;

import com.vandenrobotics.functionfirst.robotpi.ScoutActivity;
import com.vandenrobotics.functionfirst.ussfrc.CoachActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void goTo_scoutActivity(View view)
    {
    	Intent intent = new Intent(this, ScoutActivity.class);
    	startActivity(intent);
    }
    
    public void goTo_coachActivity(View view)
    {
    	Intent intent = new Intent(this, CoachActivity.class);
    	startActivity(intent);
    }
    
    public void showAbout(View view)
    {
    	new AlertDialog.Builder(this)
    		.setTitle("Function FIRST " + getResources().getString(R.string.version_number))
    		.setMessage("Function FIRST: RobotPI (Robotics Private Investigator) is designed as an electronic alternative to scouting.\n" 
    				+ "The scouting application records objective data about each individual team at an FRC event. \n"
    				+ "It is used in conjunction with a custom database (coming soon) to prepare a team list for "
    				+ "alliance selection and predict match strategies. \n\n"
    				+ "Function FIRST: USS FRC (Unitary Strategy Solution) is designed as way to facilitate strategy development between alliance partners.\n"
    				+ "By drawing out strategies, drive coaches can increase their odds of winning and guarantee alliance partner cooperation.  "
    				+ "Often times having a strategy drawn and clearly depicted can be the difference between winning and losing a match.\n\n"
    				+ "App coded and designed by Joseph A. Lewis from FRC Team 701")
    		.show();
    }
    
}
