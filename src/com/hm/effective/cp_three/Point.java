package com.hm.effective.cp_three;

/**
 * Created by dumingwei on 2017/9/11.
 */
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point p = (Point) obj;
        return p.x == x && p.y == y;
    }

}
