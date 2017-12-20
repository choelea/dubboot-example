package com.dubboot.trackid.utils;

public class TraceIDUtils {
	private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<String>();

	public static String getTraceId() {
		return TRACE_ID.get();
	}

	public static void setTraceId(String traceId) {
		TRACE_ID.set(traceId);
	}
}
