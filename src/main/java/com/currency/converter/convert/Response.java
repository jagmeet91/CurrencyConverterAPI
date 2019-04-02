package com.currency.converter.convert;

public class Response {

	String message;
	
	String Answer;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public Response(String message, String answer) {
		super();
		this.message = message;
		Answer = answer;
	}

}
