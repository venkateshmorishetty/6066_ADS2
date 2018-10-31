import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * List of .
 *
 * @param      <Item>  The item
 */
public class Stack<Item> implements Iterable<Item> {
    /**.
     * { size }.
     */
    private int size;
    /**.
     * { first }
     */
    private Node first;
    /**.
     * Class for node.
     */
    private class Node {
        /**.
         * { item }
         */
        private Item item;
        /**.
         * { next }
         */
        private Node next;
    }
   /**
     * Create an empty stack.
     */
    public Stack() {
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
     * { push }
     *
     * @param      item  The item
     */
    public void push(final Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        size++;
    }
    /**.
     * { pop }
     *
     * @return     { item }
     */
    public Item pop() {
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }
    /**.
     * { peek }
     *
     * @return     { description_of_the_return_value }
     */
    public Item peek() {
        return first.item;
    }
    /**.
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString();
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
