import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * { size }
     */
    private int size;
    /**.
     * { first }
     */
    private Node first;
    /**
     * Class for node.
     */
    private class Node {
        /**.
         * { data }
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
        size = 0;
    }
   /**.
    * Determines if empty.
    *
    * @return     True if empty, False otherwise.
    */
    public boolean isEmpty() {
        return first == null;
    }
   /**.
    * { size }
    *
    * @return     { size }
    */
    public int size() {
        return size;
    }
    /**.
     * { adds item }
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }
    /**.
     * { iterator }
     *
     * @return     { list }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }
    /**
     * Class for list iterator.
     */
    private class ListIterator implements Iterator<Item> {
        /**.
         * { current node }
         */
        private Node current = first;
        /**
         * Determines if it has next.
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }
        /**.
         * { remove }
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * { next }.
         *
         * @return     { return current value }
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
