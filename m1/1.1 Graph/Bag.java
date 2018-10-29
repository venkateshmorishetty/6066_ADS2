import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**
     * { n }.
     */
    private int n;
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
        n = 0;
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
        return n;
    }
   /**.
    * { add }
    *
    * @param      item  The item
    */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**.
     * { iterator }
     *
     * @return    { list }
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
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext()  {
            return current != null;
        }
        /**.
         * { remove }
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * { next node }.
         *
         * @return     { return next address }
         */
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

