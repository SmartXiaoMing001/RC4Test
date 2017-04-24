package rc4;

import javax.crypto.Cipher;

public class RC
{
    public static String HloveyRC4(String aInput,String aKey) 
    { 
        int[] iS = new int[256]; 
        byte[] iK = new byte[256]; 
        
        for (int i=0;i<256;i++) 
            iS[i]=i; 
            
        int j = 1; 
        
        for (short i= 0;i<256;i++) 
        { 
            iK[i]=(byte)aKey.charAt((i % aKey.length())); 
        } 
        
        j=0; 
        
        for (int i=0;i<255;i++) 
        { 
            j=(j+iS[i]+iK[i]) % 256; 
            int temp = iS[i]; 
            iS[i]=iS[j]; 
            iS[j]=temp; 
        } 
    
    
        int i=0; 
        j=0; 
        char[] iInputChar = aInput.toCharArray(); 
        char[] iOutputChar = new char[iInputChar.length]; 
        for(short x = 0;x<iInputChar.length;x++) 
        { 
            i = (i+1) % 256; 
            j = (j+iS[i]) % 256; 
            int temp = iS[i]; 
            iS[i]=iS[j]; 
            iS[j]=temp; 
            int t = (iS[i]+(iS[j] % 256)) % 256; 
            int iY = iS[t]; 
            char iCY = (char)iY; 
            iOutputChar[x] =(char)( iInputChar[x] ^ iCY) ;    
        } 
        return new String(iOutputChar);      
    }
    
    public static String hloveyRC4(String aInput,String aKey) 
    { 
        int[] iS = new int[256]; 
        byte[] iK = new byte[256]; 
        
        for (int i=0;i<256;i++) 
            iS[i]=i; 
            
        int j = 1; 
        
        for (short i= 0;i<256;i++) 
        { 
            iK[i]=(byte)aKey.charAt((i % aKey.length())); 
        } 
        
        j=0; 
        
        for (int i=0;i<255;i++) 
        { 
            j=(j+iS[i]+iK[i]) % 256; 
            int temp = iS[i]; 
            iS[i]=iS[j]; 
            iS[j]=temp; 
        } 
    
    
        int i=0; 
        j=0; 
        char[] iInputChar = aInput.toCharArray(); 
        char[] iOutputChar = new char[iInputChar.length]; 
        for(short x = 0;x<iInputChar.length;x++) 
        { 
            i = (i+1) % 256; 
            j = (j+iS[i]) % 256; 
            int temp = iS[i]; 
            iS[i]=iS[j]; 
            iS[j]=temp; 
            int t = (iS[i]+(iS[j] % 256)) % 256; 
            int iY = iS[t]; 
            char iCY = (char)iY; 
            iOutputChar[x] =(char)( iInputChar[x] ^ iCY) ;    
        } 
        return new String(iOutputChar);      
    }
    
    /**
     * 秘钥
     * 明文->密文
     * 密文->明文
     * 
     * */
    public static void main(String[] args) {  
    	
        String inputStr = "梯~!@#$%^&*(ydsfhHAJDNCN':?><:子网@91waijiao";    
        String key = "dsafdsafda`123";   
        
        //
        String str = HloveyRC4(inputStr,key);   
        System.out.println("加密:"+str);  
        
        //
        String str1 = HloveyRC4(str,key);  
        System.out.println("解密:"+str1);  
    }
    
    public static void test(byte[] bytA, byte[] bytAB){
    	String s1 = "fs123fdsa";//String变量 
    	byte b1[] = s1.getBytes();//String转换为byte[] 
    	
    	String s2 = "fs123fdsadddsfsdfsdafsafs";
    	byte b2[] = s2.getBytes();//String转换为byte[] 
    	
    	byte b3[] = null;
    	
    	
    }
    
//    public static byte[] encryptByRC4(byte[] bytP, String bytKey) throws Exception {  
//    	
//    	
//        DESKeySpec desKS = new DESKeySpec(bytKey);  
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
//        SecretKey sk = skf.generateSecret(desKS);  
//        Cipher cip = Cipher.getInstance("DES");  
//        
//        cip.init(Cipher.ENCRYPT_MODE, sk);  
//        return cip.doFinal(bytP);  
//    }
} 
