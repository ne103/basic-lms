package repository;

import javax.print.attribute.standard.PresentationDirection;
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

    //수강생 등록
    public void registerStudent(Student student) {
            store.add(student);
    }

    //ArrayList를 받아서 전체 수강생 ID들을 담아줍니다
    public void getStudentID(ArrayList<Integer> idList) {
            for (Student student : store) {
                idList.add(student.getId());
            }
    }

    /*read
     * 수강생 목록을 조회할 수 있습니다*/
    public void printAllStudents() {
        for (int i = 0; i < store.size(); i++) {
            System.out.println("--------------------------");
            System.out.println("Id: " + store.get(i).getId() + "\n" + "이름: " + store.get(i).getName());
            System.out.println("--------------------------\n");
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
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getState() + "\n 선택한 과목: " + store.get(i).getSubjectList());
                System.out.println("--------------------------\n");
                flag = true;
            }
        }
        if (flag != true) {
            System.out.println("입력한 이름의 학생을 찾지 못했습니다.");
        }
    }

    // 상태가 red인 학생들 출력
    public void printStudentsRed() {
        boolean flag = false;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getState().equals("Red")) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getState() + "\n 선택한 과목: " + store.get(i).getSubjectList());
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
            if (store.get(i).getState().equals("Yellow")) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getState() + "\n 선택한 과목: " + store.get(i).getSubjectList());
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
            if (store.get(i).getState().equals("Green")) {
                System.out.println("--------------------------");
                System.out.println(" Id: " + store.get(i).getId() +  "\n 이름: " + store.get(i).getName() + "\n 상태: " + store.get(i).getState() + "\n 선택한 과목: " + store.get(i).getSubjectList());
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


    // TODO: 추후 삭제 (더미 데이터 생성코드)
    public void setTestData(){
        Student student1 = new Student(1,"김준혁","Green");
        student1.getSubjectList().addAll(Arrays.stream(Subject.values()).toList());
        Student student2 = new Student(2,"이도윤", "red");
        student2.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JAVA, Subject.OOP, Subject.SPRING, Subject.MONGODB, Subject.MYSQL)));
        Student student3 = new Student(3,"박하준", "Yellow");
        student3.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JPA, Subject.OOP, Subject.JPA, Subject.MONGODB, Subject.MYSQL)));
        Student student4 = new Student(4,"최이안", "Yellow");
        student4.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JPA, Subject.OOP, Subject.JPA, Subject.MONGODB, Subject.MYSQL)));
        Student student5 = new Student(5,"강연우", "Green");
        student5.getSubjectList().addAll(new ArrayList<>(Arrays.asList(Subject.JPA, Subject.OOP, Subject.SPRING, Subject.MONGODB, Subject.MYSQL)));

        store.add(student1);
        store.add(student2);
        store.add(student3);
        store.add(student4);
        store.add(student5);
    }
}

