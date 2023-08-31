package com.tools.prime.controller;

import com.tools.prime.PrimeApplicationTests;
import com.tools.prime.model.PrimeNumberData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PrimeNumberControllerTest extends PrimeApplicationTests {


    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mvc;
    final String uri = "/primes/10";
    @BeforeEach
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListAsJsonWithNoAlgorithms() throws Exception {
        String json = "{\"Initial\":\"10\",\"Primes\":[2,3,5,7]}";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PrimeNumberData result = super.mapFromJson(content,PrimeNumberData.class);
        assertTrue(result.getPrimes().size()>0);
        assertEquals(json,content);
    }
    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListAsJsonWithTraditionalAlgorithms() throws Exception {
        String json = "{\"Initial\":\"10\",\"Primes\":[2,3,5,7]}";
        String uri = "/primes/10/TR";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PrimeNumberData result = super.mapFromJson(content,PrimeNumberData.class);
        assertTrue(result.getPrimes().size()>0);
        assertEquals(json,content);
    }
    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListAsJsonWithSieveOfEratosthenesAlgorithms() throws Exception {
        String json = "{\"Initial\":\"10\",\"Primes\":[2,3,5,7]}";
        String uri = "/primes/10/SE";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        PrimeNumberData result = super.mapFromJson(content,PrimeNumberData.class);
        assertTrue(result.getPrimes().size()>0);
        assertEquals(json,content);
    }
    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListAsXMLWithNoAlgorithms() throws Exception {
        String xml = "<PrimeNumberData><Initial>10</Initial><Primes><Primes>2</Primes><Primes>3</Primes><Primes>5</Primes><Primes>7</Primes></Primes></PrimeNumberData>";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String xmlString = mvcResult.getResponse().getContentAsString();

        PrimeNumberData result = xmlMapper().readValue(xmlString,PrimeNumberData.class);
        assertTrue(result.getPrimes().size()>0);
        assertEquals(200, status);
        assertEquals(xml,xmlString);
    }
    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListAsXMLWithTraditionalAlgorithms() throws Exception {
        String xml = "<PrimeNumberData><Initial>10</Initial><Primes><Primes>2</Primes><Primes>3</Primes><Primes>5</Primes><Primes>7</Primes></Primes></PrimeNumberData>";
        String uri = "/primes/10/TR";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String xmlString = mvcResult.getResponse().getContentAsString();

        PrimeNumberData result = xmlMapper().readValue(xmlString,PrimeNumberData.class);
        assertTrue(result.getPrimes().size()>0);
        assertEquals(200, status);
        assertEquals(xml,xmlString);
    }

    @Test
    void whenWeArePassingValidPrimeNumberRangeThenReturnPrimeNumberListAsXMLWithWithSieveOfEratosthenesAlgorithms() throws Exception {
        String xml = "<PrimeNumberData><Initial>10</Initial><Primes><Primes>2</Primes><Primes>3</Primes><Primes>5</Primes><Primes>7</Primes></Primes></PrimeNumberData>";
        String uri = "/primes/10/SE";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_XML_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String xmlString = mvcResult.getResponse().getContentAsString();

        PrimeNumberData result = xmlMapper().readValue(xmlString,PrimeNumberData.class);
        assertTrue(result.getPrimes().size()>0);
        assertEquals(200, status);
        assertEquals(xml,xmlString);
    }
    @Test
    void whenWeArePassingInValidPrimeNumberRangeThenReturnPrimeNumberRangeIsInvalid() throws Exception {
        String uri = "/primes/0";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertTrue(content.contains("Invalid Range: Please enter valid range"));
    }
    @Test
    void whenWeArePassingInValidPrimeNumberAlgorithmsThenReturnNotMatchingAlgorithm() throws Exception {
        String uri = "/primes/4/INVALID";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertTrue(content.contains("Algorithm is not match"));
    }
}