import java.util.*;
public class MoveToFront {
    static int R = 256;
    public static void encode() {
        String s = BinaryStdIn.readString();
        char[] array = s.toCharArray();
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        for (int i = 0; i < R; i++) {
            numbers.add(i);
        }
        for (int j = 0; j < array.length; j++) {
            int index = numbers.indexOf((int) array[j]);
            BinaryStdOut.write((char) index, 8);
            int temp = numbers.remove(index);
            numbers.add(0, temp);
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        String s1 = BinaryStdIn.readString();
        char[] array1 = s1.toCharArray();
        LinkedList<Integer> numbers1 = new LinkedList<Integer>();
        for (int k = 0; k < R; k++) {
            numbers1.add(k);
        }
        for (int i = 0; i< array1.length; i++) {
            int temp1 = numbers1.remove((int) array1[i]);
            numbers1.add(0, temp1);
            BinaryStdOut.write((char) temp1, 8);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        if (args[0].equals("-")) {
            encode();
        }
        if (args[0].equals("+")) {
            decode();
        }
    }
}