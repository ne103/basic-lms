package model;

import java.util.ArrayList;

public class Student {
    private final int id;
    private String name;
    private ArrayList<Subject> subjectList;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.subjectList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}