package PoolRunningGeneric;

import java.util.ArrayList;
import java.util.List;

public class InitializePool 
{
	private ResourcePool_0<EualShaTest> resources;
	private List<EualShaTest> tests;
	private List<Thread> threads;
	private String name;
	private String knownSha;
	private char[] charset;
	public Boolean isFound = false;
	
	public InitializePool(String knownSha)
	{
		this.knownSha = knownSha;
		this.tests = new ArrayList<>();
		this.threads = new ArrayList<>();		
		this.tests.add(new EualShaTest(knownSha, "a")); //Pool füllen		
		this.resources = new ResourcePool_0<>(tests);
		this.charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	    this.charset = "abcdefghijklmnopqrstuvwxyzAEIOU0123456789!@#$%^&*()-_+=~`[]{}|:;<>,.?/BCDFGHJKLMNPQRSTVWXYZ".toCharArray();		
	}	

    public void generate(String str, int pos, int length) {
        if (length == 0) {
            resources.release(new EualShaTest(knownSha,str));
        } else { 
            if (pos != 0) {
                pos = 0;
            }
            for (int i = pos; i < charset.length; i++) {
                generate(str + charset[i], i, length - 1);
            }
        }
    }
	
	public void beginWork()			
	{
		// start all Threads 
		for(Thread threadItem : this.threads)
			threadItem.start();
		
		// Fill Pool with further combinations 		
	    for (int length = 1;  length < 10; length++) 
	        generate("", 0, length);
	}
	
	public void stopWork()
	{
		isFound = true;
	}
		
	public boolean createThread(String name)
	{
		this.threads.add(new Thread(new CustomThread(this.resources)));
		return true;
	}
	
	public String getName() {
		return this.name;
	}	
}
