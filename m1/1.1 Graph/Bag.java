import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * { N }.
     */
    private int N;
    /**
     * { first }.
     */
    private Node first;
    /**
     * Class for node.
     */
    private class Node {
        /**.
         * { item }
         */
        private Item item;
        /**.
         * { next node address }
         */
        private Node next;
    }
   /**
     * Create an empty stack.
     */
    public Bag() {
        first = null;
        N = 0;
    }
    /**
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return first == null;
    }
    /**
     * { size function }.
     *
     * @return     { size }
     */
    public int size() {
        return N;
    }

   /**
     * Add the item to the bag.
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    /**.
     * { iterator }
     *
     * @return     { list }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();  
    }
    /**.
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**
         * { current node }.
         */
        private Node current = first;
        public boolean hasNext()  { 
            return current != null; 
        }
        public void remove() {
            throw new UnsupportedOperationException(); 
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
