package com.hm.effective_java.chapter_three;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dumingwei on 2017/9/11.
 */
public class TestPoint {

    public static void main(String[] args) {
        CounterPoint p = new CounterPoint(1, 0);
        System.out.println(p.getClass());
        System.out.println(onUnitCircle(p));
    }

    private static final Set<Point> unitCircle;

    static {
        unitCircle = new HashSet<>();
        unitCircle.add(new Point(1, 0));
        unitCircle.add(new Point(0, 1));
        unitCircle.add(new Point(-1, 0));
        unitCircle.add(new Point(0, -1));
    }

    public static boolean onUnitCircle(Point p) {
        return unitCircle.contains(p);
    }
}
