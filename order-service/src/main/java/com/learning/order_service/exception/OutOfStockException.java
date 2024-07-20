package com.learning.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfStockException extends Exception {

	public OutOfStockException(String message) {
		super(message);
	}

}
