
package PoolRunningGeneric;


public interface ResourcePool<T> {

	void release(T resource);
	T require();	
	int size();
}
