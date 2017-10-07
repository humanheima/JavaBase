package com.hm.effective.cp_three;

import java.awt.*;

/**
 * Created by dumingwei on 2017/9/11.
 * 复合优先于继承
 */
public class ColorPoint {

    private final Point point;

    private final Color color;


    public ColorPoint(int x, int y, Color color) {
        if (color == null)
            throw new NullPointerException();
        point = new Point(x, y);
        this.color = color;
    }

    public Point asPoint() {
        return point;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ColorPoint)) {
            return obj.equals(this);
        }
        ColorPoint cp = ((ColorPoint) obj);
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
