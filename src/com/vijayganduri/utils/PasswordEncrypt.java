package com.vijayganduri.utils;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;



public class PasswordEncrypt {

	public static String encrypt(String paramString)
	{
		String str1 = paramString + "|";
		SecretKeySpec localSecretKeySpec = new SecretKeySpec("PRODKEYPRODKEY12".getBytes(), "AES");
		try
		{
			Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			localCipher.init(1, localSecretKeySpec);
			byte[] arrayOfByte = localCipher.doFinal(str1.getBytes());
			String str2 = new String(new Base64().encode(arrayOfByte));
			return str2;
		}
		catch (Exception localException)
		{
			localException.printStackTrace();
		}
		return null;
	}

}
