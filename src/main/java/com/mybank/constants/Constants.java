package com.mybank.constants;

public class Constants {
private Constants() {
		
	}
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = (long) 5*60*60;
    public static final String SIGNING_KEY = "mybank";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
