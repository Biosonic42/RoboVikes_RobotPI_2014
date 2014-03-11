package com.vandenrobotics.functionfirst.model;

import android.graphics.Point;

public class HotSpot extends Point {
	
	public double x;
	public double y;
	public int type;
	
	public HotSpot(int type1, double x1, double y1){
		x = x1;
		y = y1;
		type = type1;
	}
}
