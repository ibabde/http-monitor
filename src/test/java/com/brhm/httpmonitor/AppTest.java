package com.brhm.httpmonitor;

import com.brhm.httpmonitor.resultwriter.ResultWriter;
import com.brhm.httpmonitor.statistics.ManualClock;
import com.brhm.httpmonitor.statistics.SectionsStatistics;
import com.brhm.httpmonitor.statistics.Statistics;
import com.brhm.httpmonitor.statistics.ThreshHoldStatistics;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	
	public void testSectionStatistics() {
		ManualClock clock = new ManualClock();
		Statistics statistics = new Statistics();
		
		// TODO: use mockito 
		SectionsStatistics sectionsStatistics = new SectionsStatistics(statistics, clock, new ResultWriter() {
			
			@Override
			public void writeThreshHoldPassed(int numberHits) {
			}
			
			@Override
			public void writeThreshHoldDroped(int numberHits) {
			}
			
			@Override
			public void writeMostHitedSection(String section, int numberHits) {
				assertEquals("test", section);
				assertEquals(4, numberHits);
			}
			
			@Override
			public void write(LogEntry entry) {
			}
		});
	
		sectionsStatistics.scheduleStats();
		statistics.addSection("hello");
		statistics.addSection("test");
		statistics.addSection("test");
		statistics.addSection("hi");
		
		clock.elapseTime();
	}
	

	public void testThreshHoldStatistics() {
		ManualClock clock = new ManualClock();
		Statistics statistics = new Statistics();
		
		// TODO: use mockito 
		ThreshHoldStatistics sectionsStatistics = new ThreshHoldStatistics(statistics, clock, new ResultWriter() {
			
			@Override
			public void writeThreshHoldPassed(int numberHits) {
			}
			
			@Override
			public void writeThreshHoldDroped(int numberHits) {
				assertEquals(4, numberHits);
			}
			
			@Override
			public void writeMostHitedSection(String section, int numberHits) {
				
			}
			
			@Override
			public void write(LogEntry entry) {
			}
		},3);
	
		sectionsStatistics.scheduleStats();
		statistics.addHit();
		statistics.addHit();
		statistics.addHit();
		statistics.addHit();
		
		
		clock.elapseTime();
	}
	
}
