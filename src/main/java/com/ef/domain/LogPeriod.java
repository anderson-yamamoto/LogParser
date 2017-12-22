package com.ef.domain;

public enum LogPeriod {
	DAILY(24, "24 hours"), HOURLY(1, "one hour");
	
	private String message;
	private int hours;

	private LogPeriod(int hours, String message){
		this.hours = hours;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getHours() {
		return hours;
	}
}
