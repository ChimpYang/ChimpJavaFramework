package org.cjf.util;


import java.security.MessageDigest;

public class MD5 {    
    
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
        "a", "b", "c", "d", "e", "f"};    
    
//    123456 -> E10ADC3949BA59ABBE56E057F20F883E
//    abcd1234 -> E19D5CD5AF0378DA05F63F891C7467AF
//    sleep0 -> E19D5CD5AF0378DA05F63F891C7467AF
    public static void main(String[] args){  
    	String originalA = "123456";
    	String originalB = "abcd1234";
    	String originalC = "sleep0";
    	String md5A = MD5.encode(originalA);
    	String md5B = MD5.encode(originalB);
    	String md5C = MD5.encode(originalB);
    	
    	System.out.println(String.format("%s -> %s", originalA, md5A));
    	System.out.println(String.format("%s -> %s", originalB, md5B));
    	System.out.println(String.format("%s -> %s", originalC, md5C));
    } 
        
    public static boolean check(String md5, String original){    
    	if(null == md5 || null == original) {
    		return false;
    	}
    	
        if(md5.equals(encodeByMD5(original))){    
            return true;    
        } else{    
            return false;    
        }    
    }    
    
    public static String encode(String originString){  
        return encodeByMD5(originString);  
    }  
    
    private static String encodeByMD5(String originString){
        if (originString != null){    
            try{    
                MessageDigest md = MessageDigest.getInstance("MD5");    
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算    
                byte[] results = md.digest(originString.getBytes());    
                //将得到的字节数组变成字符串返回    
                String resultString = byteArrayToHexString(results);    
                String pass =  resultString.toUpperCase();    
                return pass;  
            } catch(Exception ex){     
                ex.printStackTrace();    
            }    
        }    
        return null;    
    }    
        
    
    private static String byteArrayToHexString(byte[] b){    
        StringBuffer resultSb = new StringBuffer();    
        for (int i = 0; i < b.length; i++){    
            resultSb.append(byteToHexString(b[i]));    
        }    
        return resultSb.toString();    
    }    
        
    private static String byteToHexString(byte b){    
        int n = b;    
        if (n < 0)    
            n = 256 + n;    
        int d1 = n / 16;    
        int d2 = n % 16;    
        return hexDigits[d1] + hexDigits[d2];    
    }    
    
}   