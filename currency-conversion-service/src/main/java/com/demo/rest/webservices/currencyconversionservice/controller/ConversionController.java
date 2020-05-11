package com.demo.rest.webservices.currencyconversionservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.webservices.currencyconversionservice.model.CurrencyConversionBean;
import com.demo.rest.webservices.currencyconversionservice.service.ConversionServiceImpl;

@RestController
public class ConversionController {
	
	Logger log = LoggerFactory.getLogger(ConversionController.class);
	
	@Autowired
	ConversionServiceImpl service;
	
	@GetMapping("/convert/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getConversionValue(@PathVariable String from, @PathVariable String to, @PathVariable String quantity) {
		return service.getConversionValue(from, to, quantity);
	}
	
	@GetMapping("/convert-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean getConversionValueUsingFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable String quantity) {
		CurrencyConversionBean conversionValueUsingFeign = service.getConversionValueUsingFeign(from, to, quantity);
		log.info("CurrencyConversionBean :: {}", conversionValueUsingFeign);
		return conversionValueUsingFeign;
	}

}
