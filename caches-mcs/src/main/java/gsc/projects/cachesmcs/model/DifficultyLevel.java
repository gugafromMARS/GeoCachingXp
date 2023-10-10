package gsc.projects.cachesmcs.model;

public enum DifficultyLevel {

    VERYEASY(1),
    EASY(2),
    NORMAL(3),
    HARD(4),
    VERYHARD(5);

    private final int difficulty;

    DifficultyLevel(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
