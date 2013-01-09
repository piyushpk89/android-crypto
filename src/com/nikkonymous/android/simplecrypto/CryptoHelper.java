package com.nikkonymous.android.simplecrypto;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoHelper {
	private IvParameterSpec ivspec;
    private SecretKeySpec keyspec;
    private Cipher cipher;
    byte[] encrypted, decrypted;
    
    /*Constructor*/
    public CryptoHelper(String SecretKey, String iv) {
    	ivspec = new IvParameterSpec(iv.getBytes());
        keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");
                       
        try {
        	cipher = Cipher.getInstance("AES/CBC/NoPadding");
        }catch (NoSuchAlgorithmException e){
            	e.printStackTrace();
        }catch (NoSuchPaddingException e){
                e.printStackTrace();
        }
    }
    
    public byte[] encrypt(String text) throws Exception {
    	cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        encrypted = cipher.doFinal(padStr(text).getBytes());             
        return encrypted;
    }
    
    public byte[] decrypt(String code) throws Exception {
    	cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);        
    	decrypted = cipher.doFinal(hexToBytes(code));
        return decrypted;
    }
    
    public static String bytesToHex(byte[] data) {                  
        int len = data.length;
        String str = "";
        int i = 0;
        do{
        	if ((data[i]&0xFF)<16)
            	str = str + "0" + java.lang.Integer.toHexString(data[i]&0xFF);
            else
                str = str + java.lang.Integer.toHexString(data[i]&0xFF);
        	i++;
        }while(i < len);      
        return str;
    }
                               
    public static byte[] hexToBytes(String str) {
		int len = str.length() / 2;
		byte[] buffer = new byte[len];
		int i = 0;
		do{
			buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
			i++;
		}while(i < len);
		return buffer;
    }
                
    private static String padStr(String str) {
    	char paddingChar = ' ';
        int size = 16;
        int x = str.length() % size;
        int padLength = size - x;
        int i = 0;
        do{
        	str += paddingChar;
        	i++;
        }while(i < padLength);
        
        return str;
    }
}
