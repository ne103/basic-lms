package repository;

import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {
    private static ArrayList<Student> store = new ArrayList<>(); // Map으로도 관리할 수 있음
    private static int index = 0;
    /*
     * create
     * 수강생의 정보 등록하는 함수
     * 조건: 필수과목 3개, 선택과목 2개 이상
     * 조건: 수강생의 고유번호는 중복될 수 없습니다.
     * */

    //수강생 등록
    public void registerStudent(Student student) {
            store.add(student);
    }
    //현재 저장 된 학생 수
    public static int getStoreSize() {
        return store.size();
    }

    /*read
     * 수강생 목록을 조회할 수 있습니다*/
    public void printAllStudents() {
        if(store.isEmpty()){
            System.out.println("등록된 수강생이 없습니다.");
        }else{
//            for (int i = 0; i < store.size(); i++) {
//                System.out.println("--------------------------");
//                System.out.println("Id: " + store.get(i).getId() + "\n" + "이름: " + store.get(i).getName());
//                System.out.println("--------------------------\n");
//            }
            for (Student student : store) {
                System.out.println("--------------------------");
                System.out.println("Id: " + student.getId() + "\n" + "이름: " + student.getName());
                System.out.println("--------------------------\n");
            }
        }
    }


    public Student getStudentById (int id) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId() == id) {
                return store.get(i);
            }
        }
        return null;
    }

    //id를 받아서 해당 수강생을 삭제합니다.
    public void removeStudentById(int id) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getId() == id) {
                store.remove(i);
            }
        }
    }

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

    // 수강생 정보 조회
    public void printStudentInfo(String studentName) {
        boolean flag = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getName().equals(studentName)) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getCondition().name() + "\n 선택한 과목: " + store.get(i).getSubjectList());
                System.out.println("--------------------------\n");
                flag = true;
            }
        }
        if (flag != true) {
            System.out.println("입력한 이름의 학생을 찾지 못했습니다.");
        }
    }

    //상태별 수강생 리스트 조회
    public List<Student> findByCondition(String condition) {
        return store.stream()
                    .filter(o -> o.getCondition().toString().equals(condition))
                    .collect(Collectors.toList());
    }

    // 상태가 red인 학생들 출력
    public void printStudentsRed() {
        boolean flag = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getCondition().name().toLowerCase().equals("red")) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getCondition().name() + "\n 선택한 과목: " + store.get(i).getSubjectList());
                System.out.println("--------------------------\n");
                flag = true;
            }
        }
        if (flag != true) {
            System.out.println("잘못 입력하셨습니다. 이전으로 돌아갑니다.");
        }
    }

    // 상태가 Yellow인 학생들 출력
    public void printStudentsYellow() {
        boolean flag = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getCondition().name().toLowerCase().equals("yellow")) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getCondition().name() + "\n 선택한 과목: " + store.get(i).getSubjectList());
                System.out.println("--------------------------\n");
                flag = true;
            }
        }
        if (flag != true) {
            System.out.println("잘못 입력하셨습니다. 이전으로 돌아갑니다.");
        }
    }

    //상태가 Green인 학생들 출력
    public void printStudentsGreen() {
        boolean flag = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getCondition().name().toLowerCase().equals("green")) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getCondition().name() + "\n 선택한 과목: " + store.get(i).getSubjectList());
                System.out.println("--------------------------\n");
                flag = true;
            }
        }
        if (flag != true) {
            System.out.println("잘못 입력하셨습니다. 이전으로 돌아갑니다.");
        }
    }




    /*update
     * 수강생 상태를 관리할 수 있습니다. (변경)
     * 수강생 정보를 수정할 수 있습니다. (이름 or 상태를 입력받아 수정)*/



    /* delete
    수강생을 삭제할 수 있습니다.
    * 수강생 삭제시 해당 수강생의 점수 기록도 함께 삭제됩니다.*/


}

