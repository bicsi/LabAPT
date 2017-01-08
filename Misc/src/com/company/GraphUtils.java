package com.company;

import java.util.*;

/**
 * Created by bicsi on 23.10.2016.
 */
public class GraphUtils<T> {
    public static class GraphNode<T> {
        public List<GraphNode<T>> adjList;
        public T info;

        public GraphNode(T arg) {
            info = arg;
            adjList = new ArrayList<>();
        }
    }
    public static class Graph<T> {
        private Map<T, GraphNode<T>> graph = new HashMap<>();

        public void addNode(T info) {
            graph.put(info, new GraphNode<>(info));
        }
        public void addEdge(T from, T to) {
            getNode(from).adjList.add(getNode(to));
        }
        public GraphNode<T> getNode(T key) {
            return graph.get(key);
        }
    }

    public List<T> breadthFirst(GraphNode<T> startVertex) {
        Queue<GraphNode<T>> queue = new MyQueue<>();
        Set<GraphNode<T>> visited = new HashSet<>();
        List<T> ret = new ArrayList<>();

        queue.add(startVertex);
        visited.add(startVertex);
        while(!queue.isEmpty()) {
            GraphNode<T> front = queue.poll();
            ret.add(front.info);
            for(GraphNode<T> adj : front.adjList) {
                if(!visited.contains(adj)) {
                    queue.add(adj);
                    visited.add(adj);
                }
            }
        }

        return ret;
    }
}
