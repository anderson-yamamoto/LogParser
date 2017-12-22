package com.ef.domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class IpAccessRepositoryImpl implements IpAccessRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String insertQuery = "INSERT INTO ip_access (ip, access_date) VALUES (?,?)";

	private String selectThreshold = "SELECT ip FROM ip_access where access_date between ? and ? group by ip having count(0) > ?";
	
	@Override
	public void insert(IpAccess ipAccess){
		jdbcTemplate.update(insertQuery, ipAccess.getIp(), ipAccess.getAccessDate());
	}
	
	@Override
	public void batchInsert(List<IpAccess> list){
		jdbcTemplate.batchUpdate(insertQuery, 
				new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getIp());
                ps.setTimestamp(2, Timestamp.valueOf(list.get(i).getAccessDate()));
            }

            public int getBatchSize() {
                return list.size();
            }
        } );
				
	}

	@Override
	public List<String> findIpsWithThresholdBetweenDates(int threshold, LocalDateTime start, LocalDateTime end) {
		return jdbcTemplate.queryForList(selectThreshold, String.class, start, end, threshold);
	}
	
	
}
