package repository;

import model.Student;

import java.util.ArrayList;

public class StudentRepository {
    private static ArrayList<Student> store = new ArrayList<>(); // Map으로도 관리할 수 있음
    private static int index = 0;

    /*
     * create
     * 수강생의 정보 등록하는 함수
     * 조건: 필수과목 3개, 선택과목 2개 이상
     * 조건: 수강생의 고유번호는 중복될 수 없습니다.
     * */


    /*read
     * 수강생 목록을 조회할 수 있습니다*/



    // ---------------------- 선택 -----------------------------
    /*read
     * 수강생 정보를 조회할 수 있습니다.
     * 상태별 수강생 목록을 조회할 수 있습니다.*/

    /*update
     * 수강생 상태를 관리할 수 있습니다. (변경)
     * 수강생 정보를 수정할 수 있습니다. (이름 or 상태를 입력받아 수정)*/

    /* delete
    수강생을 삭제할 수 있습니다.
    * 수강생 삭제시 해당 수강생의 점수 기록도 함께 삭제됩니다.*/
}

