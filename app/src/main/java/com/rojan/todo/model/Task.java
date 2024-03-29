package com.rojan.todo.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int taskId;
    private String taskName;
    private String taskDescription;
    private Date taskDate;
    private Date taskTime;
    private boolean isCompleted;
    // high = 0, medium = 1, low = 2
    private int priority;
    @ForeignKey
        (entity = Task.class,
                parentColumns = "categoryId",
                childColumns = "categoryId",
                onDelete = CASCADE)
    private int categoryId;
    private Date createdOn;
    private Date updatedOn;

    @Ignore
    public Task(String taskName, String taskDescription, Date taskDate, Date taskTime, boolean isCompleted, int priority, int categoryId, Date createdOn, Date updatedOn){
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.categoryId = categoryId;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Task(){
        this.taskId = 0;
        this.taskName = "";
        this.taskDescription = "";
        this.taskDate = new Date();
        this.taskTime = new Date();
        this.isCompleted = false;
        this.priority = 0;
        this.categoryId = 0;
        this.createdOn = new Date();
        this.updatedOn = new Date();
    }

    public Task(int taskId, String taskName, String taskDescription, Date taskDate, Date taskTime, boolean isCompleted, int priority, int categoryId, Date createdOn, Date updatedOn){
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.isCompleted = isCompleted;
        this.priority = priority;
        this.categoryId = categoryId;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
