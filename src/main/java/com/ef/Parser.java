package com.ef;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ef.domain.LogPeriod;
import com.ef.service.ParserService;
import com.ef.util.CommandLineParameterParser;

@SpringBootApplication
public class Parser implements CommandLineRunner {

	@Autowired
	private ParserService service;


	public static void main(String[] args) throws Exception {
		SpringApplication.run(Parser.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		CommandLineParameterParser commandLine = new CommandLineParameterParser(args);
		
		LocalDateTime startDate = LocalDateTime.parse(commandLine.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
				

    	//service.loadFileToDatabase(commandLine.getAccessLog());
    	service.getIpsExceedingThreshold(startDate, LogPeriod.valueOf(commandLine.getDuration().toUpperCase()), commandLine.getThreshold()).forEach(ip -> System.out.println("Exceeded:" + ip));
    }

}
