package com.coffeemaker.coffeemaker.models;
import lombok.Data;

@Data
public class Message {

	private boolean success;
	private String message;
	private String result;

	public Message(){
		
	}


}
