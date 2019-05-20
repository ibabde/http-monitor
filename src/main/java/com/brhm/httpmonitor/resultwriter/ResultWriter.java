package com.brhm.httpmonitor.resultwriter;

import com.brhm.httpmonitor.LogEntry;

public interface ResultWriter {
	void write(LogEntry entry);
	
	void writeMostHitedSection(String section,int numberHits);
	
	void writeThreshHoldPassed(int numberHits);
	
	void writeThreshHoldDroped(int numberHits);
}
