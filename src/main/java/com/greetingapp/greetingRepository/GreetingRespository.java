package com.greetingapp.greetingRepository;

import com.greetingapp.greetingModel.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRespository extends JpaRepository<Greeting,Long>
{
}
