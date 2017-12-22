package com.ef;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ef.domain.LogPeriod;
import com.ef.service.ParserService;
import com.ef.util.CommandLineParameterParser;

public class Parser {

	public static void main(String[] args) throws Exception {
		CommandLineParameterParser commandLine = new CommandLineParameterParser(args);

		LocalDateTime startDate = LocalDateTime.parse(commandLine.getStartDate(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));

		ApplicationContext ctx = new AnnotationConfigApplicationContext("com.ef");
		ParserService service = ctx.getBean(ParserService.class);

		// service.loadFileToDatabase(commandLine.getAccessLog());
		service.getIpsExceedingThreshold(startDate, LogPeriod.valueOf(commandLine.getDuration().toUpperCase()),
				commandLine.getThreshold()).forEach(ip -> System.out.println("Exceeded:" + ip));
	}
}
