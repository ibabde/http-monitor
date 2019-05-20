package com.brhm.httpmonitor.logreader;

import java.io.IOException;

import com.brhm.httpmonitor.LogEntry;

public interface LogReader {
	LogEntry readOne() throws InterruptedException, IOException;
}
