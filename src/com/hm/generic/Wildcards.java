package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/12 0012.
 */
public class Wildcards {

    static void rawArgs(Holder holder, Object arg) {
        holder.set(arg);
        holder.set(new Wildcards());

        //T t= holder.get();
        Object obj = holder.get();
    }

    static void unboundedArgs(Holder<?> holder, Object arg) {
        //holder.set(arg);//error
        //holder.set(new Wildcards());

        //T t= holder.get();//error
        Object obj = holder.get();
    }

    static <T> T exact(Holder<T> holder) {
        T t = holder.get();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg) {
        holder.set(arg);
        T t = holder.get();
        return t;
    }

    static <T> T wildSubType(Holder<? extends T> holder, T arg) {
        //holder.set(arg);//error
        T t = holder.get();
        return t;
    }

    static <T> void wildSuperType(Holder<? super T> holder, T arg) {
        holder.set(arg);
        //T t = holder.get();//error
        Object obj = holder.get();
    }

    public static void main(String[] args) {
        Holder raw = new Holder<Long>();
        raw = new Holder();

        Holder<Long> qualified = new Holder<>();
        Holder<?> unbounded = new Holder<>();
        Holder<? extends Long> bounded = new Holder<>();

        Long lng = 1L;
        rawArgs(raw, lng);
        rawArgs(qualified, lng);
        rawArgs(unbounded, lng);
        rawArgs(bounded, lng);

        unboundedArgs(raw, lng);
        unboundedArgs(qualified, lng);
        unboundedArgs(unbounded, lng);
        unboundedArgs(bounded, lng);

        Object r1 = exact(raw);
        Long r2 = exact(qualified);

        Object r3 = exact(unbounded);

        Long r4 = exact(bounded);

        Long r5 = exact2(raw, lng);

        Long r6 = exact2(qualified, lng);
        //Long r7 = exact2(unbounded, lng);//error

        //Long r8 = exact2(bounded, lng);

        Long r9 = wildSubType(raw, lng);
        Long r10 = wildSubType(qualified, lng);
        Object r11 = wildSubType(unbounded, lng);

        Long r12 = wildSubType(bounded, lng);

        wildSuperType(raw,lng);

        wildSuperType(qualified,lng);

        //wildSuperType(unbounded,lng);

        //wildSuperType(bounded,lng);

    }


}
