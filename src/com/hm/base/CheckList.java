package com.hm.base;

import java.util.ArrayList;
import java.util.List;


public class CheckList {

    public static int checkLastElements(List<Element> list, int maxCheck) {
        // 从 list.size() - 1 开始遍历，到 0 结束
        for (int i = list.size() - 1; i >= 0 && (list.size() - i) <= maxCheck; i--) {
            Element element = list.get(i);
            if (element.isChecked()) {
                return i; // 找到 checked=true 的元素
            }
        }
        return -1; // 未找到
    }

    public static void main(String[] args) {
        // 示例数据
        List<Element> myList = new ArrayList<>();

        myList.add(new Element(false));
        myList.add(new Element(false));
        myList.add(new Element(true));
        myList.add(new Element(true));
        myList.add(new Element(true));
        myList.add(new Element(false));
        myList.add(new Element(false));
        myList.add(new Element(false));
        myList.add(new Element(false));
        myList.add(new Element(false));

        // 检查是否存在 checked=true 的元素
        int result = checkLastElements(myList, 5);
        System.out.println(result); // 输出: true
    }
}

// 定义一个简单的 Element 类
class Element {
    private boolean checked;

    public Element(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }
}