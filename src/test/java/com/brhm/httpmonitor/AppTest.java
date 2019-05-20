package com.brhm.httpmonitor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.brhm.httpmonitor.resultwriter.ResultWriter;
import com.brhm.httpmonitor.statistics.ManualClock;
import com.brhm.httpmonitor.statistics.SectionsStatistics;
import com.brhm.httpmonitor.statistics.Statistics;
import com.brhm.httpmonitor.statistics.ThreshHoldStatistics;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	public AppTest(String testName) {
		super(testName);
	}
	
	public void testSectionStatistics() {
		ManualClock clock = new ManualClock();
		Statistics statistics = new Statistics();
		
		ResultWriter mockedWriter = mock(ResultWriter.class);
		
		SectionsStatistics sectionsStatistics = new SectionsStatistics(statistics, clock, mockedWriter);
	
		sectionsStatistics.scheduleStats();
		statistics.addSection("hello");
		statistics.addSection("test");
		statistics.addSection("test");
		statistics.addSection("hi");
		
		clock.elapseTime();
		
		verify(mockedWriter).writeMostHitedSection("test", 4);
	}

	public void testThreshHoldStatistics() {
		ManualClock clock = new ManualClock();
		Statistics statistics = new Statistics();
		
		ResultWriter mockedWriter = mock(ResultWriter.class);
		ThreshHoldStatistics sectionsStatistics = new ThreshHoldStatistics(statistics, clock, mockedWriter,3);
	
		sectionsStatistics.scheduleStats();
		statistics.addHit();
		statistics.addHit();
		statistics.addHit();
		statistics.addHit();
		
		clock.elapseTime();
		
		verify(mockedWriter).writeThreshHoldPassed(4);
	}
	
}
