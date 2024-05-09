
import model.Score;
import model.Student;
import model.Subject;

import repository.ScoreRepository;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */

/*
 * 최대한 객체지향적이게 설계해보려고 노력했습니다.
 * 더 좋은 방법이 있다면 언제든 말씀해주세요!
 * 모델 및 레포지토리 클래스들에서 필요한 함수들을 적절하게 구현해서 완성해주시면 될 것 같습니다!
 * 전체적인 틀에 대해 궁금하신 점이나 이상한게 있다면 꼭 말씀해주세요!
 * */
public class CampManagementApplication {
    // 데이터 저장소
    private static StudentRepository studentRepository = new StudentRepository();
    private static ScoreRepository scoreRepository = new ScoreRepository();


    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // 더미 데이터 입력
//        studentRepository.setTestData();
//        scoreRepository.setTestData();

        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    private static void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private static void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 관리");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> manageStudent();
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록
    private static void createStudent() {
        boolean success = false;
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();

        System.out.print("수강생 상태 입력 (Green, Yellow, Red) : ");
        String state = sc.next();

        //버퍼에서 \n값 빼기
        sc.nextLine();

        String condition="";
        //수강생 상태 입력 받기. Green Red Yellow 아니면 다시 받음
        while(!success) {
            System.out.print("수강생의 상태를 입력하세요(Green, Red, Yellow):");
            condition = sc.nextLine();
            if(condition.equals("Green")||condition.equals("Red")||condition.equals("Yellow")) {
                success = true;
            } else {
                System.out.println("다시 입력하세요.");
            }
        }
        //학생 인스턴스 생성
        Student student =new Student(studentName,Student.stringToCondition(condition));

        //조건을 충족하는 과목들을 선택할때 까지 반복
        success = false;
        while(!success) {
            //과목 선택
            System.out.println("3개 이상의 필수 과목, 2개 이상의 선택 과목 선택");
            System.out.println("필수 과목 : 1.JAVA, 2.OOP, 3.SPRING, 4.JPA, 5.MYSQL");
            System.out.println("선택 과목 : 6.DEGINE_PATTERN, 7.SPRING_SECURITY, 8.REFIS, 9.MONGODB");
            System.out.print("등록할 과목 입력(숫자/띄어쓰기): ");
            String[] subject = sc.nextLine().split(" ");

            try {
                int[] subjectId = Arrays.stream(subject).mapToInt(Integer::parseInt).toArray();
                //학생 과목 등록
                student.registerSubject(subjectId);
                //조건 충족 판별
                if (student.determineRequirementMet(student.countEssential(), student.countNonEssential())) {
                    success = true;
                    //조건 충족 시 저장소에 저장
                    studentRepository.registerStudent(student);
                } else {
                    //조건 불충족 시 등록 한 과목 삭제
                    student.subjectListClear();
                    System.out.println("수강생 등록 실패. 최소 3개 이상의 필수 과목과 2개 이상의 선택 과목을 입력하세요.");
                }
            } catch (Exception e) {
                System.out.println("과목은 숫자로 입력하시고 각 과목을 띄어쓰기로 구분해주세요");
            }
        }
        System.out.println("수강생 등록 성공!");
        System.out.println("해당 수강생의 ID는 "+student.getId()+"입니다.");
    }

    //수강생 상태 관리
    private static void managingStudent() {

    }


    //수강생 검색
    private static void searchStudent() {
        System.out.println("==================================");
        System.out.print("수강생 이름을 입력하세요...");
        String studentName = sc.next();
        studentRepository.printStudentInfo(studentName);
    }



    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        studentRepository.printAllStudents();// 기능 구현
        System.out.println("\n수강생 목록 조회 성공!");
    }

    //수강생 정보 관리
    public static void manageStudent() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 정보 관리 실행 중...");
            System.out.println("1. 수강생 정보 수정");
            System.out.println("2. 수강생 삭제");
            System.out.println("3. 수강생 관리 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> { // 수강생 상태 확인
                    boolean success = false;
                    while(!success){
                        try {
                            System.out.print("수강생 ID를 입력하세요:");
                            int studentId = sc.nextInt();
                            sc.nextLine();
                            System.out.println("확인");
                            System.out.println("해당 수강생의 상태는 " + studentRepository.findById(studentId).getCondition().name() + "입니다.");
                            break;
                        } catch (Exception e) {
                            System.out.println("ID를 잘 못 입력했습니다.");
                        }
                    }


                }
                case 2 -> { // 수강생 정보 수정
                    System.out.print("수강생의 ID를 입력하세요:");
                    int studentId = sc.nextInt();
                    System.out.println("수강생 이름: "+studentRepository.findById(studentId).getName());
                    System.out.println("수강생 상태: "+studentRepository.findById(studentId).getCondition().name());
                    System.out.print("수정할 것을 입력하세요(1.이름 2.상태):");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    switch (choice) {
                        case 1 -> {
                            System.out.print("변경할 이름을 입력하세요:");
                            String studentName = sc.nextLine();
                            studentRepository.findById(studentId).setName(studentName);
                            System.out.println("이름 변경 성공!");
                        }
                        case 2 -> {
                            System.out.println("1.Green 2.Red 3.Yellow");
                            System.out.print("변경할 상태를 입력하세요:");
                            int condition = sc.nextInt();
                            studentRepository.findById(studentId).chageConditionByNum(condition);
                            System.out.println("상태 변경 성공!");
                        }
                    }
                }
                case 3 -> { // 수강생 삭제
                    System.out.println("수강생 ID를 입력하세요:");
                    int studentId = sc.nextInt();
                    System.out.println("ID: "+studentRepository.getStudentById(studentId).getId());
                    System.out.println("Name: "+studentRepository.getStudentById(studentId).getName());
                    System.out.print("해당 수강생을 삭제하시겠습니까?(Y/N):");
                    String choice = sc.next();
                    if (choice.equals("Y")) {
                        studentRepository.removeStudentById(studentId);
                        System.out.println("수강생 삭제 완료!");
                    } else if (choice.equals("N")) {
                        System.out.println("삭제 취소");
                        flag = false;
                    }


                }
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n수강생 관리 화면 이동...");
                    flag = false;
                }
            }
        }
    }





    //상태별 수강생 목록
    private static void stateListStudent(){
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("상태별 수강생 목록 실행 중...");
            System.out.println("1. Green");
            System.out.println("2. Yellow");
            System.out.println("3. Red");
            System.out.println("4. 이전 으로 이동");
            System.out.print("조회 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> studentRepository.printStudentsGreen(); //상태가 Green 인 학생들 출력
                case 2 -> studentRepository.printStudentsYellow(); // 상태가 Yellow 인 학생들 출력
                case 3 -> studentRepository.printStudentsRed(); // 상태가 Red 인 학생들 출력
                case 4 -> flag = false; // 이전 으로 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n 이전으로...");
                    flag = false;
                }
            }
        }
    }

    private static void displayScoreView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        // 학생 id 입력 및 존재 확인
        System.out.print("관리할 학생의 id를 입력하세요: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        Student student = studentRepository.findById(studentId);
        if (student == null){
            System.out.println(studentId + "번 학생은 존재하지 않습니다.");
            return;
        }

        // 학생이 수강하는 과목 리스트 출력
        for (Subject subject : student.getSubjectList()) {
            System.out.print(subject.getId() + ". " + subject.name() + " ");
        }
        System.out.println(" ");

        // 과목 id 입력 및 존재 확인, 해당과목 수강하는지 확인
        System.out.print(student.getName()+" 학생의 추가할 점수의 과목 id를 입력하세요: ");
        int subjectId = sc.nextInt();
        sc.nextLine();
        Subject subject = Subject.findById(subjectId);
        if(subject == null){
            System.out.println("존재하지 않는 과목 id 입니다.");
            return;
        }
        if(!student.checkSubjectExist(subject)){
            System.out.println("이 학생은 해당 과목을 수강하지 않았습니다.");
            return;
        }

        // 회차 입력 및 회차범위, 이미 존재여부 확인
        System.out.print(student.getName() + " 학생의 " + subject.name() + " 과목의 회차를 입력하세요:");
        int round = sc.nextInt();
        sc.nextLine();
        if (round < 1 || round > 10 ){
            System.out.println("회차는 1~10까지만 존재합니다.");
            return;
        }

        if(scoreRepository.hasRound(studentId, subject, round)){
            System.out.println("이미 존재하는 회차입니다. 등록할 수 없습니다.");
            return;
        }

        System.out.print("등록할 점수를 입력해주세요: ");
        int score = sc.nextInt();
        sc.nextLine();
        if (score < 0 || score > 100){
            System.out.println("잘못된 점수입니다.");
            return;
        }
        scoreRepository.create(studentId,subject,round,score);

        System.out.println(subject.name() + " " + round + "회차 " + score + "점 등록 성공!");
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() throws InterruptedException {
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("==================================");
        System.out.println("시험 점수를 수정합니다...");

        // 수강생 고유번호 입력
        System.out.print("수강생 고유번호를 입력해주세요...");
        int id = sc.nextInt();
        sc.nextLine(); // 엔터 입력 시 엔터 자체를 입력으로 받아들여 다음 실행될 구문이 씹히는 것을 방지

        Student student = studentRepository.findById(id);

        if (student != null) {

            // 수정할 과목ID 입력
            System.out.print("수정할 과목의 ID를 입력해주세요...");
            Subject.printList();
            int subjectId = sc.nextInt();
            sc.nextLine();

            ArrayList<Subject> subjectList = student.getSubjectList();

            // Subject에 입력한 과목id 비교 - anyMatch() : 하나라도 존재하는것이 있으면 true, 아니면 false
            if (subjectList.stream().anyMatch(o -> o.getId() == subjectId)) {
                // 수정할 회차 입력
                System.out.print("수정할 회차(1~10)를 입력해주세요...");
                int round = sc.nextInt();
                sc.nextLine();

                Score score = scoreRepository.readScore(subjectId, id, round);
                if (score != null) {
                    System.out.println("과목 : " + Subject.findById(score.getSubjectId()));
                    System.out.println("회차 : " + score.getRound() + "회차");
                    System.out.println("점수 : " + score.getScore() + "점");
                    System.out.print("수정할 점수를 입력해주세요...");
                    int updateScore = sc.nextInt();
                    sc.nextLine();
                    if (updateScore >= 0 && updateScore <= 100) {
                        scoreRepository.update(student.getId() ,score.getId(), updateScore);
                    } else {
                        System.out.println("점수는 0부터 100까지 입력 가능합니다...");
                        System.out.println("점수 관리 화면으로 이동합니다...");
                        Thread.sleep(1000);
                        return;
                    }
                } else {
                    System.out.println("해당 회차에 등록되어있는 점수가 없습니다...");
                    System.out.println("점수 관리 화면으로 이동합니다...");
                    Thread.sleep(1000);
                    return;
                }
            } else {
                System.out.println("해당 과목을 수강하고 있지 않습니다...");
                System.out.println("점수 관리 화면으로 이동합니다...");
                Thread.sleep(1000);
                return;
            }


        } else {
            System.out.println("해당 수강생 고유번호는 존재하지 않습니다...");
            System.out.println("점수 관리 화면으로 이동합니다...");
            Thread.sleep(1000);
            return;
        }

        // 기능 구현
        System.out.println("\n점수 수정 성공! 점수 관리 화면으로 이동합니다...");
        Thread.sleep(1000);
    }

    // 수강생의 특정 과목 회 차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        // 기능 구현 (조회할 특정 과목)
        System.out.println("회차별 등급을 조회합니다...");
        // 학생 id 입력 및 존재 확인
        System.out.print("조회할 학생의 id를 입력하세요: ");
        int studentId = sc.nextInt();
        sc.nextLine();
        Student student = studentRepository.findById(studentId);
        if (student == null){
            System.out.println(studentId + "번 학생은 존재하지 않습니다.");
            return;
        }

        // 학생이 수강하는 과목 리스트 출력
        for (Subject subject : student.getSubjectList()) {
            System.out.print(subject.getId() + ". " + subject.name() + " ");
        }
        System.out.println(" ");

        // 과목 id 입력 및 존재 확인, 해당과목 수강하는지 확인
        System.out.print(student.getName()+" 학생의 조회할 과목 id를 입력하세요: ");
        int subjectId = sc.nextInt();
        sc.nextLine();
        Subject subject = Subject.findById(subjectId);
        if(subject == null){
            System.out.println("존재하지 않는 과목 id 입니다.");
            return;
        }
        if(!student.checkSubjectExist(subject)){
            System.out.println("이 학생은 해당 과목을 수강하지 않았습니다.");
            return;
        }

        // 회차 입력 및 회차범위, 이미 존재여부 확인
        System.out.print(student.getName() + " 학생의 " + subject.name() + " 과목의 회차를 입력하세요:");
        int round = sc.nextInt();
        sc.nextLine();
        if (round < 1 || round > 10 ){
            System.out.println("회차는 1~10까지만 존재합니다.");
            return;
        }

        if(!scoreRepository.hasRound(studentId, subject, round)){
            System.out.println(round + "회차는 아직 등록되지 않았습니다.");
            return;
        }

        Score score = scoreRepository.readScore(subjectId, studentId, round);

        System.out.println("==========================");
        System.out.println("학생: " + studentId + ". " + student.getName());
        System.out.println("과목: " + subject.name());
        System.out.println("점수: " + score.getRound() + "회차 " + score.getScore() + "점 " + score.getGrade() + "등급");
        System.out.println("==========================");
        System.out.println("\n등급 조회 성공!");
    }
}
