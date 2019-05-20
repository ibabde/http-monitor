package com.brhm.httpmonitor.logreader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brhm.httpmonitor.LogEntry;

public class CommonLogParser implements LogParser {
	private final int NUM_FIELDS = 9;
	private final String logPattern = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)\\s?(\\S+)?\\s?(\\S+)?\" (\\d{3}|-) (\\d+|-)";

	public LogEntry parse(String line) {
		Pattern p = Pattern.compile(logPattern);
	    Matcher matcher = p.matcher(line);
	    
	    if (!matcher.matches() || matcher.groupCount() != NUM_FIELDS) {
	    	throw new IllegalArgumentException(String.format("Number of fields found doesnt match found %d expected %s\n%s",matcher.groupCount(),NUM_FIELDS,line));
	    }
	    
	    LogEntry entry = new LogEntry();
	    
	    entry.ip = matcher.group(1);
	    entry.statusCode = Integer.parseInt(matcher.group(8));
	    entry.httpMethod = matcher.group(5);
	    entry.size = Integer.parseInt(matcher.group(9));
	    entry.resource = matcher.group(6);
		return entry;
	}

}
