package com.greetingapp.greetingService;

import com.greetingapp.greetingModel.Greeting;
import com.greetingapp.greetingModel.User;
import com.greetingapp.greetingRepository.GreetingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements  IGreetingService
{
    private static final String template ="Hello , %s!";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private GreetingRespository greetingRepository;

    @Override
    public Greeting addGreeting(User user) {
        String message = String.format(template,(user.toString().isEmpty()) ? "Hello World" : user.toString());
        return  greetingRepository.save(new Greeting(counter.incrementAndGet(), message));
    }

    @Override
    public Greeting getGreetingById(long id)
    {
        return greetingRepository.findById(id).get();
    }

    @Override
    public List<Greeting> getAllGreetings()
    {
        List<Greeting> greetings = new ArrayList<>();
        greetingRepository.findAll().forEach(greeting -> greetings.add(greeting));
        return greetings;
    }

    @Override
    public Optional<Greeting> editGreetingById(long id, String name) {
        Optional<Greeting> particularGreeting = greetingRepository.findById(id);
        particularGreeting.get().setContent(name);
        return particularGreeting;
    }
    @Override
    public String deleteGreeting(long id)
    {
        greetingRepository.deleteById(id);
        return "Greeting Deleted Succesfully";
    }

}
