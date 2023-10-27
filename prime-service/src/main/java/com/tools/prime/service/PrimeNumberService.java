package com.tools.prime.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.tools.prime.exception.InvalidPrimeNumberRangeException;
import com.tools.prime.exception.NotMatchingAlgorithmException;
import com.tools.prime.process.AlgorithmHandler;
import com.tools.prime.process.AlgorithmDecisionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimeNumberService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PrimeNumberService.class);

    @Qualifier("caffeine")
    private Cache<String, List<Integer>> cache;

    @Autowired
    public PrimeNumberService(Cache<String, List<Integer>> cache) {
        this.cache = cache;
    }

    public List<Integer> getPrimeNumbers(int range, String algo) throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmException {

        return generatePrimeNumbers(range, algo);
    }

    private List<Integer> generatePrimeNumbers(int range, String algorithm) throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmException {
        if (range < 1) {
            throw new InvalidPrimeNumberRangeException("Invalid Range: Please enter a valid range");
        }

        List<Integer> primeList = retrieveFromCache(range, algorithm);

        if (primeList == null && isValidAlgorithm(algorithm)) {
            final AlgorithmHandler algorithmHandler = AlgorithmDecisionFactory.getOperation(algorithm).get();
            primeList = algorithmHandler.apply(range);
            cache.put(algorithm + range, primeList);
        }

        return primeList;
    }

    private List<Integer> retrieveFromCache(int range, String algorithm) {
        if (algorithm == null) {
            String cacheKey = "TR" + range;
            return cache.getIfPresent(cacheKey);
        } else {
            return cache.getIfPresent(algorithm + range);
        }
    }

    private boolean isValidAlgorithm(String algorithm) throws NotMatchingAlgorithmException {
        if (algorithm == null) {
            return true; // No specific algorithm, proceed with default
        } else if (!AlgorithmDecisionFactory.getOperation(algorithm).isPresent()) {
            LOGGER.info("Algorithm {} does not a match", algorithm);
            throw new NotMatchingAlgorithmException("Algorithm does not match.");
        } else {
            return true; // Algorithm is valid
        }
    }

}
