package com.ef.domain;

public class BlockedIp {
	private String ip;
	private String reason;
	
	public BlockedIp(String ip, String reason) {
		super();
		this.ip = ip;
		this.reason = reason;
	}
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
