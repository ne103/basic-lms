package model;

import java.util.ArrayList;

public class Student {
    private final int id;
    private String name;
    private Report report;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.report = new Report();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Report getReport() {
        return report;
    }

}