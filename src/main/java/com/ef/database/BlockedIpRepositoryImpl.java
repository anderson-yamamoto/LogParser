package com.ef.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ef.domain.BlockedIp;

@Component
public class BlockedIpRepositoryImpl implements BlockedIpRepository  {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private String insertQuery = "INSERT INTO blocked_ip (ip, reason) VALUES (?,?)";

	@Override
	public void insert(BlockedIp blockedIp) {
		jdbcTemplate.update(insertQuery, blockedIp.getIp(), blockedIp.getReason());
	}
	
	
}
