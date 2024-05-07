package repository;

import model.Score;
import model.Subject;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoreRepository {

    private static ArrayList<Score> store = new ArrayList<>(); // Map으로도 관리할 수 있음
    private static int index = 0;

    /*
     * create
     * 학생id, 과목, 회차, 점수를 받아서 store에 추가합니다.
     * */
    public void create(int studentId, Subject subject, int round, int score) {
        Score scoreInstance = new Score(++index, subject, studentId, round, score);
        store.add(scoreInstance);
        System.out.println("점수 등록 성공");
    }

    /*
     * 과목id와 학생id를 통해서 학생의 해당 과목의 회차리스트를 반환합니다.
     * ex) 김준현 학생의 JAVA 과목 회차들 (1,2,3,4,5회차)를 반환*/
    public ArrayList<Score> findByIds(int subjectId, int studentId) {
        ArrayList<Score> list = new ArrayList<>();
        for (Score score : store) {
            if (score.getStudentId() == studentId && score.getSubjectId() == subjectId) {
                list.add(score);
            }
        }
        return list;
    }

    /*
     * 회차가 존재하는지 판별합니다.*/
    public boolean checkRoundIsExist(ArrayList<Score> scoreArrayList, int round) {
        for (Score score : scoreArrayList) {
            if (score.getRound() == round) {
                return true;
            }
        }
        return false;
    }


    /*read
     * 수강생 등급을 조회할 수 있습니다*/
    // 수강생의 수정할 과목의 회차의 점수 조회
    public Score readScore(int subjectId, int studentId, int round) {
        return store.stream().filter(o -> o.getSubjectId() == subjectId)
            .filter(o -> o.getStudentId() == studentId)
            .filter(o -> o.getRound() == round)
            .findFirst()
            .orElse(null);
    }

    /*update
     * 수강생의 과목별 회차점수를 수정할 수 있습니다.*/
    public void update(int id, int updateScore) {
        store.stream().filter(o -> o.getId() == id).findFirst()
            .ifPresent(score -> score.setScore(updateScore));
    }

    // -------------------------------선택----------------------------------
    /*read
     * 1. 수강생의 과목별 평균 등급을 조회할 수 있습니다.
     *
     * 2. 특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다. */

    /*delete
     * 수강생이 삭제될 때 같이 해당 학생의 점수들도 삭제
     * */

    // TODO: 추후 삭제 (더미 데이터 생성코드)
    public void setTestData() {
        Score score1 = new Score(1, Subject.JAVA, 1, 1, 100);
        Score score2 = new Score(2, Subject.JAVA, 1, 2, 90);
        Score score3 = new Score(3, Subject.JAVA, 1, 3, 70);
        Score score4 = new Score(4, Subject.SPRING, 1, 1, 80);
        Score score5 = new Score(5, Subject.JAVA, 2, 1, 100);

        store.add(score1);
        store.add(score2);
        store.add(score3);
        store.add(score4);
        store.add(score5);
    }
}
