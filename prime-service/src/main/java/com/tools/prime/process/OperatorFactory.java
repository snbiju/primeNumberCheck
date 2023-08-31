package com.tools.prime.process;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put(null, new TraditionalPrimeNumber());
        operationMap.put("TR", new TraditionalPrimeNumber());
        operationMap.put("SE", new SieveOfEratosthenesAlgorithmPrimeNumber());
        operationMap.put("IR",new IterativePrimeNumber());
    }

    public static Optional<Operation> getOperation(String operation) {
        return Optional.ofNullable(operationMap.get(operation));
    }
}
