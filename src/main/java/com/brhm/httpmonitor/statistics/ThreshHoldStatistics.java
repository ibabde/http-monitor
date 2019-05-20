package com.brhm.httpmonitor.statistics;

import com.brhm.httpmonitor.resultwriter.ResultWriter;

public class ThreshHoldStatistics extends StatisticListener {
	
	private int threshHold;

	public ThreshHoldStatistics(Statistics statistics, Clock clock, ResultWriter resultWriter, int threshHold) {
		super(statistics, clock, resultWriter);
		this.threshHold = threshHold;
	}

	@Override
	public void timeElapsed() {
		int numberHits = statistics.getTotalHitsLast2Minuts();
		statistics.reset2Min();
		
		if(numberHits >= threshHold) {
			resultWriter.writeThreshHoldPassed(numberHits);
		} else {
			resultWriter.writeThreshHoldDroped(numberHits);	
		}
	}
}
