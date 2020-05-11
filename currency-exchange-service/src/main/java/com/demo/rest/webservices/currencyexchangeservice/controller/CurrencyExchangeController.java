package com.demo.rest.webservices.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.webservices.currencyexchangeservice.model.ExchangeValue;
import com.demo.rest.webservices.currencyexchangeservice.service.ExchangeValueDaoImpl;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	Environment env;
	
	@Autowired
	ExchangeValueDaoImpl service;
	
	Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@GetMapping("/currencyExchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = service.getExchangeValue(from, to);
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		log.info("ExchangeValue :: {}", exchangeValue);
		return exchangeValue;
	}
	
	@PostMapping("/currencyExchange/create")
	public ExchangeValue createExchangeValue(@RequestBody ExchangeValue value) {
		return service.createExchangeValue(value);
	}
	
}
