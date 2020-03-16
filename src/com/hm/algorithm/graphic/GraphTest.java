package com.hm.algorithm.graphic;

import java.util.Scanner;

/**
 * Crete by dumingwei on 2020-03-16
 * Desc:
 */
public class GraphTest {

    public static void main(String[] args) {


        // initialGraph();

        Graph graph = new Graph();
        //Graph graph = new Graph(4, 4);
        initialGraph(graph);

    }


    /**
     * 根据用户输入的数据初始化一个图，以邻接表的形式构建!
     *
     * @param graph 生成的图
     */
    public static void initialGraph(Graph graph) {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入顶点数和边数：");
        graph.verNum = scan.nextInt();
        graph.edgeNum = scan.nextInt();

        graph.vertexArray = new Vertex[graph.verNum];
        System.out.println("请依次输入顶点名称：");
        for (int i = 0; i < graph.verNum; i++) {
            Vertex vertex = new Vertex();
            String name = scan.next();
            vertex.verName = name;
            vertex.nextNode = null;
            graph.vertexArray[i] = vertex;
        }

        System.out.println("请依次输入图的边：");
        for (int i = 0; i < graph.edgeNum; i++) {
            //其起始顶点
            String preV = scan.next();
            //结束顶点
            String folV = scan.next();

            Vertex v1 = getVertex(graph, preV);
            if (v1 == null) {
                System.out.println("输入边存在图中没有的顶点，程序退出！");
                return;
            }

            //下面代码是图构建的核心：链表操作
            Vertex v2 = new Vertex();
            v2.verName = folV;
            v2.nextNode = v1.nextNode;
            v1.nextNode = v2;

            //紧接着下面注释的代码加上便是构建无向图的，不加则是构建有向图的！
            //          Vertex reV2=getVertex(graph,folV);
            //          if(reV2==null)
            //              System.out.println("输入边存在图中没有的顶点！");
            //          Vertex reV1=new Vertex();
            //          reV1.verName=preV;
            //          reV1.nextNode=reV2.nextNode;
            //          reV2.nextNode=reV1;
        }
    }

    /**
     * 根据用户输入的string类型的顶点返回该顶点
     *
     * @param graph 图
     * @param str   输入数据
     * @return 返回一个顶点
     */
    public static Vertex getVertex(Graph graph, String str) {
        for (int i = 0; i < graph.verNum; i++) {
            if (graph.vertexArray[i].verName.equals(str)) {
                return graph.vertexArray[i];
            }
        }
        return null;
    }

}
