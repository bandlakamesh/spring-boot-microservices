package com.demo.rest.webservices.currencyexchangeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rest.webservices.currencyexchangeservice.model.ExchangeValue;
import com.demo.rest.webservices.currencyexchangeservice.repository.ExchangeValueRepository;

@Service
public class ExchangeValueDaoImpl implements ExchangeValueDao {
	
	@Autowired
	ExchangeValueRepository repo;

	@Override
	public ExchangeValue createExchangeValue(ExchangeValue value) {
		return repo.save(value);
	}

	@Override
	public ExchangeValue getExchangeValue(String from, String to) {
		return repo.findByFromAndTo(from, to);
	}

}
