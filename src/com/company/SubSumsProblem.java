package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * interviewTraining
 * Created the 31/05/18 at 4:02 PM
 *
 * @author Bakari Nouhou
 */
public class SubSumsProblem {
    // Complete the getWays function below.
    private static long getWays(long sumToProduce, long[] coinsAvailable) {
        Arrays.sort(coinsAvailable);
        long count = 0;
        long[] max = new long[coinsAvailable.length];
        for (int i = 0; i < coinsAvailable.length; i++) {
            max[i] = sumToProduce / coinsAvailable[i];
        }
        for (int mainLoopCoin = coinsAvailable.length - 1; mainLoopCoin >= 0; mainLoopCoin--) {
            long currentVal = 0;
            StringBuilder comb = new StringBuilder();
            int innerLoopCoin = mainLoopCoin;
            for (int coinsMultiplier = 0; innerLoopCoin >= 0 && max[mainLoopCoin] > 0; coinsMultiplier++) {
                if (coinsMultiplier < max[innerLoopCoin]) {
                    currentVal += coinsAvailable[innerLoopCoin];
                    comb.append(coinsAvailable[innerLoopCoin]);
                } else {
                    innerLoopCoin--;
                    coinsMultiplier = -1;
                }
                if (currentVal == sumToProduce) {
                    count++;
                    System.out.println(comb);
                    if (max[mainLoopCoin] >= 0) {
                        max[mainLoopCoin]--;
                        coinsMultiplier = -1;
                        currentVal = 0;
                        comb.delete(0, comb.length());
                        innerLoopCoin = mainLoopCoin;
                    } else {
                        break;
                    }
                }
                if (currentVal > sumToProduce) {
                    currentVal -= coinsAvailable[innerLoopCoin];
                    comb.deleteCharAt(comb.length() - 1);
                    innerLoopCoin--;
                    coinsMultiplier = -1;
                }
            }
        }
        System.out.println(count);
        return count;
    }


    private static long getWaysOpt(int sumToProduce, long coinsAvailable[]) {
        //Time complexity of this function: O(mn)
        //Space Complexity of this function: O(n)

        // table[i] will be storing the number of solutions
        // for value i. We need n+1 rows as the table is
        // constructed in bottom up manner using the base
        // case (n = 0)
        long[] table = new long[sumToProduce + 1];

        // Initialize all table values as 0
        Arrays.fill(table, 0);   //O(n)

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (long aCoinsAvailable : coinsAvailable)
            for (long j = aCoinsAvailable; j <= sumToProduce; j++)
                table[(int) j] += table[(int) (j - aCoinsAvailable)];

        return table[sumToProduce];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.out"));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        long[] c = new long[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long cItem = Long.parseLong(cItems[i]);
            c[i] = cItem;
        }

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = getWaysOpt(n, c);
        bufferedWriter.write(String.valueOf(ways));
        bufferedWriter.close();

        scanner.close();
    }
}
