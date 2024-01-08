package com.tools.prime.controller;

import com.tools.prime.exception.InvalidPrimeNumberRangeException;
import com.tools.prime.exception.NotMatchingAlgorithmException;
import com.tools.prime.model.PrimeNumberData;
import com.tools.prime.service.PrimeNumberService;
import jakarta.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/primes")
public class PrimeNumberController {

    public static final Logger log = LoggerFactory.getLogger(PrimeNumberController.class);
    @Autowired
    PrimeNumberService service;
    @GetMapping(value = {"{range}", "{range}/{algo}",}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PrimeNumberData> getPrimeNumber(
            @PathVariable int range, @Nullable @PathVariable String algo)
            throws InvalidPrimeNumberRangeException, NotMatchingAlgorithmException {

        return new ResponseEntity<>(PrimeNumberData.builder()
                .initial(range)
                .primes(service.getPrimeNumbers(range, algo))
                .build(), HttpStatus.OK);

    }

}
