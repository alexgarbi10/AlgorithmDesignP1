/**
 * Scheduling
 * @author Alejandro Garbi, 08-10398
 * @author David Lilue,
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P3 {
    
    /**
     * Sort an array using merge sort.
     * @param array the array of values to be sorted.
     * @param start the start of the sorting region.
     * @param end the end of the sorting region.
     */
    public static void mergeSort(int array[][], int start, int end) {
        int middle;
        int left;
        int right;
        int temps;
        int tempf;

        if (start < end) {
            // Split array in half
            middle = (start + end) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);

            // Merge both arrays
            left = start;
            right = middle + 1;

            while (left <= middle && right <= end) {
                if (array[left][1] > array[right][1]) {
                    temps = array[right][0];
                    tempf = array[right][1];
                    
                    for (int i=right-1; i>=left; i--) {
                        array[i+1][0] = array[i][0];
                        array[i+1][1] = array[i][1];
                    }

                    array[left][0] = temps;
                    array[left][1] = tempf;

                    right++;
                    middle++;
                }

                left++;
            }
        }
    }
    
    /**
     * Print all elements in an array.
     * @param array the array of values to be printed.
     */
    public static void printArray(int array[]) {        
        for (int element : array) {
            System.out.println(element);
        }
    }

    /**
     * Print all elements in a matrix.
     * @param array the array of values to be printed.
     */
    public static void printTimes(int array[][]) {
        int index = 0;
        
        for (int[] element : array) {
            System.out.println("Elemento " + index);
            System.out.print("ts: " + element[0] + " ");
            System.out.println("tf: " + element[1] + " ");
            index++;
        }
    }
    
    /**
     * Select the max size of activities to be executed.
     * @param array the array of values to select.
     *        array[][0] -> s[] (start times).
     *        array[][1] -> f[] (finish times).
     * @param n the array size.
     * @return integer.
     */
    public static int greedySelector(int array[][], int n) {
        int answer = 1;
        int k = 0;
                
        for (int i=1; i < n; i++) {
            if (array[i][0] >= array[k][1]) {
                answer++;
                k = i;
            }
        }
        
        return answer;
    }

    /**
     * Main method.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        try {
            // Parse instance
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            int m, n;
            int count = 0;
            
            line = br.readLine();
            m = Integer.parseInt(line);
            
            if (m < 0) {
                System.out.println("Error: El numero de empleados debe ser positivo.");
                System.exit(1);
            }
            
            int[] result = new int[m];
            
            // Check every employee instance
            while (count < m) {
                line = br.readLine();
                n = Integer.parseInt(line);
                
                if (n < 0) {
                    System.out.println("Error: El numero de actividades debe ser positivo.");
                    System.exit(1);
                }
                
                // times = s[] and f[]
                int [][] times = new int[n][2];
                int index = 0;
                
                // Parse activities
                while (index < n) {
                    line = br.readLine();
                    String[] sf = line.split(" ");
                    int x = Integer.parseInt(sf[0]);
                    int y = Integer.parseInt(sf[1]);
                    
                    if (x > y) {
                        System.out.println("Error: El tiempo de inicio debe ser menor que el de finalizacion.");
                        System.exit(1);
                    }
                    
                    times[index][0] = Integer.parseInt(sf[0]);
                    times[index][1] = Integer.parseInt(sf[1]);
                    index++;
                }
                
                // Sort
                mergeSort(times, 0, n-1);
                
                // GreedySelector                
                result[count] = greedySelector(times, n);
                count++;
            }
            
            printArray(result);
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error: No ha podido procesarse el archivo.");
        }
    }
}