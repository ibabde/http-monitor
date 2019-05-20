package com.brhm.httpmonitor.resultwriter;

import java.io.PrintStream;

import com.brhm.httpmonitor.LogEntry;

public class ConsoleWriter implements ResultWriter {
	
	private final PrintStream out;
	
	public ConsoleWriter() {
		out = System.out;
	}
	
	public void write(LogEntry entry) {
		out.println(entry);
	}

	public void writeMostHitedSection(String section, int numberHits) {
		out.println("section : "+section+" had the most hits, number total hits in 10 seconds "+numberHits);
	}

	public void writeThreshHoldPassed(int numberHits) {
		out.print("Threshold Passed number hits "+numberHits);
	}

	public void writeThreshHoldDroped(int numberHits) {
		out.print("Threshold droped number hits "+numberHits);
	}
}
