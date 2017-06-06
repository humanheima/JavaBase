package com.hm.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class HashSetTest2 {

    public static void main(String[] args) {
        HashSet hs = new HashSet();
        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));
        System.out.println(hs);
        Iterator it = hs.iterator();
        R first = (R) it.next();
        first.count=-3;
        //再次输出hs，有重复元素
        System.out.println(hs);
        hs.remove(new R(-3));
        System.out.println(hs);
        System.out.println("hs是否包含count=-3的R对象:"+hs.contains(new R(-3)));
        System.out.println("hs是否包含count=-2的R对象:"+hs.contains(new R(-2)));
    }

    static class R {

        int count;

        public R(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "R[count:" + count + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj != null && obj.getClass() == R.class) {
                R r = (R) obj;
                return this.count == r.count;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return count;
        }
    }

}
