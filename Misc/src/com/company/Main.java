package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        testCosineSimilarity();
        testGraphTraversal();
        testQueue();
        testBST();
        testBST2();
    }

    private static void testBST() throws FileNotFoundException {
        MyBST<Integer> bst = new MyBST<>();
        Scanner scanner = new Scanner(new File("input_bst.txt"));
        while(scanner.hasNext()) {
            int value = scanner.nextInt();
            bst.add(value);
        }
        System.out.println(bst);
    }

    private static void testBST2() {
        class X implements Comparable<X> {
            int value;
            public X(int a) {
                value = a;
            }
            @Override
            public int compareTo(X x) {
                Integer a = value / 10;
                Integer b = x.value / 10;
                return b.compareTo(a);
            }

            @Override
            public String toString() {
                return "$" + value + "$";
            }
        }
        class Y extends X {
            int value2;
            public Y(int a, int b) {
                super(a);
                value2 = b;
            }

            @Override
            public String toString() {
                return "~" + value + " " + value2 + "~";
            }
        }

        MyBST<X> bst2 = new MyBST<>();
        for(int i = 0; i < 20; ++i) {
            if(i % 3 == 0) {
                bst2.add(new Y(i * 5, i * 2));
            } else {
                bst2.add(new X(i * 5));
            }
        }
        System.out.println(bst2);
    }

    private static void testQueue() {
        Queue<Integer> queue = new MyQueue<>();
        queue.add(2);
        queue.add(5);
        queue.add(10);
        for(int x : queue) System.out.print(x + " ");
        System.out.println();
        queue.poll();
        for(int x : queue) System.out.print(x + " ");
        System.out.println();
    }

    private static String readString(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner( new File(fileName));
        String ret = scanner.useDelimiter("\\A").next();
        scanner.close();
        return ret;
    }

    private static void testGraphTraversal() {
        GraphUtils<Integer> utils = new GraphUtils<>();
        GraphUtils.Graph<Integer> graph = new GraphUtils.Graph<>();
        for(int i = 0; i < 5; ++i)
            graph.addNode(i + 1);
        graph.addEdge(2, 5);
        graph.addEdge(1, 3);
        graph.addEdge(5, 2);
        graph.addEdge(2, 1);

        List<Integer> ret = utils.breadthFirst(graph.getNode(2));
        for (int x : ret) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    private static void testCosineSimilarity() throws FileNotFoundException {
        SimilarityCalculator s = new SimilarityCalculator();
        System.out.println(s.compute(readString("input1.txt"), readString("input2.txt")));
    }
}
