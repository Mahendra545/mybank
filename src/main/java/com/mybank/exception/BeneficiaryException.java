package com.mybank.exception;

public class BeneficiaryException extends RuntimeException{
	
	 private static final long serialVersionUID = 1L;
	    public BeneficiaryException(){
	        super();
	    }
	    public BeneficiaryException(String message){
	        super(message);
	    }

}
