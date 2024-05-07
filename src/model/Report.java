package model;

import java.util.HashMap;
import java.util.Map;

public class Report {
    private Map<Subject, Score> report;

    public Report() {
        this.report = new HashMap<>();
    }

    public Map<Subject, Score> getReport() {
        return report;
    }

    public void updateReport(Subject subject, Score score) {
        this.report.put(subject, score);
    }

    public void printReport() {
        this.report.forEach((subject, score) -> {
            System.out.println(subject.name() + ":");
//            printScore() // To be implemented
        });
    }
}
