package org.microcks.exception;

public class ShowSimpleTextException extends Exception {

	private StringBuffer msg;
	
	public ShowSimpleTextException(StringBuffer msg) {
		this.msg = msg;
	}
	
	@Override
	public String getMessage() {
		return msg.toString();
	}
	
}
