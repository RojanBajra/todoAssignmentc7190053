package com.rojan.todo.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int taskId;
    private String taskName;
    private String taskDescription;
    private Date taskDate;
    private Date taskTime;
    private boolean isCompleted;
    private int priority;
    private Date createdOn;
    private Date updatedOn;

    @Ignore
    public Task(String taskName, String taskDescription, Date taskDate, Date taskTime, boolean isCompleted, int priority, Date createdOn, Date updatedOn){
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Task(int taskId, String taskName, String taskDescription, Date taskDate, Date taskTime, boolean isCompleted, int priority, Date createdOn, Date updatedOn){
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
