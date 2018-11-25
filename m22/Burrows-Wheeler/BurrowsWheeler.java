import java.util.*;
public class BurrowsWheeler {
    public static void transform() {
        String input = BinaryStdIn.readString();
        CircularSuffixArray csuffix = new CircularSuffixArray(input);
        for (int i = 0; i < csuffix.length(); i++) {
            if (csuffix.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }
        for (int i = 0; i < csuffix.length(); i++) {
            if (csuffix.index(i) == 0) {
                BinaryStdOut.write(input.charAt(input.length()-1));
            } else {
                BinaryStdOut.write(input.charAt(csuffix.index(i) - 1));
            }
        }
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
        int num = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] array = s.toCharArray();
        HashMap<Character, Queue<Integer>> dictionary = new HashMap<Character, Queue<Integer>>();
        for (int i = 0; i < array.length; i++) {
            if (!dictionary.containsKey(array[i])) {
                dictionary.put(array[i], new Queue<Integer>());
            } 
            dictionary.get(array[i]).enqueue(i);
        }
        Arrays.sort(array);
        int[] array1 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            array1[i] = (dictionary.get(array[i])).dequeue();
        }
        for (int i = 0; i < array1.length; i++) {
            BinaryStdOut.write(array[num]);
            num = array1[num];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }
    }
}
