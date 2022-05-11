package com.hm.picture_of_patten.memento.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2022/5/10
 * <p>
 * Desc: 表示主人公状态的类
 */
public class Memento {
    int money;                              // 所持金钱
    ArrayList<String> fruits;                       // 当前获得的水果

    public int getMoney() {                 // 获取当前所持金钱（narrow interface）
        return money;
    }

    Memento(int money) {                    // 构造函数(wide interface)
        this.money = money;
        this.fruits = new ArrayList<>();
    }

    void addFruit(String fruit) {           // 添加水果(wide interface)
        fruits.add(fruit);
    }

    List<String> getFruits() {                      // 获取当前所持所有水果（wide interface）
        return (List<String>) fruits.clone();
    }
}
