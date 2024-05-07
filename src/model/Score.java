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
        this.grade = scoreToGrade(subjectId, score);
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
        this.grade = scoreToGrade(subjectId, score);
    }

    /*
    * 등급 산정하는 함수
    * score가 변동이 있을때 실행되어야 합니다.*/
    private String scoreToGrade(int subjectId, int score){
        Subject subject = Subject.findById(subjectId);
        if (subject == null) {
            throw new IllegalArgumentException();// 예외처리?
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

