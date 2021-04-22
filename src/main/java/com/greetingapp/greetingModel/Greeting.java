package com.greetingapp.greetingModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "greetings")
public class Greeting
{
    @Id
    private final long id;
    private final String content;

    public Greeting()
    {
        id = 0;
        content = " ";
    }

    public Greeting(long id, String content)
    {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
