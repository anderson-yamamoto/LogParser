package com.ef.domain;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ef.service.LogReader;


public class LogReaderTest {
	
    private LogReader reader = new LogReader();
	
	@Before
	public void setup(){
		reader.setSeparator("\\|");
		reader.setDatePattern("yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	@Test
	public void readFromFile(){
		System.out.println(new File("example.xml").getAbsolutePath());
		
		String filename = "src/test/resources/testAccess.log";
		
		List<IpAccess> list = reader.readFromFile(filename);
		
		assertEquals(2, list.size());
		assertEquals("192.168.234.82", list.get(0).getIp());
		assertEquals(LocalDateTime.of(2017, 01, 01,0,0,21,164000000), list.get(0).getAccessDate());
		assertEquals("192.168.169.194", list.get(1).getIp());
		assertEquals(LocalDateTime.of(2017, 01, 01,0,0,23,3000000), list.get(1).getAccessDate());
	}
}
