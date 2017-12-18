package com.hm.generic;

import java.util.EnumSet;
import java.util.Set;

import static com.hm.generic.Watercolors.*;

/**
 * Created by dumingwei on 2017/12/2 0002.
 */
public class WaterColorSets {

    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);

        System.out.println(set1);
        System.out.println(set2);
        System.out.println("union(set1,set2):");
        System.out.println(Sets.union(set1, set2));
        System.out.println("intersection(set1,set2):");
        System.out.println(Sets.intersection(set1, set2));
        System.out.println("difference(set1,set2):");
        System.out.println(Sets.difference(set1, set2));
        System.out.println("complement(set1,set2):");
        System.out.println(Sets.complement(set1, set2));
    }


}
