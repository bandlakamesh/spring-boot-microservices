package com.demo.rest.webservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.rest.webservices.currencyexchangeservice.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer>{

	ExchangeValue findByFromAndTo(String from, String to);

}
