package com.greetingapp.greetingController;

import com.greetingapp.greetingModel.Greeting;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController
{
    private static final String template ="Hello , %s!";
    private final AtomicLong counter = new AtomicLong();

    // curl localhost:8080/greeting
    @GetMapping("/greeting")
    public Greeting getGreeting() {
        return new Greeting(counter.incrementAndGet(), String.format(template, " "));
    }
    //curl -X GET "http://localhost:8080/greeting/param"
    //curl -X GET "http://localhost:8080/greeting/param?name=Sanket"
    @GetMapping("/greeting/param")
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name)
    {
        return new Greeting(counter.incrementAndGet(),String.format(template,name));

    }
    //curl  -X POST -H "Content-Type:application/json" -d "{\"content\": \"Sanket, More\" }" http://localhost:8080/greeting/post
    @PostMapping("/greeting/post")
    public Greeting postGreeting(@RequestBody Greeting greeting) {
        return new Greeting(counter.incrementAndGet(), String.format(template, greeting.getContent()));
    }

    //curl -X PUT "http://localhost:8080/put/greeting/?name=bhushan"
    @PutMapping("/greeting/put")
    public Greeting putGreeting(@RequestParam(value = "name") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
