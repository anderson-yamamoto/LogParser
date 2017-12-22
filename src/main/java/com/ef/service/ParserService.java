package com.ef.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.database.BlockedIpRepository;
import com.ef.database.IpAccessRepository;
import com.ef.domain.BlockedIp;
import com.ef.domain.IpAccess;
import com.ef.domain.LogPeriod;

@Service
public class ParserService {
	private static final String BLOCKED_MESSAGE = "This IP was blocked for exceeding the threshold of %d requests in %s";

	@Autowired
	private IpAccessRepository repository;

	@Autowired
	private BlockedIpRepository blockedRepository;

	@Autowired
	private LogReader reader;

	public void loadFileToDatabase(String filename) {
		List<IpAccess> list = reader.readFromFile(filename);
		repository.batchInsert(list);

	}

	public List<String> getIpsExceedingThreshold(LocalDateTime start, LogPeriod duration, int threshold) {
		LocalDateTime end = start.plusHours(duration.getHours());
		List<String> list = repository.findIpsWithThresholdBetweenDates(threshold, start, end);
		for (String s : list){
			try{
				blockedRepository.insert(new BlockedIp(s, generateMessage(threshold, duration)));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}

	private String generateMessage(int threshold, LogPeriod duration) {
		return String.format(BLOCKED_MESSAGE, threshold, duration.getMessage());
	}
}
