import java.util.Scanner;
public class insertion { 
    static void sort(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=1; i<n; ++i) 
        { 
            int key = arr[i]; 
            int j = i-1; 
            while (j>=0 && arr[j] > key) 
            { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        } 
    } 
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i] + " "); 
  
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
        insertion.sort(array);
        printArray(array);
        System.out.println(s.elapsedTime());
    }
}
