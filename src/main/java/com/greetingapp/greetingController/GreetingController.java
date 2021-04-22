package com.greetingapp.greetingController;

import com.greetingapp.greetingModel.Greeting;
import com.greetingapp.greetingModel.User;
import com.greetingapp.greetingService.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController
{
    private static final String template ="Hello , %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private IGreetingService greetingService;
    User user = new User();
//--------------------For Save To Repository --------------------------
    @GetMapping(value = {"","/","/home"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        user.setFirstName(name);
        return greetingService.addGreeting(user);
    }
    @GetMapping("/getById/{id}")
    public Greeting getGreetingById(@PathVariable(value = "id")long id)
    {
        return greetingService.getGreetingById(id);
    }
    @GetMapping("/getAllGreetings")
    public List<Greeting> getAllGreeting() {
        return greetingService.getAllGreetings();
    }

    @PutMapping("/editGreeting/{id}")
    public Optional<Greeting> editGreetingById(@PathVariable("id") long id, @RequestParam(value = "name") String name) {
        return greetingService.editGreetingById(id, name);
    }
    @PostMapping("/post")
    public Greeting getRepoGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        user.setFirstName(name);
        return greetingService.addGreeting(user);
    }

    @DeleteMapping("/deleteGreeting/{id}")
    public String deletePerson(@PathVariable("id") long id) {
        greetingService.deleteGreeting(id);
        return "Greeting Deleted Successfully";
    }
//--------------------------------------------------------------------------

    //--------------For Just Check Http methods----------------------------------
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
