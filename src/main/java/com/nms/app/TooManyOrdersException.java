package com.nms.app;

public class TooManyOrdersException extends Exception{
	
	public TooManyOrdersException(){}
	
	public TooManyOrdersException(String message)
    {
       super(message);
    }
}


