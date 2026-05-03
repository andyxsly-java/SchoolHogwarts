package ru.hogwarts.school.model;

import jakarta.persistence.*;

@Entity
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private Faculty faculties;

    public Faculty (long id, String name, String colour) {
        this.id = id;
        this.name = name;
        this.color = colour;
    }

    public long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor() {
        this.color = color;
    }
}



