/* *****************************************************************************
 *  Name:              Vinicius Corbellini
 *  Last modified:     3/7/2021
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int n;          // size of the stack
    private Node<Item> first;     // beginning
    private Node<Item> last;      // end

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldfirst.prev = first;
            first.next = oldfirst;
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("null argument");
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        Node<Item> next = first.next;
        if (next != null) next.prev = null;
        first = next;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        Node<Item> prev = last.prev;
        if (prev != null) prev.next = null;
        last = prev;
        n--;
        if (isEmpty()) {
            last = null;
            first = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("unsupported operation");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> test = new Deque<>();
        test.addFirst("teste");
        StdOut.println(test.size());
        test.addFirst("ola");
        StdOut.println(test.size());
        test.addLast("tudo");
        StdOut.println(test.size());
        test.addLast("bem");
        StdOut.println(test.size());
        test.addLast("Sim");
        StdOut.println(test.size());
        StdOut.println(test.removeLast());
        StdOut.println(test.size());
        StdOut.println(test.removeFirst());
        StdOut.println(test.size());
        test.removeLast();
        StdOut.println(test.size());
        test.removeLast();
        StdOut.println(test.size());

        for (String s : test)
            StdOut.println(s);
    }
}
