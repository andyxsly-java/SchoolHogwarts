package ru.hogwarts.school.model;

public class Faculty {
    private long id;
    private String name;
    private String color;

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



