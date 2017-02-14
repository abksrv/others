import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int size = 0;
	private Node<Item> front = null, end = null;

	public RandomizedQueue() {
		// construct an empty randomized queue
	}

	public boolean isEmpty() {
		return size == 0;
		// is the queue empty?
	}

	public int size() {
		return size;
		// return the number of items on the queue
	}

	public void enqueue(Item item) {
		checkNullItem(item);
		Node<Item> node = new Node<Item>(item, null);
		if (front == null) {
			front = end = node;
		} else {
			end.next = node;
			end = node;
		}
		size++;
	}

	public Item dequeue() {
		checkIfNonEmpty();
		int random = StdRandom.uniform(size);
		Node<Item> node = front;
		Node<Item> prev = null;
		for (int i = 0; i < random; i++) {
			prev = node;
			node = node.next;
		}
		if (prev != null) {
			prev.next = node.next;
		}
		if (front == node) {
			front = front.next;
		}
		if (end == node) {
			end = prev;
		}
		size--;
		return node.item;
	}

	public Item sample() {
		checkIfNonEmpty();
		int random = StdRandom.uniform(size);
		Node<Item> node = front;
		for (int i = 0; i < random; i++) {
			node = node.next;
		}
		return node.item;
	}

	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
		// return an independent iterator over items in random order
	}


	private static class Node<Item> {
		Item item;
		Node<Item> next;

		public Node(Item item, Node<Item> next) {
			super();
			this.item = item;
			this.next = next;
		}
	}

	private static void checkNullItem(Object item) {
		if (item == null) {
			throw new NullPointerException();
		}
	}

	private void checkIfNonEmpty() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private Item[] ordering;
		private int currentIndex = 0;

		public RandomizedQueueIterator() {
			ordering = (Item[]) new Object[size];
			Node<Item> node = front;
			for (int i = 0; i < size; i++) {
				ordering[i] = node.item;
				node = node.next;
			}
			StdRandom.shuffle(ordering);
		}

		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public Item next() {
			if (currentIndex >= size)
				throw new NoSuchElementException();
			return ordering[currentIndex++];
		}

		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}
	
	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		queue.enqueue("A");
		queue.enqueue("B");
		queue.enqueue("C");
		queue.enqueue("E");
		queue.enqueue("D");
		String dequeue = queue.dequeue();
		System.out.println(dequeue);
		
		Iterator<String> itr = queue.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println("----");
		System.out.println(queue.sample());
		System.out.println(queue.sample());
		System.out.println(queue.sample());
		System.out.println(queue.sample());
		
		
	}
}