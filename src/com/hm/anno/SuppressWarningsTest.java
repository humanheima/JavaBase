package com.hm.anno;

import java.util.*;

// 关闭整个类里的编译器警告
@SuppressWarnings(value = "unchecked")
public class SuppressWarningsTest {
    public static void main(String[] args) {
        List<String> myList = new ArrayList();     // ①
    }
}

