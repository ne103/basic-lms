package repository;

import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

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


    // 학생id를 사용해서 StudentRepository에 저장되어있는 학생을 찾아 반환합니다.
    // 없으면 null을 반환하고, null을 실패조건으로 사용했습니다.
    public Student findById(int id){
        for (Student student : store) {
            if(student.getId() == id) {
                return student;
            }
        }
        return null; // null이면 실패
    }



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


    // TODO: 추후 삭제 (더미 데이터 생성코드)
    public void setTestData(){
        Student student1 = new Student(1,"김준혁");
        student1.getSubjectList().addAll(Arrays.stream(Subject.values()).toList());
        Student student2 = new Student(2,"이도윤");
        student2.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JAVA, Subject.OOP, Subject.SPRING, Subject.MONGODB, Subject.MYSQL)));
        Student student3 = new Student(3,"박하준");
        student3.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JPA, Subject.OOP, Subject.JPA, Subject.MONGODB, Subject.MYSQL)));
        Student student4 = new Student(4,"최이안");
        student4.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JPA, Subject.OOP, Subject.JPA, Subject.MONGODB, Subject.MYSQL)));
        Student student5 = new Student(5,"강연우");
        student5.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JPA, Subject.OOP, Subject.SPRING, Subject.MONGODB, Subject.MYSQL)));

        store.add(student1);
        store.add(student2);
        store.add(student3);
        store.add(student4);
        store.add(student5);
    }
}

