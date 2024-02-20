package com.goit.dto;

public class PrintPrices {
    private final int projectID;
    private final int projectCost;

    public PrintPrices(int projectID, int projectCost) {
        this.projectID = projectID;
        this.projectCost = projectCost;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getProjectCost() {
        return projectCost;
    }
}
