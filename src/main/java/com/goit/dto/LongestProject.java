package com.goit.dto;

public class LongestProject {
    private final int id;
    private final int countOfMonths;

    public LongestProject(int id, int countOfMonths) {
        this.id = id;
        this.countOfMonths = countOfMonths;
    }

    public int getID() {
        return id;
    }

    public int getCountOfMonths() {
        return countOfMonths;
    }
}
