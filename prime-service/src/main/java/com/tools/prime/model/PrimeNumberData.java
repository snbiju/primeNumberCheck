package com.tools.prime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrimeNumberData {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("Initial")
    int initial;
    @JsonProperty("Primes")
    List<Integer> primes;
}
