package model;

public enum Subject {
    JAVA(1, true),
    OOP(2, true),
    SPRING(3, true),
    JPA(4, true),
    MYSQL(5, true),
    DEGINE_PATTERN(6, false),
    SPRING_SECURITY(7, false),
    REDIS(8, false),
    MONGODB(9, false);

    private final int id;
    private final boolean isEssential;

    Subject(int id, boolean isEssential) {
        this.id = id;
        this.isEssential = isEssential;
    }

    public int getId() {
        return id;
    }

    public boolean isEssential() {
        return isEssential;
    }

    /* findById
     * 과목id로 해당하는 Subject 객체를 찾아 반환해줍니다.
     * 없으면 null을 반환하고, null을 실패 조건으로 사용합니다.*/
    public static Subject findById(int id){
        for (Subject value : values()) {
            if(value.getId() == id){
                return value;
            }
        }
        return null;
    }
}

