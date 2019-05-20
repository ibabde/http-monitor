package com.brhm.httpmonitor.logreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.brhm.httpmonitor.LogEntry;

public class LogFileReader implements LogReader {
	
	private BufferedReader bufferedReader;
	private LogParser logPraser;
	
	public LogFileReader(InputStream inputstream,LogParser parser) {
		this.bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
		this.logPraser = parser;
	}
	
	public LogEntry readOne() throws InterruptedException, IOException {
		String line = null;
		while((line = bufferedReader.readLine()) == null) {
			Thread.sleep(1000);
		}
			
		return this.logPraser.parse(line);
	}
}
