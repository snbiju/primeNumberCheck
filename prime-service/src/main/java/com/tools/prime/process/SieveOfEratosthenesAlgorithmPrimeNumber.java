package com.tools.prime.process;

import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenesAlgorithmPrimeNumber implements AlgorithmHandler {

    /**
     * The time complexity of the getPrimeSieveOfEratosthenes() method is O(n * log log n).
     * The outer for loop iterates from 2 to range, which takes O(n) time. The inner for loop iterates from 2 to the square root of i, which takes O(log log n) time. The total time complexity is O(n * log log n).
     * The space complexity of the getPrimeSieveOfEratosthenes() method is O(n).
     * The isPrime array stores all the prime numbers found, which can have a maximum size of n. Therefore, the space complexity is O(n).
     *
     * The outer for loop: O(n)
     * The inner for loop: O(log log n)
     * The isPrime check: O(1)
     * The overall time complexity is the product of the time complexities of each step, which is O(n * log log n).
     *
     */
    private List<Integer> getPrimeSieveOfEratosthenes(int range) {
        boolean[] isPrime = new boolean[range + 1];
        for (int i = 2; i <= range; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= range; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= range; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primeList = new ArrayList<>();
        for (int i = 2; i <= range; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
        }
        return primeList;
    }

    @Override
    public List<Integer> apply(int range) {
        return getPrimeSieveOfEratosthenes(range);
    }
}
