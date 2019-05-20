package com.brhm.httpmonitor.statistics;

import java.util.TreeMap;

public class Statistics {
	
	private TreeMap<String, Integer> sectionsCount = new TreeMap<String, Integer>();
	
	private int totalHits = 0;
	
	private int totalHitsLast10Seconds = 0;
	
	private int totalHitsLast2Minuts = 0;
	
	
	public synchronized void addSection(String section) {
		addHit();
		if(!sectionsCount.containsKey(section)) {
			sectionsCount.put(section, 1);
		} else {
			int count = sectionsCount.get(section);
			
			sectionsCount.put(section, count + 1);
		}
	}
	
	public synchronized String getMostHitsSection() {
		String mostHitedSection;
		
		if(sectionsCount.isEmpty())
			mostHitedSection = "NONE";
		else  {
			mostHitedSection = sectionsCount.lastKey();
			
			sectionsCount.clear();
			totalHitsLast10Seconds = 0;
		}
			
		return mostHitedSection;
	}
	
	public synchronized void addHit() {
		totalHits++;
		totalHitsLast10Seconds++;
		totalHitsLast2Minuts++;
	}

	public synchronized void reset10Second() {
		totalHitsLast10Seconds = 0;
	}
	
	public synchronized void reset2Min() {
		totalHitsLast2Minuts = 0;
	}
	
	public synchronized int getTotalHits() {
		return totalHits;
	}
	
	public synchronized int getTotalHitsLast10Seconds() {
		return totalHitsLast10Seconds;
	}

	public synchronized int getTotalHitsLast2Minuts() {
		return totalHitsLast2Minuts;
	}	
}
