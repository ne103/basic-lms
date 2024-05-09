package repository;

import model.Score;
import model.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class ScoreRepository {
    private static HashMap<Integer, ArrayList<Score>> store = new HashMap<>();
    private static int index = 0;
    /*
     * create
     * 학생id, 과목, 회차, 점수를 받아서 store에 추가합니다.
     * */
    public void create(int studentId, Subject subject, int round, int score){
        Score scoreInstance = new Score(++index, subject, studentId, round, score);
        ArrayList<Score> scores = store.get(studentId);
        if(scores == null){
            ArrayList<Score> tempScores = new ArrayList<>();
            tempScores.add(scoreInstance);
            store.put(studentId, tempScores);
        } else {
            scores.add(scoreInstance);
        }
    }

    /*
    * 회차가 존재하는지 판별합니다.*/
    public boolean hasRound(int studentId, Subject subject, int round){
        ArrayList<Score> scores = store.get(studentId);
        if (scores == null){
            return false;
        }
        for (Score score : scores) {
            if (score.getSubjectId() == subject.getId() && score.getRound() == round){
                return true;
            }
        }
        return false;
    }


    /*read
     * 수강생 등급을 조회할 수 있습니다*/
    // 수강생의 수정할 과목의 회차의 점수 조회
    public Score readScore(int subjectId, int studentId, int round) {
        if(store.get(studentId) == null){
            return null;
        }
        return store.get(studentId).stream().filter(o -> o.getSubjectId() == subjectId)
                             .filter(o -> o.getRound() == round)
                             .findFirst()
                             .orElse(null);
    }

    /*update
     * 수강생의 과목별 회차점수를 수정할 수 있습니다.*/
    public void update(int studentId, int id, int updateScore) {
        store.get(studentId).stream().filter(o -> o.getId() == id).findFirst().ifPresent(score -> score.setScore(updateScore));
    }

    // -------------------------------선택----------------------------------
    /*read
     * 1. 수강생의 과목별 평균 등급을 조회할 수 있습니다.*/
    public String average(int studentId, int subjectId) {
//        ArrayList<Score> scores = store.get(studentId);
//        int sumScore = 0;
//        int count = 0;
//
//        for (Score score : scores) {
//            if (score.getSubjectId() == subjectId) {
//                count++;
//                sumScore += score.getScore();
//            }
//        }
//        int avgScore = sumScore / count;
//        return Score.scoreToGrade(subjectId, avgScore);

        ArrayList<Score> scores = store.get(studentId);
        OptionalDouble average = scores.stream()
                                       .filter(o -> o.getSubjectId() == subjectId)
                                       .mapToInt(Score::getScore)
                                       .average();
        int avgScore = (int) Math.floor(average.getAsDouble());
        return Score.scoreToGrade(subjectId, avgScore);
    }

     /* 2. 특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다. */

    /*delete
     * 수강생이 삭제될 때 같이 해당 학생의 점수들도 삭제
     * */

    // TODO: 추후 삭제 (더미 데이터 생성코드)
    public void setTestData(){
        Score score1 = new Score(1, Subject.JAVA, 1, 1, 100);
        Score score2 = new Score(2, Subject.JAVA, 1, 2, 90);
        Score score3 = new Score(3, Subject.JAVA, 1, 3, 70);
        Score score4 = new Score(4, Subject.SPRING, 1, 1, 80);
        Score score5 = new Score(5, Subject.JAVA, 2, 1, 100);

//        store.add(score1);
//        store.add(score2);
//        store.add(score3);
//        store.add(score4);
//        store.add(score5);
    }
}
