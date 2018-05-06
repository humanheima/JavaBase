package com.hm.pattern.composite.menuiterator;

import java.util.*;

public class CompositeIterator implements Iterator<MenuComponent> {
    Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    public MenuComponent next() {
        if (hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent component = iterator.next();
            stack.push(component.createIterator());
            return component;
        } else {
            return null;
        }
    }

    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            //从栈中取出一个元素，但是不删除
            Iterator<MenuComponent> iterator = stack.peek();
            /**
             * 如果传入的是一个Menu的迭代器,Menu内部的实现是ArrayList返回的迭代器
             * MenuItem内部返回的是一个NullIterator
             */
            if (!iterator.hasNext()) {
                //删除并返回栈顶的元素
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }
}


