package com.hm.effective_java.chapter_two;

import com.hm.effective_java.chapter_two.NutritionFacts;

/**
 * Created by dumingwei on 2017/9/5.
 */
public class NutritionFactsTest {

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {


        NutritionFacts facts = new NutritionFacts.Builder(240, 8)
                .calories(299).carbohydrate(20).fat(20).sodium(30).build();
    }
}
