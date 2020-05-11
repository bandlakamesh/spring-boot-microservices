package com.demo.rest.webservices.currencyexchangeservice.service;

import com.demo.rest.webservices.currencyexchangeservice.model.ExchangeValue;

public interface ExchangeValueDao {

	ExchangeValue createExchangeValue(ExchangeValue value);

	ExchangeValue getExchangeValue(String from, String to);

}
