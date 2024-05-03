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

}

