package com.mobisoft.library.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Administrator
 * 
 */
public class MD5Util {
	public static String base = "d19cf361181f5a169c107872e1f5b722";

	public static String getToken(long time) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update((base+time).getBytes());

			byte[] result = md.digest();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toHexString(result[i]>>>4&0xf));
				sb.append(Integer.toHexString(result[i]&0xf));
			}
			return sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static String makeMD5(String password) {   
		MessageDigest md;   
		   try {   
		    md = MessageDigest.getInstance("MD5");   
		    md.update(password.getBytes());   
		    String pwd = new BigInteger(1, md.digest()).toString(16);   
		    System.err.println(pwd);   
		    return pwd;   
		   } catch (Exception e) {   
		    e.printStackTrace();   
		   }   
		   return password;   
		}

}
