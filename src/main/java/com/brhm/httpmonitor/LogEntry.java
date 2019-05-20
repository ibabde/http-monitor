package com.brhm.httpmonitor;

public class LogEntry {

	public String ip;

	public int statusCode;

	public String httpMethod;

	public int size;

	public String resource;

	@Override
	public String toString() {
		return String.format("%s %d %s %d %s", ip, statusCode, httpMethod, size, resource);
	}
}
