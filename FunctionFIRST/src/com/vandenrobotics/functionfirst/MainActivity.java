package com.vandenrobotics.functionfirst;

import com.vandenrobotics.functionfirst.robotpi.ScoutActivity;
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
    
    public void showAbout(View view)
    {
    	new AlertDialog.Builder(this)
    		.setTitle("RobotPI " + getResources().getString(R.string.version_number))
    		.setMessage("Robotics Private Investigator (RobotPI) is designed by FRC Team 701 as an electronic alternative to scouting.\n" +
    				"The scouting application records both objective and subjective data about each individual team at an FRC event. \n"
    				+ "It is used to compare teams for alliance selection as well as predict match outcomes. \n"
    				+ "Coded and Designed by Joseph A. Lewis from FRC Team 701")
    		.show();
    }
    
}
