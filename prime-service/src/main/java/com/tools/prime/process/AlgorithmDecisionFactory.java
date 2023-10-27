package com.tools.prime.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AlgorithmDecisionFactory {
    static Map<String, AlgorithmHandler> decisionMap = new HashMap<>();
    static {
        decisionMap.put(null, new TraditionalPrimeNumber());
        decisionMap.put("TR", new TraditionalPrimeNumber());
        decisionMap.put("SE", new SieveOfEratosthenesAlgorithmPrimeNumber());
        decisionMap.put("IR",new IterativePrimeNumber());
    }

    public static Optional<AlgorithmHandler> getOperation(String algorithm) {
        return Optional.ofNullable(decisionMap.get(algorithm));
    }
}
