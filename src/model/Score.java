package model;

public class Score {
    private final int id;
    private final int subjectId;
    private final int studentId;
    private int round;
    private int score;
    private String grade;

    public Score(int id, Subject subject, int studentId, int round, int score) {
        this.id = id;
        this.subjectId = subject.getId();
        this.studentId = studentId;
        this.round = round;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

