package com.mytutorials.rest.webservices.helloworld;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, path = "/hello-bitches")
    public String helloWorld() {
        return "Hello Bitches";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/hello-bitches-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello Bitches");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello-bitches/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello Bitches from %s", name));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hello-bitches/exception")
    public HelloWorldBean helloWorldThrowsException() {
        throw new RuntimeException("You fucked up !");
    }
}
