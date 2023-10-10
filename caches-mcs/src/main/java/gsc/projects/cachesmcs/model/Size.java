package gsc.projects.cachesmcs.model;

public enum Size {

    VERYSMALL(1),
    SMALL(2),
    NORMAL(3),
    LARGE(4),
    VERYLARGE(5);

    private final int sizeLevel;

    Size(int sizeLevel) {
        this.sizeLevel = sizeLevel;
    }

    public int getSizeLevel() {
        return sizeLevel;
    }
}
