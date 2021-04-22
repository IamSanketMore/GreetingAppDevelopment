package com.greetingapp.greetingService;

import com.greetingapp.greetingModel.Greeting;
import com.greetingapp.greetingModel.User;

import java.util.List;

public interface IGreetingService
{
    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);
    List<Greeting> getAllGreetings();
}
