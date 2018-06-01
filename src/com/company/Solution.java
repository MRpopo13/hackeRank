package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
//            long n = Long.parseLong(line);
            int n = Integer.parseInt(line);
            String result = fizzBuzzClassic(n);
//            System.out.println(result);
        }
    }


    private static long fiboClassic(long n) {
        if (n < 2) {
            return n;
        } else {
            return fiboClassic(n - 2) + fiboClassic(n - 1);
        }
    }

    private static String fibonacciPlus(long n) {
        double phi = (1 + Math.sqrt(5)) * 0.5;
        double invPhi = (-1 / phi);
        double result = (1 / Math.sqrt(5)) * (Math.pow(phi, n) - Math.pow(invPhi, n));
        return new BigDecimal(result).toBigInteger().toString();
    }

    private static String fiboIter(long n) {
        if (n < 2) {
            return String.valueOf(n);
        }
        BigInteger prev = BigInteger.ZERO, cur = BigInteger.ONE, next = BigInteger.ZERO;
        for (long i = 1; i < n; i++) {
            next = prev.add(cur);
            prev = cur;
            cur = next;
        }
        return next.toString();
    }

    private static String factorialIter(long n) {
        if (n < 2) {
            return String.valueOf(1);
        }
        BigInteger res = BigInteger.ONE;
        for (long i = 1; i <= n; i++) {
            res = res.multiply(new BigInteger(String.valueOf(i)));
        }
        return String.valueOf(res);
    }

    private static String fizzBuzzClassic(int n) {
        IntStream.rangeClosed(1, n).boxed().map(
                i -> {
                    if (i % 15 == 0) {
                        return "FizzBuzz";
                    } else if (i % 5 == 0) {
                        return "Buzz";
                    } else if (i % 3 == 0) {
                        return "Fizz";
                    } else {
                        return String.valueOf(i);
                    }
                }
        ).forEach(System.out::println);
        return "";
    }
}