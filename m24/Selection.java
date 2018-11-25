import java.util.Comparator;
import java.util.Scanner;
public class Selection {
    static void sort(int arr[]) { 
        int n = arr.length; 
        for (int i = 0; i < n-1; i++) {  
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        } 
    } 
    static void printArray(int arr[]) { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) {
            System.out.print(arr[i]+" "); 
        } 
        System.out.println();
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String[] array1 = a.split("", a.length());
        int[] array = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array[i] = Integer.parseInt(array1[i]);
        }
        Stopwatch s = new Stopwatch();
        Selection.sort(array);
        printArray(array);
        System.out.println(s.elapsedTime());
    }
}
