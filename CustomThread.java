package PoolRunningGeneric;

public class CustomThread implements Runnable 
{
	private ResourcePool_0<EualShaTest> resources;
	private Boolean isRunning = true;;
	
	public CustomThread(ResourcePool_0<EualShaTest> resources) 
	{
		this.resources = resources;
	}		
	
	@Override
	public void run() 
	{ 		
		while(isRunning)
		{
			EualShaTest test = this.resources.require();
			// start
			try {
				test.start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 			
		}		
	}
	public void stop()
	{
		isRunning = false;
	}
}
