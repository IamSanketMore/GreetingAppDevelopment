package com.greetingapp.greetingService;

import com.greetingapp.greetingModel.Greeting;
import com.greetingapp.greetingModel.User;

import java.util.List;
import java.util.Optional;

public interface IGreetingService
{
    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);
    List<Greeting> getAllGreetings();

    Optional<Greeting> editGreetingById(long id, String name);
    String deleteGreeting(long id);
}
