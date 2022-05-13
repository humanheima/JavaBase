package com.hm.picture_of_patten.command.drawer;

import com.hm.picture_of_patten.command.command.Command;

import java.awt.Point;

/**
 * Created by dumingwei on 2022/5/12
 *
 * Desc:
 */
public class DrawCommand implements Command {
    // 绘制对象
    protected Drawable drawable;
    // 绘制位置
    private Point position;
    // 构造函数
    public DrawCommand(Drawable drawable, Point position) {
        this.drawable = drawable;
        this.position = position;
    }
    // 执行
    public void execute() {
        drawable.draw(position.x, position.y);
    }
}
