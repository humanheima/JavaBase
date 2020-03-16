package com.hm.algorithm.graphic;

import java.util.ArrayList;
import java.util.List;

/**
 * Crete by dumingwei on 2020-03-16
 * Desc: 拓扑排序，返回数组
 * <p>
 * https://mp.weixin.qq.com/s/tGJ0MHSoV-XILS1JaTn7aA
 * https://mp.weixin.qq.com/s/r2Sf0I0JarpXmvBlX_1LTg
 */
public class TopologySolutionTwo {

    public static void main(String[] args) {

        test2();
    }

    private static void test1() {
        //这是定义了一个一行两列
        int[][] prerequisites = new int[][]{
                {1, 0}

        };
        int[] order = findOrder(2, prerequisites);
        for (int i : order) {
            System.out.println(i);
        }
    }

    private static void test2() {
        //这是定义了一个四行两列
        int[][] prerequisites = new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2},

        };
        int[] order = findOrder(4, prerequisites);
        for (int i : order) {
            System.out.println(i);
        }
    }


    /**
     * 深度优先
     * <p>
     * （1）对图执行深度优先搜索。
     * （2）在执行深度优先搜索时，若某个顶点不能继续前进（没有后继节点），即顶点的出度为0，则将此顶点入栈。
     * （3）最后得到栈中顺序的逆序即为拓扑排序顺序。
     *
     * @param numCourses    活动顶点个数
     * @param prerequisites 节点之间的依赖关系，{1, 0}，1依赖于0
     * @return
     */
    private static int[] findOrder(int numCourses, int[][] prerequisites) {

        List<Integer> result = new ArrayList<>();

        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; ++i) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] isLooped = new boolean[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            if (!visited[i] && !dfs(graph, visited, isLooped, i, result)) {
                return new int[0];
            }
        }

        int[] output = new int[numCourses];
        int index = 0;
        for (int i : result) {
            output[index++] = i;
        }

        return output;
    }

    private static boolean dfs(List<Integer>[] graph,
                               boolean[] visited,
                               boolean[] isLooped,
                               int curCourse,
                               List<Integer> result) {
        if (isLooped[curCourse]) {
            return false;
        }

        isLooped[curCourse] = true;
        for (int i : graph[curCourse]) {
            if (!visited[i] && !dfs(graph, visited, isLooped, i, result)) {
                return false;
            }
        }

        isLooped[curCourse] = false;
        visited[curCourse] = true;
        result.add(curCourse);

        return true;
    }


}
