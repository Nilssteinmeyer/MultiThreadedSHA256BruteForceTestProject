package PoolRunningGeneric;
import java.util.Scanner;
import java.security.MessageDigest;

public class Main {

	public static void main(String[] args) 
	{		
		String knownSha;		
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter a Passwort to Brutefore: ");
		String n = reader.next(); 
		System.out.println("Enter Number of Threads you would like to work for you: ");
		int y = reader.nextInt(); 
		reader.close();
		
		// gets the Sha256 of the entered Password.
		knownSha = getSha256(n);
	
		InitializePool pool = new InitializePool(knownSha);
		
		for(int i = 0; i < y; i++)
		{
			pool.createThread("Thread " + i);			
		}		
		pool.beginWork();		
	}
	
	public static String getSha256(String value) {
	    try{
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(value.getBytes());
	        return bytesToHex(md.digest());
	    } catch(Exception ex){
	        throw new RuntimeException(ex);
	    }
	 }

 private static String bytesToHex(byte[] bytes) {
	    StringBuffer result = new StringBuffer();
	    for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    return result.toString();
	 }

}
