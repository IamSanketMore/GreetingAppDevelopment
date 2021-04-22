package com.greetingapp.greetingService;

import com.greetingapp.greetingModel.Greeting;
import com.greetingapp.greetingModel.User;

public interface IGreetingService
{
    Greeting addGreeting(User user);
    Greeting getGreetingById(long id);
}
