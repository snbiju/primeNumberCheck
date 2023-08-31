package com.tools.prime.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.tools.prime.exception.InvalidPrimeNumberRangeException;
import com.tools.prime.exception.NotMatchingAlgorithmExceptiion;
import com.tools.prime.process.Operation;
import com.tools.prime.process.OperatorFactory;
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

    public List<Integer> getPrimeNumbers(String range, String algo) throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {

        return generatePrimeNumbers(Integer.valueOf(range), algo);
    }

    private List<Integer> generatePrimeNumbers(int range, String algorithms) throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmExceptiion {
        if (range < 1) throw new InvalidPrimeNumberRangeException("Invalid Range: Please enter valid range");
        List<Integer> primeList = null;
        if (algorithms == null) {
            String cacheKey = "TR" + range;
            primeList = cache.getIfPresent(cacheKey);
        } else if (!OperatorFactory.getOperation(algorithms).isPresent()) {
            LOGGER.info("Algorithm {} is not match", algorithms);
            throw new NotMatchingAlgorithmExceptiion("Algorithm is not match");
        } else {
            primeList = cache.getIfPresent(algorithms + range);
        }
        if ((primeList == null || primeList.isEmpty()) && OperatorFactory.getOperation(algorithms).isPresent()) {
            final Operation operation = OperatorFactory.getOperation(algorithms).get();
            if (operation != null) {
                primeList = OperatorFactory.getOperation(algorithms).get().apply(range);
                cache.put(algorithms + range, primeList);
            }
        }

     return primeList;
    }
}
