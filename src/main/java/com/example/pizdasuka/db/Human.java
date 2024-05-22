package com.example.pizdasuka.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
public class Human {
    private int id;

    private String name;

    private String last_name;

    public Human(String name, String last_name) {
        this.name = name;
        this.last_name = last_name;
    }

    public Human(int id, String name, String last_name) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
    }
}
