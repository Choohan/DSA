package choohan.adt;

import java.util.Iterator;

public interface QueueInterface<T> {
	public void enqueue(T newEntry);
	
	public void enqueueArray(T[] newEntryArray);
	
	public T dequeue();
	
	public T getFront();
	
	public boolean replaceWith(int position, T newEntry);
	
	public boolean remove(int position);
	
	public boolean removeData(T entry);
	
	public boolean isEmpty();
	
	public int count();
	
	public int loc(T entry);
	
	public String toString();
	
	public void clear();
	
}
