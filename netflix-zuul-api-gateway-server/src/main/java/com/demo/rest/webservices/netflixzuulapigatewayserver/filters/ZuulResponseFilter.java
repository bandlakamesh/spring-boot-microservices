package com.demo.rest.webservices.netflixzuulapigatewayserver.filters;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulResponseFilter extends ZuulFilter {

	Logger log = LoggerFactory.getLogger(ZuulResponseFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
	    try (final InputStream responseDataStream = context.getResponseDataStream()) {
	        if(responseDataStream == null) {
	        	log.info("BODY: {}", "");
	            return null;
	        }
	 
	        String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
	        log.info("BODY: {}", responseData);
	 
	        context.setResponseBody(responseData);
	    }
	    catch (Exception e) {
	        throw new ZuulException(e, 500 ,e.getMessage());
	    }
	 
	    return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
