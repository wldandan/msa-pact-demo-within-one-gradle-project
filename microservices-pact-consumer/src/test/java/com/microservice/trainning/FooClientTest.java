package com.microservice.trainning;

import au.com.dius.pact.consumer.*;
import au.com.dius.pact.model.PactFragment;
import com.microservice.trainning.gateway.FooClient;
import com.microservice.trainning.model.Foo;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FooClientTest {

    @Rule
    public PactRule rule = new PactRule("localhost", 8080, this);

    FooClient fooClient = new FooClient("http://localhost:8080");

    List<Foo> fooResults = Arrays.asList(new Foo(24), new Foo(50));


    @Pact(state="WhenFooListAvailable", provider="FooProvider", consumer="FooConsumer")
    public PactFragment createFooAvaliableFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request for Foos")
                .path("/foos")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("[{\"value\":24}, {\"value\":50}]").toFragment();
    }

    @Pact(state="WhenFooDetailAvailable", provider="FooProvider", consumer="FooConsumer")
    public PactFragment createFooNotAvaliableFragment(ConsumerPactBuilder.PactDslWithProvider.PactDslWithState builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.uponReceiving("a request for Foo with id 35")
                .path("/foos/35")
                .method("GET")

                .willRespondWith()
                .headers(headers)
                .status(200)
                .body("{\"value\":35}").toFragment();
    }

    @Test
    @PactVerification("WhenFooListAvailable")
    public void testGetAvaliableFoos() {
        assertEquals(fooClient.getFoos(),
                     Arrays.asList(new Foo(0), new Foo(24), new Foo(50), new Foo(100)));
    }

    @Test
    @PactVerification("WhenFooDetailAvailable")
    public void testGetAvaliableFoo() {
        TestCase.assertEquals(fooClient.getFoo("35"), new Foo(35));

    }
}