package choohan.adt;

import java.util.Iterator;

public class CircularLinkedQueue<T> implements QueueInterface<T>{
	private Node lastNode = null;
	
	public CircularLinkedQueue() {
	}
	@Override
	public void enqueue(T newEntry) {
		Node newNode = new Node(newEntry, null);
		if(this.lastNode == null) {
			this.lastNode = newNode;
			this.lastNode.next = lastNode;
		}else {
			newNode.next = lastNode.next;
			lastNode.next = newNode;
			lastNode = newNode;
		}
	}

	@Override
	public void enqueueArray(T[] newEntryArray) {
		for (int i = 0; i < newEntryArray.length; i++) {
			Node newNode = new Node(newEntryArray[i], null);
			if(this.lastNode == null) {
				this.lastNode = newNode;
				this.lastNode.next = lastNode;
			}else {
				newNode.next = lastNode.next;
				lastNode.next = newNode;
				lastNode = newNode;
			}
		}
	}

	@Override
	public T dequeue() {
		if(lastNode == null) {
			return null;
		}else {
			Node frontNode = lastNode.next;
			if(lastNode.next.equals(lastNode)) {
				lastNode = null;
			}else {
				lastNode.next = lastNode.next.next;
			}
			return frontNode.data;
		}
	}

	@Override
	public T getFront() {
		if(lastNode == null) {
			return null;
		}else {
			Node frontNode = lastNode.next;
			return frontNode.data;
		}
	}

	@Override
	public boolean replaceWith(int position, T newEntry) {
		
		Node currentNode = null;
		boolean metLast = false;
		for(int i = 1; i <= position; i++) {
			currentNode = this.lastNode.next;
			if(metLast) {
				return false;
			}
			if (currentNode.equals(this.lastNode)) {
				metLast = true;
			}
			if(i == position) {
				currentNode.data = newEntry;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int position) {
		
		Node currentNode = null;
		boolean metLast = false;
		for(int i = 1; i <= position; i++) {
			currentNode = this.lastNode.next;
			if(metLast) {
				return false;
			}
			if (currentNode.equals(this.lastNode)) {
				metLast = true;
			}
			if(i == (position - 1)) {
				currentNode.next = currentNode.next.next;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean removeData(T entry) {
		Node currentNode = null;
		boolean metLast = false;
		do {

			currentNode = this.lastNode.next;
			if (currentNode.equals(this.lastNode)) {
				metLast = true;
			}
			if(currentNode.data.equals(entry)) {
				if(currentNode.next.equals(currentNode)) {
					this.lastNode = null;
				}else {
					currentNode.next = currentNode.next.next;
				}
			}
			
		}while(!metLast);
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (this.lastNode == null);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub

		Node currentNode = null;
		boolean metLast = false;
		int count = 0;
		do {

			currentNode = this.lastNode.next;
			if (currentNode.equals(this.lastNode)) {
				metLast = true;
			}
			count++;
			
		}while(!metLast);
		return count;
	}

	@Override
	public int loc(T entry) {
		Node currentNode = null;
		boolean metLast = false;
		int count = 0;
		do {

			currentNode = this.lastNode.next;
			if (currentNode.equals(this.lastNode)) {
				metLast = true;
			}
			count++;
			if(currentNode.data.equals(entry)) {
				return count;
			}
			
		}while(!metLast);
		return -1;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.lastNode = null;
	}
	
	private class Node {

	    private T data; 
	    private Node next; 

	    private Node(T data) {
	      this.data = data;
	      this.next = null;
	    } 

	    private Node(T data, Node next) {
	      this.data = data;
	      this.next = next;
	    } 
	} 

}
