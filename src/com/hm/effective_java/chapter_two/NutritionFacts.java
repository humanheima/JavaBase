package com.hm.effective_java.chapter_two;

/**
 * Created by dumingwei on 2017/9/5.
 * 遇到多个构造器参数时，考虑使用构建器
 */
public class NutritionFacts {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int carbohydrate;
    private final int sodium;

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        carbohydrate = builder.carbohydrate;
        sodium = builder.sodium;
    }

    public static class Builder {

        //required parameters
        private final int servingSize;
        private final int servings;

        //optional parameters
        private int calories = 0;
        private int fat = 0;
        private int carbohydrate = 0;
        private int sodium = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }

    }
}
