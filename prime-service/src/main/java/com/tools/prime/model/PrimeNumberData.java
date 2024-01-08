package com.tools.prime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrimeNumberData {
    @JsonProperty("Initial")
    int initial;
    @JsonProperty("Primes")
    List<Integer> primes;
}
