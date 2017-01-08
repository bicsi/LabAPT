package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by bicsi on 20.11.2016.
 */
public class PartitionSolver {

    public void Solve() throws IOException {
        Scanner in = new Scanner(new File("date.in"));
        PrintWriter out = new PrintWriter("date.out");

        int n = in.nextInt();

        List<Integer> array = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            array.add(in.nextInt());

        TreeMap<MapKey, List<Integer>> map = new TreeMap<>();

        for(int pos = 0; pos < array.size(); ++pos) {
            int value = array.get(pos);
            MapKey key = new MapKey(pos, value);
            MapKey lowKey = map.ceilingKey(key);
            List<Integer> list;

            if(lowKey == null) {
                list = new ArrayList<>();
            } else {
                list = map.get(lowKey);
                map.remove(lowKey);
            }

            list.add(value);
            map.put(key, list);
        }

        out.println(map.size());
        map.values().forEach(l -> {
            for(Integer elem : l)
                out.print(elem + " ");
            out.println();
        });

        out.println("Sorting...");
        PriorityQueue<MapKey> pq = new PriorityQueue<>();

        List<List<Integer>> lists = map.values().stream().collect(Collectors.toList());
        for(int pos = 0; pos < lists.size(); ++pos) {
            pq.add(new MapKey(pos, lists.get(pos).get(lists.get(pos).size() - 1)));
        }

        List<Integer> sorted = new ArrayList<>();
        while(!pq.isEmpty()) {
            MapKey now = pq.poll();
            sorted.add(now.value);

            lists.get(now.pos).remove(lists.get(now.pos).get(lists.get(now.pos).size() - 1));
            if(!lists.get(now.pos).isEmpty()) {
                pq.add(new MapKey(now.pos, lists.get(now.pos).get(lists.get(now.pos).size() - 1)));
            }
        }

        for(Integer x : sorted)
            out.print(x + " ");
        out.println();

        in.close();
        out.close();
    }

    private class MapKey implements Comparable {
        Integer pos, value;

        public MapKey(Integer pos, Integer value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            MapKey oth = (MapKey) o;
            if(value.equals(oth.value))
                return pos.compareTo(oth.pos);
            return value.compareTo(oth.value);
        }
    }
}
