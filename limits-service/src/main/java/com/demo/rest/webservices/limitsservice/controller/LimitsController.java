package com.demo.rest.webservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.webservices.limitsservice.config.PropertiesConfiguration;
import com.demo.rest.webservices.limitsservice.model.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope
public class LimitsController {
	
	@Autowired
	PropertiesConfiguration config;
	
	@GetMapping("/limits")
	public LimitConfiguration getLimits() {
		return new LimitConfiguration(config.getMaximum(), config.getMinimum());
	}
	
	@GetMapping("/limits-fault")
	@HystrixCommand(fallbackMethod = "fallBack")
	public LimitConfiguration getLimitsFault() {
		throw new RuntimeException("Not Found");
	}
	
	public LimitConfiguration fallBack() {
		return new LimitConfiguration(666, 6);
	}

}
