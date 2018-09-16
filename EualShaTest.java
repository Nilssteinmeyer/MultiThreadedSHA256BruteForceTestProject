package PoolRunningGeneric;

import java.security.MessageDigest;

public class EualShaTest
{
	private boolean running;
	private Boolean equaler;
	private String knownSha;
	private String knownString;
	
	public EualShaTest(String knownSha, String knownString)
	{
		this.running = false;
		this.knownSha = knownSha;
		this.knownString = knownString;
	}
	
	 public static String getSha256(String value) 
	 {
		 try
		 {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
		    md.update(value.getBytes());
		    return bytesToHex(md.digest());
		 } 	catch(Exception ex)
		 {
			 throw new RuntimeException(ex);
		 }
	 }

	 private static String bytesToHex(byte[] bytes) {
		    StringBuffer result = new StringBuffer();
		    for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		    return result.toString();
		 }
	
	private Boolean equalShaString(String knownSha, String unknownSha)
	{
		if( knownSha.equals( getSha256(unknownSha)))
			return true;
		else
			return false;
	}
	
	public void start() throws InterruptedException
	{
		this.running = true;	
		this.equaler = false;
		equaler = equalShaString(this.knownSha,this.knownString);
		
		if(equaler)
		{
			System.out.println("Hash wurde gefunden! \n");
			System.out.println("String  ist:");
			System.out.println(knownString);			
			System.out.println("Hash ist:");
			System.out.println(knownSha);

			Runtime.getRuntime().exit(0);
		}			
	}
	
	public void stop()
	{
		this.running = false;
	}
	
	public boolean isRunning(){
		return this.running;
	}
	
}
