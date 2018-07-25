package program;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int T = sc.nextInt();
//        for(int i = 0; i < T; i++)
//        {
//            int k = sc.nextInt();
//            int n = sc.nextInt();
//            int[] arr = new int[n];
//
//            for(int j = 0; j < n; j++)
//            {
//                arr[j] = sc.nextInt();
//            }
            int[] arr = {189, 107, 444, 400, 84, 270, 225, 334, 410, 433, 249, 193, 487, 312, 493, 430, 422, 208, 90, 245, 337, 234, 168, 360};
            System.out.println(getMinPartition(14, 24, arr));
//        }
    }

    public static int getMinPartition(int k, int n, int[] arr)
    {
        if (k >= n)
        {
            int max = Integer.MIN_VALUE;

            for(int i = 0; i < arr.length; i++)
            {
                max = arr[i] > max ? arr[i] : max;
            }

            return max;
        }

        return DPMinPartition(k, arr);

    }

    private static int DPMinPartition(int k, int[] arr) {
        // k x n matrix
        int[][] M = new int[k + 1][arr.length];


        // base case
        // one worker must take all the jobs left
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            count += arr[i];
            M[1][i] = count;
        }
        // fill the table
        for(int i = 2; i <= k; i++)
        {
            for(int j = i - 1; j < arr.length; j++)
            {
                int numOfOptions = j - (i - 2);
                count = 0;
                int currentPartition = 0;
                int bestPartition = Integer.MAX_VALUE;
                for(int m = numOfOptions + i - 2; m > (i - 2); m--)
                {
                    count += arr[m];
                    currentPartition = Math.max(M[i - 1][m - 1], count);
                    bestPartition =  currentPartition < bestPartition ? currentPartition: bestPartition;
                }

                M[i][j] = bestPartition;
            }
        }

        return M[k][arr.length-1];
    }
}
