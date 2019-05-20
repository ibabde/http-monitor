package com.brhm.httpmonitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.brhm.httpmonitor.logreader.CommonLogParser;
import com.brhm.httpmonitor.logreader.LogFileReader;
import com.brhm.httpmonitor.logreader.LogReader;
import com.brhm.httpmonitor.resultwriter.ConsoleWriter;
import com.brhm.httpmonitor.resultwriter.ResultWriter;
import com.brhm.httpmonitor.statistics.RealClock;
import com.brhm.httpmonitor.statistics.SectionsStatistics;
import com.brhm.httpmonitor.statistics.StatisticListener;
import com.brhm.httpmonitor.statistics.Statistics;
import com.brhm.httpmonitor.statistics.ThreshHoldStatistics;

public class App {
	private static final int THRESH_HOLD = 1000;
	
	public static void main(String[] args) {
		System.out.println("The application started...");
		
		ResultWriter writer = new ConsoleWriter();
		final Statistics statistics = new Statistics();
		
		StatisticListener sectionsStatistics = new SectionsStatistics(statistics, new RealClock(10, TimeUnit.SECONDS), writer);
		sectionsStatistics.scheduleStats();
		
		StatisticListener threshHoldStatistics = new ThreshHoldStatistics(statistics, new RealClock(2, TimeUnit.MINUTES), writer,THRESH_HOLD);
		threshHoldStatistics.scheduleStats();
		
		FileInputStream logFile = null;
		
		try {
			logFile = new FileInputStream("access.log");
			LogReader logReader = new LogFileReader(logFile,new CommonLogParser());
			
			
			while(true) {
				LogEntry entry = logReader.readOne();
				
				statistics.addSection(entry.resource);
				
				writer.write(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(logFile != null) {
				try {
					logFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
