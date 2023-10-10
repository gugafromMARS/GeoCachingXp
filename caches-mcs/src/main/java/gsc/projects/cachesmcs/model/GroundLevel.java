package gsc.projects.cachesmcs.model;

public enum GroundLevel {

    VERYGOOD(1),
    GOOD(2),
    NORMAL(3),
    ROUGH(4),
    VERYROUGH(5);

    private final int difficulty;

    GroundLevel(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
