import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for lsd.
 */
class LSD {
    /**.
     * { sort using LSD alg. }
     *
     * @param      a     { array }
     * @param      w     { string len }
     */
    public void sort(final String[] a, final int w) {
        /**.
         * { size of array }
         */
        int n = a.length;
        /**.
         * { number of characters possible }
         */
        final int total = 256;
        /**.
         * { auxilary array }
         */
        String[] aux = new String[n];
        for (int d = w - 1; d >= 0; d--) {
            int[] count = new int[total + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < total; r++) {
                count[r + 1] += count[r];
            }
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //constructor
    }
    /**.
     * { main }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        String[] arr = new String[count];
        for (int i = 0; i < count; i++) {
            arr[i] = sc.nextLine();
        }
        LSD l = new LSD();
        l.sort(arr, arr[0].length());
        System.out.println(Arrays.toString(arr));
    }
}
