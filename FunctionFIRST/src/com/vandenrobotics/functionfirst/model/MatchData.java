package com.vandenrobotics.functionfirst.model;

public class MatchData {
	public InitData initData;
	public AutoData autoData;
	public TeleData teleData;
	public PostData postData;
	
	public MatchData(){
		initData = new InitData();
		autoData = new AutoData();
		teleData = new TeleData();
		postData = new PostData();
	}
	
	@Override
	public String toString(){
		return initData.toString()+","+autoData.toString()+","
	          +teleData.toString()+","+postData.toString();
	}
}
