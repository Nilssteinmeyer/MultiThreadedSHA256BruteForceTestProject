package PoolRunningGeneric;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool_0<T> implements ResourcePool<T>
{
	private List<T> pool;
	
	public ResourcePool_0(List<T> resources) 
	{
		this.pool = new ArrayList<>();
		for(T item : resources)
			this.pool.add(item);		
	}
	
	@Override
	public synchronized void release(T resource) 
	{
		this.pool.add(resource);
		notify();		
	}

	@Override
	public synchronized T require() 
	{
		while(this.pool.size() == 0)
		{
			try 
			{
				wait();
			} catch (InterruptedException e) 
			{

				e.printStackTrace();
			}
		}
		return this.pool.remove(this.pool.size() - 1);		
	}

	@Override
	public int size() {
		return this.pool.size();
	}
}
