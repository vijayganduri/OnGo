package com.vijayganduri.utils;

public class ValidatorUtils {


	/**
	 * TODO : ADD regex apattern or redirect request to apache lib
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email){
		if(email==null || email.length()==0){//TODO Add validation for IP
			return false;
		}else{
			return true;
		}
	}

	public static boolean isValidPassword(String password){
		if(password==null || password.length()==0){
			return false;
		}else{
			return true;
		}
	}

	
}
