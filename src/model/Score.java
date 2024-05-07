package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Score {
//    private final int id;
    private final int subjectId;
    private final int studentId;
//    private int round;
    private ArrayList<Entry<Integer, String>> scores;

    public Score(Subject subject, int studentId, ArrayList<Entry<Integer, String>> scores) {
//        this.id = id;
        this.subjectId = subject.getId();
        this.studentId = studentId;
        this.scores = scores;
    }

//    public int getId() {
//        return id;
//    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public ArrayList<Entry<Integer, String>> getScores() {
        return scores;
    }

    /**/
    public void updateScores(int inputScore){
        String grade = scoreToGrade(inputScore);
        Map.Entry<Integer, String> entry = Map.entry(inputScore, grade);
        this.scores.add(entry);
    }

    /*
    * 등급 산정하는 함수
    * score가 변동이 있을때 실행되어야 합니다.*/
    private String scoreToGrade(int score){
        Subject subject = Subject.findById(subjectId);
        if (subject == null) {
            throw new IllegalArgumentException();
        }
        if (subject.isEssential()) {
            if (score >= 95){
                return "A";
            } else if(score >= 90){
                return "B";
            } else if (score >= 80) {
                return "C";
            } else if (score >= 70) {
                return "D";
            } else if (score >= 60) {
                return "F";
            } else {
                return "N";
            }
        } else {
            if (score >= 90){
                return "A";
            } else if(score >= 80){
                return "B";
            } else if (score >= 70) {
                return "C";
            } else if (score >= 60) {
                return "D";
            } else if (score >= 50) {
                return "F";
            } else {
                return "N";
            }
        }
    }

}

