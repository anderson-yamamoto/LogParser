package com.ef.util;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CommandLineParameterParser {
	@Option(name = "--accesslog", aliases = { "-a" }, required = true, usage = "path to file to be read")
	private String accessLog;
	
	@Option(name = "--startDate", aliases = { "-s" }, required = true, usage = "startDate on format \"yyyy-MM-dd HH:mm:ss.SSS\"")
	private String startDate;
	
	@Option(name = "--duration", aliases = { "-d" }, required = true, usage = "duration can be \"hourly\" or \"daily\"" )
	private String duration;
	
	@Option(name = "--threshold", aliases = { "-t" }, required = true, usage = "threshold should be an int")
	private int threshold;	

	private boolean errorFree = false;

	public CommandLineParameterParser(String... args) {
		CmdLineParser parser = new CmdLineParser(this);
		try {
			parser.parseArgument(args);

			errorFree = true;
		} catch (CmdLineException e) {
			System.err.println(e.getMessage());
			parser.printUsage(System.err);
		}
	}

	/**
	 * Returns whether the parameters could be parsed without an error.
	 *
	 * @return true if no error occurred.
	 */
	public boolean isErrorFree() {
		return errorFree;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getDuration() {
		return duration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getAccessLog() {
		return accessLog;
	}

	public void setAccessLog(String accessLog) {
		this.accessLog = accessLog;
	}

}
