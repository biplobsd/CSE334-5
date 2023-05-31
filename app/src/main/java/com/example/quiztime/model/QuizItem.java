package com.example.quiztime.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuizItem implements Serializable {
    private String title;
    private int answer;
    private List<String> options = new ArrayList<String>();

    public QuizItem(String question, int answer, List<String> options) {
        this.title = question;
        this.answer = answer;
        this.options = options;
    }

    public QuizItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "QuizItem{" +
                "title='" + title + '\'' +
                ", answer='" + answer + '\'' +
                ", options=" + options.toString() +
                '}';
    }
}
