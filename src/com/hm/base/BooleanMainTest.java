package com.hm.base;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by dmw on 2018/12/17.
 * Desc:在定义POJO中的布尔类型的变量时，不要使用isSuccess这种形式，而要直接使用success！
 */
public class BooleanMainTest {

    public static void main(String[] args) {

        Model3 model3 = new Model3();
        model3.setSuccess(true);
        System.out.println("Serializable Result With fastjson :" + JSON.toJSONString(model3));
        Gson gson = new Gson();
        System.out.println("Serializable Result With Gson :" + gson.toJson(model3));

        /* 对于同一个对象，我使用fastjson进行序列化，再使用Gson反序列化会发生什么？ */
        System.out.println(gson.fromJson(JSON.toJSONString(model3), Model3.class));

        List<Integer> integers = new ArrayList<>();

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);

        System.out.println(gson.toJson(integers));

    }


}

/**
 * 下面四个类的代码的setter/getter是使用Intellij IDEA自动生成的，仔细观察代码，你会发现以下规律：
 * <p>
 * 基本类型自动生成的getter和setter方法，名称都是isXXX()和setXXX()形式的。
 * 包装类型自动生成的getter和setter方法，名称都是getXXX()和setXXX()形式的。
 */
class Model1 {

    private Boolean isSuccess;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }
}

class Model2 {

    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

class Model3 implements Serializable {

    private static final long serialVersionUID = 5972785412767158362L;

    private boolean isSuccess;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Model3.class.getSimpleName() + "[", "]")
                .add("isSuccess=" + isSuccess)
                .toString();
    }
}

class Model4 {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
