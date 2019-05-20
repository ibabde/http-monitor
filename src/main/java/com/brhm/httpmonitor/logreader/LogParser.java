package com.brhm.httpmonitor.logreader;

import com.brhm.httpmonitor.LogEntry;

public interface LogParser {
	LogEntry parse(String line);
}
