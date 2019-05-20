package com.brhm.httpmonitor.statistics;

import com.brhm.httpmonitor.resultwriter.ResultWriter;
import com.brhm.httpmonitor.statistics.Clock.Listener;

public abstract class StatisticListener implements Listener {
	
	protected final Statistics statistics;
	protected ResultWriter resultWriter;
	private Clock clock;

	public StatisticListener(Statistics statistics, Clock clock, ResultWriter resultWriter) {
		this.statistics = statistics;
		this.resultWriter = resultWriter;
		this.clock = clock;
		
		this.clock.register(this);
	}
	
	public void scheduleStats() {
		clock.start();
	}
}
