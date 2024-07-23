package com.stackroute.exception;

public class AlreadyBookedSlotException extends RuntimeException {

	public AlreadyBookedSlotException(String message) {
		super(message);
	}
}
