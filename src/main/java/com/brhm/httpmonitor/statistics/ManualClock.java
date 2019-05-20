package com.brhm.httpmonitor.statistics;

import java.util.ArrayList;
import java.util.List;

public class ManualClock implements Clock {
	private final List<Listener> listeners = new ArrayList<>();

	@Override
	public void register(Listener listener) {
		listeners.add(listener);
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	public void elapseTime() {
		listeners.forEach(Listener::timeElapsed);
	}
}
