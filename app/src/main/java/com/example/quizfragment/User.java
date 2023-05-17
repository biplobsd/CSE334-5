package com.example.quizfragment;

public class User {

    public User() {
        // Default constructor required for Firebase deserialization
    }
    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", score=" + score;
    }
}
