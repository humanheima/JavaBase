package com.hm.algorithm.graphic;

import java.util.*;

/**
 * Crete by dumingwei on 2020-03-15
 * Desc: 拓扑排序
 * 链接：https://leetcode-cn.com/problems/course-schedule/solution/tuo-bu-pai-xu-by-liweiwei1419/
 */
public class TopologySolution {


    public static void main(String[] args) {
        //test1();
        test2();

    }

    private static void test1() {
        //这是定义了一个一行两列
        int[][] prerequisites = new int[][]{
                {1, 0}

        };
        System.out.println(canFinishBfs(2, prerequisites));
    }

    private static void test2() {
        //这是定义了一个四行两列
        int[][] prerequisites = new int[][]{
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2},

        };
        System.out.println(canFinishBfs(4, prerequisites));
        System.out.println(canFinishDfs(4, prerequisites));
    }

    /**
     * 广度优先
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinishBfs(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }

        // 特判
        int pLen = prerequisites.length;
        if (pLen == 0) {
            return true;
        }

        //入度，指向当前节点的节点个数
        int[] inDegree = new int[numCourses];
        //定义一个HashSet数组，键表示当前节点，value表示依赖当前节点的后继节点
        HashSet<Integer>[] adj = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new HashSet<>();
        }

        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
            adj[p[1]].add(p[0]);
        }


        Queue<Integer> queue = new LinkedList<>();

        // 首先加入入度为 0 的结点
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 记录已经出队的课程数量
        int cnt = 0;
        while (!queue.isEmpty()) {
            //获取并移除节点
            Integer top = queue.poll();
            cnt += 1;
            // 遍历当前出队节点的所有后继节点
            HashSet<Integer> integers = adj[top];
            for (int successor : integers) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.add(successor);
                }
            }
        }
        return cnt == numCourses;
    }

    /**
     * 深度优先
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinishDfs(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return false;
        }
        int plen = prerequisites.length;
        if (plen == 0) {
            return true;
        }
        int[] marked = new int[numCourses];

        // 初始化有向图 begin
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        // 初始化有向图 end
        // 有向图的 key 是前驱节点，value 是后继节点的集合
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, marked)) {
                // 注意方法的语义，如果图中存在环，表示课程任务不能完成，应该返回 false
                return false;
            }
        }
        // 在遍历的过程中，一直 dfs 都没有遇到已经重复访问的结点，就表示有向图中没有环
        // 所有课程任务可以完成，应该返回 true
        return true;
    }

    /**
     * 注意这个 dfs 方法的语义
     *
     * @param i      当前访问的课程结点
     * @param graph
     * @param marked 如果 == 1 表示正在访问中，如果 == 2 表示已经访问完了
     * @return true 表示图中存在环，false 表示访问过了，不用再访问了
     */
    private static boolean dfs(int i,
                               HashSet<Integer>[] graph,
                               int[] marked) {
        // 如果访问过了，就不用再访问了
        if (marked[i] == 1) {
            // 从正在访问中，到正在访问中，表示遇到了环
            return true;
        }

        if (marked[i] == 2) {
            // 表示在访问的过程中没有遇到环，这个节点访问过了
            return false;
        }
        // 走到这里，是因为初始化呢，此时 marked[i] == 0
        // 表示正在访问中
        marked[i] = 1;
        // 后继结点的集合
        HashSet<Integer> successorNodes = graph[i];

        for (Integer successor : successorNodes) {
            if (dfs(successor, graph, marked)) {
                // 层层递归返回 true ，表示图中存在环
                return true;
            }
        }
        // i 的所有后继结点都访问完了，都没有存在环，则这个结点就可以被标记为已经访问结束
        // 状态设置为 2
        marked[i] = 2;
        // false 表示图中不存在环
        return false;
    }

    public boolean canFinishTwo(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // Get all the courses with the indegree of 0.
        for (int i = 0; i < numCourses; i++)
            if (indegrees[i] == 0) queue.add(i);
        // BFS TopSort.
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for (int cur : adjacency.get(pre))
                if (--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }

}

