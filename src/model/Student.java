package model;
import repository.StudentRepository;
import model.Subject;
import java.util.ArrayList;

public class Student {
    private final int id;
    private String name;
    private ArrayList<Subject> subjectList;
    private String condition;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.subjectList = new ArrayList<>();
    }

    public Student(String name) {
        this.id = StudentRepository.getStoreSize() +1;
        this.name = name;
        this.subjectList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public String getCondition() {
        return condition;
    }


    public boolean determineRequirementMet() {
        int countEssential=0;
        int countNoEssential=0;
        for (Subject subject : subjectList) {
            if (subject.isEssential()){
                countEssential++;
            } else {
                countNoEssential++;
            }
        }
        if (countEssential>=3 && countNoEssential >=2) {
            return true;
        } else {
            return false;
        }
    }

    public void subjectListClear() {
        subjectList.clear();
    }

    public void subjectRegister(int[] subjectId){
        for(int i = 0; i<subjectId.length; i++){
            subjectList.add(Subject.findById(subjectId[i]));
        }
    }


    // 매개변수로 들어온 과목이 학생이 수강중인 과목인지 아닌지 판별합니다.
    public boolean checkSubjectExist(Subject subject) {
        return subjectList.contains(subject);
    }
}