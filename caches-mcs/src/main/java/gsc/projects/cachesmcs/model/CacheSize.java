package gsc.projects.cachesmcs.model;

public enum CacheSize {

    VERYSMALL(1),
    SMALL(2),
    NORMAL(3),
    LARGE(4),
    VERYLARGE(5);

    private final int sizeLevel;

    CacheSize(int sizeLevel) {
        this.sizeLevel = sizeLevel;
    }

    public int getSizeLevel() {
        return sizeLevel;
    }
}
