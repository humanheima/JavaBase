package com.hm.generic;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by dumingwei on 2017/12/2 0002.
 */
public class ContainerMethodDifferences {

    static Set<String> methodSet(Class<?> type) {
        Set<String> result = new TreeSet<>();
        for (Method method : type.getMethods()) {
            result.add(method.getName());
        }
        return result;
    }

    static void interfaces(Class<?> type) {
        System.out.println("Interfaces in " + type.getSimpleName() + ":");
        List<String> result = new ArrayList<>();
        for (Class<?> aClass : type.getInterfaces()) {
            result.add(aClass.getSimpleName());
        }
        System.out.println(result);
    }

    static Set<String> object = methodSet(Object.class);

    static {
        object.add("clone");
    }

    static void difference(Class<?> superset, Class<?> subset) {
        System.out.println(superset.getSimpleName() + " extends " + subset.getSimpleName() + " adds:");
        Set<String> comp = Sets.difference(methodSet(subset), methodSet(subset));
        comp.removeAll(object);
        System.out.println(comp);
        interfaces(superset);
    }

    public static void main(String[] args) {
        System.out.println("Collection:" + methodSet(Collection.class));
        interfaces(Collection.class);
        difference(Set.class, Collection.class);
        difference(HashSet.class, Set.class);
        difference(LinkedHashSet.class, HashSet.class);
        difference(TreeSet.class, Set.class);
        difference(List.class, Collection.class);
        difference(ArrayList.class, List.class);
        difference(LinkedList.class, List.class);
        difference(PriorityQueue.class, Queue.class);
        difference(HashMap.class, Map.class);
        difference(LinkedHashMap.class, HashMap.class);
        difference(SortedMap.class, Map.class);
        difference(TreeMap.class, Map.class);
    }

}
