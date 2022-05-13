package com.hm.picture_of_patten.command.command;

import java.util.Stack;
import java.util.Iterator;

/**
 * Created by dumingwei on 2022/5/12
 * <p>
 * Desc:
 */

public class MacroCommand implements Command {
    // 命令的集合
    private Stack<Command> commands = new Stack<>();

    // 执行
    public void execute() {
        Iterator<Command> it = commands.iterator();
        while (it.hasNext()) {
            it.next().execute();
        }
    }

    // 添加命令
    public void append(Command cmd) {
        if (cmd != this) {//注意不要将自己添加进去了，会造成死循环
            commands.push(cmd);
        }
    }

    // 删除最后一条命令
    public void undo() {
        if (!commands.empty()) {
            commands.pop();
        }
    }

    // 删除所有命令
    public void clear() {
        commands.clear();
    }
}
