package repository;

import model.Score;
import model.Subject;

import java.util.ArrayList;

public class ScoreRepository {
    private static ArrayList<Score> store = new ArrayList<>(); // Map으로도 관리할 수 있음
    private static int index = 0;
    /*
     * create
     * 수강생의 과목별 시험 회차 및 점수를 등록할 수 있습니다.
     * 등록하려는 과목의 회차 점수가 이미 등록되어 있다면 등록할 수 없습니다. 과목의 회차 점수가 중복되어 등록될 수 없습니다. (학생id, 과목id가 같은 값들 조회해야함)
     * 회차 또한 10회까지
     * 점수를 등록하면 자동으로 등급이 추가 저장됩니다. (점수별 등급 지정해주는 함수 필요)
     * */



    /*read
     * 수강생 등급을 조회할 수 있습니다*/


    /*update
     * 수강생의 과목별 회차점수를 수정할 수 있습니다.*/





    // -------------------------------선택----------------------------------
    /*read
     * 1. 수강생의 과목별 평균 등급을 조회할 수 있습니다.
     *
     * 2. 특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다. */

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

        store.add(score1);
        store.add(score2);
        store.add(score3);
        store.add(score4);
        store.add(score5);
    }
}
