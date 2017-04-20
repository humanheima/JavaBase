package com.hm.lambda.streams;

/**
 * Created by dumingwei on 2017/3/13.
 */
public class Task {

    enum Status {
        CLOSED, OPEN;
    }

    private final Status status;
    private final Integer points;

    public Task(Status status, Integer points) {
        this.status = status;
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", status, points);
    }
}
