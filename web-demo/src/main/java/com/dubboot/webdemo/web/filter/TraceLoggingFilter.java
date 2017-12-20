package com.dubboot.webdemo.web.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dubboot.trackid.utils.TraceIDUtils;

@Component
public class TraceLoggingFilter extends OncePerRequestFilter implements Ordered{

	private int order = Ordered.HIGHEST_PRECEDENCE;

	@Override
	public int getOrder() {
		return this.order;
	}

	/**
	 * Set the order for this filter.
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(TraceIDUtils.getTraceId()==null){ // For Best practice, this should be an edge service and just an consumer in dubbo. So the traceId should be null.
			TraceIDUtils.setTraceId(UUID.randomUUID().toString());
			String mdcData = String.format("[TraceID:%s]", TraceIDUtils.getTraceId());
			MDC.put("mdcData", mdcData);			
		}
		filterChain.doFilter(request, response);
	}

}
