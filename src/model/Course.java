package model;

import java.util.Arrays;
import java.util.Date;

public class Course {
    private String id;
    private String title;
    private String[] instructorNames;
    private String[] requirements;
    private Date startDate;

    // Constructors, getters, setters, toString() method

    public Course(String id, String title, String[] instructorNames, String[] requirements, Date startDate) {
        this.id = id;
        this.title = title;
        this.instructorNames = instructorNames;
        this.requirements = requirements;
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getInstructorNames() {
        return instructorNames;
    }

    public void setInstructorNames(String[] instructorNames) {
        this.instructorNames = instructorNames;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public void setRequirements(String[] requirements) {
        this.requirements = requirements;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\tTitle: %s\tInstructor: %s\tRequirement: %s\tStart Date: %s",
                id, title, Arrays.toString(instructorNames), Arrays.toString(requirements), startDate);
    }
}
