package gsc.projects.cachesmcs.model;

public enum CacheLevel {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5);

    private final int level;

    CacheLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
