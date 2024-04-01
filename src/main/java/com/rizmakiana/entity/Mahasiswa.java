package com.rizmakiana.entity;

public class Mahasiswa {
 
    private String id, name;
    private int taskScore, midTestScore, lastTestScore;
    private double lastScore;

    public Mahasiswa (){
    }

    public Mahasiswa (String name, int taskScore, int midTestScore, int lastTestScore){
        this.name = name;
        this.taskScore = taskScore;
        this.midTestScore = midTestScore;
        this.lastTestScore = lastTestScore;
    }
    public Mahasiswa (String id,String name, int taskScore, int midTestScore, int lastTestScore){
        this.id = id;
        this.name = name;
        this.taskScore = taskScore;
        this.midTestScore = midTestScore;
        this.lastTestScore = lastTestScore;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setTaskScore(int taskScore) {
        this.taskScore = taskScore;
    }
    public int getTaskScore() {
        return taskScore;
    }

    public void setMidTestScore(int midTestScore) {
        this.midTestScore = midTestScore;
    }
    public int getMidTestScore() {
        return midTestScore;
    }

    public void setLastTestScore(int lastTestScore) {
        this.lastTestScore = lastTestScore;
    }
    public int getLastTestScore() {
        return lastTestScore;
    }

    public void setLastScore() {
        this.lastScore = ((0.2 * getTaskScore()) + (0.3 * getMidTestScore()) + (0.5 * lastTestScore));
    }
    public double getLastScore() {
        return lastScore;
    }

}
