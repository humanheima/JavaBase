package com.hm.generic.erase;

/**
 * Created by dumingwei on 2021/4/11.
 * <p>
 * Desc:
 */
public class EraseMainTest2 {

    public static void main(String[] args) {
        DeveloperContainer<AbsDeveloper> container = new DeveloperContainer<>();

        AndroidDeveloper androidDeveloper = new AndroidDeveloper("android", 110);

        container.setContained(androidDeveloper);

        AbsDeveloper developer = container.getContained();

        System.out.println(developer);


        JavaDeveloper javaDeveloper = new JavaDeveloper("Java", 110);

        container.setContained(javaDeveloper);

        AbsDeveloper developer1 = container.getContained();

        System.out.println(developer1);
    }
}
