import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for bag.
 *
 * @param      <Item>  The item
 */
public class Bag<Item> implements Iterable<Item> {
    /**.
     * { first }
     */
    private Node<Item> first;
    /**.
     * { n }
     */
    private int n;
    /**
     * Class for node.
     *
     * @param      <Item>  The item
     */
    private static class Node<Item> {
        /**.
         * { item }
         */
        private Item item;
        /**.
         * { next }
         */
        private Node<Item> next;
    }
    /**
     * Constructs the object.
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
    /**.
     * { size }
     *
     * @return     { size }
     */
    public int size() {
        return n;
    }
    /**.
     * { adds the item }
     *
     * @param      item  The item
     */
    public void add(final Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }
    /**.
     * { iterator }
     *
     * @return     { item }
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }
    /**
     * Class for list iterator.
     *
     * @param      <Item>  The item
     */
    private class ListIterator<Item> implements Iterator<Item> {
        /**.
         * { current }
         */
        private Node<Item> current;
        /**
         * Constructs the object.
         *
         * @param      f  The first
         */
        ListIterator(final Node<Item> f) {
            current = f;
        }
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
        /**.
         * { next }
         *
         * @return     { item }
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
