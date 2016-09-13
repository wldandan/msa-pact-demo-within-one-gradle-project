package com.microservice.trainning.gateway;

import com.microservice.trainning.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FooClient {

    private String url;
    private RestTemplate restTemplate;

    @Autowired
    public FooClient(@Value("${producer}") String url) {
        this.url = url;
        this.restTemplate = new RestTemplate();
    }

    private List<Foo> getResults() {
        ParameterizedTypeReference<List<Foo>> responseType = new ParameterizedTypeReference<List<Foo>>() {};
        return restTemplate.exchange(url + "/foos", HttpMethod.GET, null, responseType).getBody();
    }

    private Foo getFooResult(String id) {
        return restTemplate.getForObject(url + "/foos/" + id, Foo.class);
    }

    public Foo getFoo(String id) {
        return getFooResult(id);
    }

    public List<Foo> getFoos(){
        ArrayList<Foo> foos = new ArrayList<Foo>();
        Foo fooHeader = new Foo(0);
        Foo fooFooter = new Foo(100);

        foos.add(fooHeader);

        List<Foo> results = getResults();
        if (null!= results && !results.isEmpty()){
            foos.addAll(results);
        }

        foos.add(fooFooter);
        return foos;
    }
}
