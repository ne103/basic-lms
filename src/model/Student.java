package model;

import java.util.ArrayList;

public class Student {
    private final int id;
    private String name;
    private ArrayList<Subject> subjectList;
    private Report report;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.subjectList = new ArrayList<>();
        this.report = new Report();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public Report getReport() {
        return report;
    }

    // 매개변수로 들어온 과목이 학생이 수강중인 과목인지 아닌지 판별합니다.
    public boolean hasSubject(Subject subject) {
        return subjectList.contains(subject);
    }
}