package com.brhm.httpmonitor.statistics;

import com.brhm.httpmonitor.resultwriter.ResultWriter;

public class SectionsStatistics extends StatisticListener {

	public SectionsStatistics(Statistics statistics, Clock clock, ResultWriter resultWriter) {
		super(statistics, clock, resultWriter);
	}

	@Override
	public void timeElapsed() {
		synchronized (statistics) {
			int numberHits = statistics.getTotalHitsLast10Seconds();
			String mostHitedSection = statistics.getMostHitsSection();
			
			resultWriter.writeMostHitedSection(mostHitedSection, numberHits);
		}
	}
}
