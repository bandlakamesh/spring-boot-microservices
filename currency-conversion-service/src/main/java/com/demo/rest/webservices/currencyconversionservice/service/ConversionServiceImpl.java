package com.demo.rest.webservices.currencyconversionservice.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.rest.webservices.currencyconversionservice.model.CurrencyConversionBean;

@Service
public class ConversionServiceImpl {
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;

	@Value("${currency-exchange-service.url}")
	private String url;
	
	public CurrencyConversionBean getConversionValue(String from, String to, String quantity) {
		String requestUrl = url+"from/"+from+"/to/"+to;
		CurrencyConversionBean bean = template.getForObject(requestUrl, CurrencyConversionBean.class);
		bean.setQuantity(BigDecimal.valueOf(Integer.parseInt(quantity)));
		bean.setTotalAmount(bean.getQuantity().multiply(bean.getConversionMultiple()));
		return bean;
	}
	
	public CurrencyConversionBean getConversionValueUsingFeign(String from, String to, String quantity) {
		CurrencyConversionBean bean = proxy.getExchangeValue(from, to);
		bean.setQuantity(BigDecimal.valueOf(Integer.parseInt(quantity)));
		bean.setTotalAmount(bean.getQuantity().multiply(bean.getConversionMultiple()));
		return bean;
	}

}
