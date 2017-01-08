package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * Created by bicsi on 04.12.2016.
 */
public class ClosestPairSolver {
    public void Solve() throws Exception {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        int n = scanner.nextInt();

        List<Point> P = new ArrayList<>(n);
        for(int i = 0; i < n; ++i) {
            P.add(new Point(scanner.nextInt(), scanner.nextInt()));
        }
        P.sort((lhs, rhs) -> lhs.x.compareTo(rhs.x));

        Answer ans = SolveRec(P, 0, n);

        writer.print("Distance is: " + Math.sqrt(ans.getSqDist()));
        writer.println();
        writer.print("Points are: " + ans.p1 + " " + ans.p2);
        writer.println();

        scanner.close();
        writer.close();
    }

    private Answer SolveRec(List<Point> points, int b, int e) throws Exception {
        if(e - b == 1) return null;

        int m = (b + e) / 2;
        Answer ans = minAns(SolveRec(points, b, m), SolveRec(points, m, e));

        int refX = points.get(m).x;

        // Merge points by y coordinate
        Merge(points, b, m, e);

        long dist = ans == null ? Long.MAX_VALUE : ans.getSqDist();

        List<Point> P = new ArrayList<>();
        for(int i = b; i < e; ++i) {
            long dx = points.get(i).x - refX;
            if(dx * dx <= dist)
                P.add(points.get(i));
        }


        int jSmall = 0, jLarge = 0;
        for(int i = 0; i < P.size(); ++i) {
            while(jSmall < P.size()) {
                long dy = P.get(i).y - P.get(jSmall).y;
                if(dy >= 0 && dy * dy >= dist)
                    ++jSmall;
                else break;
            }
            while(jLarge < P.size()) {
                int dy = P.get(jLarge).y - P.get(i).y;
                if(dy <= 0 || dy * dy <= dist)
                    ++jLarge;
                else break;
            }

            // Maximum 8 iterations
            if(jLarge - jSmall > 8)
                throw new Exception("Iteration limit exceeded in recursive call!!");

            for(int j = jSmall; j < jLarge; ++j) {
                if(i == j) continue;
                ans = minAns(ans, new Answer(P.get(i), P.get(j)));
            }
        }

        return ans;
    }

    private Answer minAns(Answer lhs, Answer rhs) {
        if(lhs == null) return rhs;
        if(rhs == null) return lhs;
        if(lhs.getSqDist() < rhs.getSqDist()) return lhs;
        return rhs;
    }

    private void Merge(List<Point> points, int b, int m, int e) {
        List<Point> aux = new ArrayList<>();

        int j = m;
        for(int i = b; i < m; ++i) {
            while(j < e && points.get(j).y < points.get(i).y)
                aux.add(points.get(j++));
            aux.add(points.get(i));
        }
        while(j < e) aux.add(points.get(j++));

        j = b;
        for(Point p : aux)
            points.set(j++, p);
    }

    class Point {
        public Integer x, y;
        public Point(Integer x, Integer y) {
            this.x = x; this.y = y;
        }
        public long sqDistTo(Point oth) {
            long dx = x - oth.x;
            long dy = y - oth.y;
            return dx * dx + dy * dy;
        }

        @Override
        public String toString() {
            return "(" + x + " " + y + ")";
        }
    }

    private class Answer {
        public Point p1, p2;
        public Answer(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public BigInteger add(BigInteger a, BigInteger b) {
            a = new Scanner().
        }

        public Long getSqDist() {
            return p1.sqDistTo(p2);
        }
    }
}
