package com.ef.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.domain.IpAccess;
import com.ef.domain.IpAccessRepository;
import com.ef.domain.LogPeriod;

@Service
public class ParserService {
	@Autowired
    private IpAccessRepository repository;
	
	@Autowired
    private LogReader reader;
	
	public void loadFileToDatabase(String filename){
		List<IpAccess> list = reader.readFromFile(filename);
		repository.batchInsert(list);
		
	}

	public List<String> getIpsExceedingThreshold(LocalDateTime start, LogPeriod duration, int threshold) {
		LocalDateTime end = start.plusHours(duration.getHours());
		return repository.findIpsWithThresholdBetweenDates(threshold, start, end);
	}
}
