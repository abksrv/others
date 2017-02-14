import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> front = null, end = null;
	private int size;

	public Deque() {
		// construct an empty deque
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		checkNullItem(item);
		Node<Item> node = new Node<Item>(item, null, front);
		if (front != null)
			front.head = node;
		if (isEmpty())
			end = front = node;
		else
			front = node;
		size++;
	}

	public void addLast(Item item) {
		checkNullItem(item);
		Node<Item> node = new Node<Item>(item, end, null);
		if (end != null) {
			end.tail = node;
		}
		if (isEmpty())
			end = front = node;
		else
			end = node;
		size++;
	}

	public Item removeFirst() {
		checkIfNonEmpty();
		Node<Item> temp = front;
		front = front.tail;
		if (front == null)
			front = end = null;
		size--;
		return temp.item;
	}

	public Item removeLast() {
		checkIfNonEmpty();
		Node<Item> temp = end;
		end = end.head;
		if (end == null)
			end = front = null;
		size--;
		return temp.item;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node<Item> currentItem = front;

		@Override
		public boolean hasNext() {
			if (currentItem == null) {
				return false;
			}
			return true;
		}

		@Override
		public Item next() {
			if (currentItem == null)
				throw new NoSuchElementException();
			Node<Item> temp = currentItem;
			currentItem = temp.tail;
			return temp.item;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove");
		}
	}

	private static class Node<Item> {
		Item item;
		Node<Item> head, tail;

		public Node(Item item, Node<Item> head, Node<Item> tail) {
			super();
			this.item = item;
			this.head = head;
			this.tail = tail;
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

	public static void main(String[] args) {
		Deque<String> dq = new Deque<String>();
		dq.addFirst("C");
		dq.addFirst("B");
		dq.removeFirst();
		dq.removeFirst();
		
		Iterator<String> itr = dq.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}