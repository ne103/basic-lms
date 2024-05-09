package model;
import repository.StudentRepository;

import java.util.ArrayList;

public class Student {
    public enum Condition {Green, Red, Yellow}

    private final int id;
    private String name;
    private String state;
    private ArrayList<Subject> subjectList;
    private Condition condition;


    //생성자 오버로딩
    public Student(int id, String name) {

        this.id = id;
        this.name = name;
        this.state = state;
        this.subjectList = new ArrayList<>();
    }

    //생성자 오버로딩
    public Student(String name, Condition condition) {
        this.id = StudentRepository.getStoreSize() + 1;
        this.name = name;
        this.subjectList = new ArrayList<>();
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }


    //과목 리스트에 필수과목의 개수를 반환합니다.
    public int countEssential() {
        int count = 0;
        for (Subject subject : subjectList) {
            if (subject.isEssential()) {
                count++;
            }
        }
        return count;
    }

    //과목 리스트에 선택과목의 개수를 반환합니다.
    public int countNonEssential() {
        int count = 0;
        for (Subject subject : subjectList) {
            if (!subject.isEssential()) {
                count++;
            }
        }
        return count;
    }

    //필수과목이 3개 이상 선택과목이 2개 이상인지 판별합니다.
    public boolean determineRequirementMet(int amountEseential, int amountNonEseential) {
        if (amountEseential >= 3 && amountNonEseential >= 2) {
            return true;
        } else {
            return false;
        }
    }

    //과목 리스트를 비웁니다.
    public void subjectListClear() {
        subjectList.clear();
    }


    //과목 ID를 받아서 해당 과목을 과목 리스트에 넣습니다.
    public void registerSubject(int[] subjectId) {
        for (int i = 0; i < subjectId.length; i++) {
            subjectList.add(Subject.findById(subjectId[i]));
        }
    }


    // 매개변수로 들어온 과목이 학생이 수강중인 과목인지 아닌지 판별합니다.
    public boolean checkSubjectExist(Subject subject) {
        return subjectList.contains(subject);
    }

    //문자열 상태를 입력 받아서 해당 상태의 Condition 상태를 반환합니다.
    public static Condition stringToCondition(String value) {
        return Condition.valueOf(value);
    }

    public Condition getCondition() {
        return condition;
    }

    public void setName(String name) {
        this.name = name;
    }

    //정수값을 받아서 수강생 상태 변경
    public void chageConditionByNum(int num) {
        switch (num) {
            case 1 -> condition = Condition.Green;
            case 2 -> condition = Condition.Red;
            case 3 -> condition = Condition.Yellow;
        }
    }
}

