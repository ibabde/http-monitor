package com.brhm.httpmonitor.statistics;

// abstraction of clock so that we can unit test statistics 
public interface Clock {
	void register(Listener listener);

	void start();

	void stop();

	interface Listener {
		void timeElapsed();
	}
}