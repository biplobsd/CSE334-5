package com.example.quiztime.model;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                ", quizID='" + quizID + '\'' +
                ", score=" + score +
                '}';
    }

    private String displayName;
    private int quizID;
    private int score;

    public User(String displayName, int quizID, int score) {
        this.displayName = displayName;
        this.quizID = quizID;
        this.score = score;
    }

    public User() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
