package com.example.quiztime.model;

public class Configs {
    private  int total;

    public Configs() {
    }

    @Override
    public String toString() {
        return "Configs{" +
                "total=" + total +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Configs(int total) {
        this.total = total;
    }
}
