package com.ef.domain;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ef.service.LogReader;
import com.ef.service.ParserService;


public class ParserServiceTest {
	@InjectMocks
	private ParserService service;
	
	@Mock
    private IpAccessRepository repository;
	
	@Mock
    private LogReader reader;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	public void loadFileToDatabase(){
		ArrayList<IpAccess> list = new ArrayList<>();
		String filename = "file";
		when(reader.readFromFile(any(String.class))).thenReturn(list);
		
		service.loadFileToDatabase(filename);
		
		verify(reader).readFromFile(eq(filename));
		verify(repository).batchInsert(eq(list));
	}
	
	@Test
	public void getIpsExceedingThresholdDaily(){
		int threshold = 100;
		LocalDateTime startDate = LocalDateTime.now();
		
		ArrayList<String> list = new ArrayList<>();
		when(repository.findIpsWithThresholdBetweenDates(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(list);
		
		service.getIpsExceedingThreshold(startDate, LogPeriod.DAILY, threshold);
		
		verify(repository).findIpsWithThresholdBetweenDates(eq(threshold), eq(startDate), eq(startDate.plusHours(24)));
	}

	@Test
	public void getIpsExceedingThresholdHourly(){
		int threshold = 100;
		LocalDateTime startDate = LocalDateTime.now();
		
		ArrayList<String> list = new ArrayList<>();
		when(repository.findIpsWithThresholdBetweenDates(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(list);
		
		service.getIpsExceedingThreshold(startDate, LogPeriod.HOURLY, threshold);
		
		verify(repository).findIpsWithThresholdBetweenDates(eq(threshold), eq(startDate), eq(startDate.plusHours(1)));
	}
}
