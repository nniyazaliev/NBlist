package com.geektech.todo;

import java.io.Serializable;

public class Task implements Serializable {
    public String title;
    public String description;
    public String deadline; // TODO: Change to Date type


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
