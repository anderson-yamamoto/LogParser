package com.ef.domain;

import java.time.LocalDateTime;

public class IpAccess {
	private int id;

	private String ip;
	private int count;
	private LocalDateTime accessDate;

	public IpAccess(String ip, LocalDateTime accessDate) {
		this.ip = ip;
		this.accessDate = accessDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalDateTime getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(LocalDateTime accessDate) {
		this.accessDate = accessDate;
	}
}
