package com.example.quiztime.model;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
    private int id;

    private int length;
    private List<QuizItem> items;

    public Quiz( int length, List<QuizItem> items) {
        this.id = id;
        this.length = length;
        this.items = items;
    }

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<QuizItem> getItems() {
        return items;
    }

    public void setItems(List<QuizItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", length=" + length +
                ", items=" + items +
                '}';
    }
}
