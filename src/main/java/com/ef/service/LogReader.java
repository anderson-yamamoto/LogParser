package com.ef.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ef.domain.IpAccess;

@Service
public class LogReader {

	@Value("${logparser.reader.separator}")
	private String SEPARATOR;
	

	@Value("${logparser.reader.datepattern}")
	private String DATE_PATTERN;
	
	private static final int DATE = 0;
	private static final int IP = 1;
	
	public List<IpAccess> readFromFile(String filename) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		ArrayList<IpAccess> list = new ArrayList<IpAccess>(1000);
		try (Scanner scanner = new Scanner(new File(filename))) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] fields = parse(line);
				list.add(new IpAccess(fields[IP], LocalDateTime.parse(fields[DATE], formatter)));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return list;
	}

	private String[] parse(String line) {
		return line.split(SEPARATOR);
	}
}
