package com.greetingapp.greetingController;

import com.greetingapp.greetingModel.Greeting;
import com.greetingapp.greetingModel.User;
import com.greetingapp.greetingService.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController
{
    private static final String template ="Hello , %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private IGreetingService greetingService;


    @GetMapping(value = {"","/","/home"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        User user = new User();
        user.setFirstName(name);
        return greetingService.addGreeting(user);
    }
    @GetMapping("/getById/{id}")
    public Greeting getGreetingById(@PathVariable(value = "id")long id)
    {
        return greetingService.getGreetingById(id);
    }
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
