package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IntervalSolver {
    public class Interval implements Cloneable {
        public Integer a, b;
        public Interval(Integer a, Integer b) {
            this.a = a; this.b = b;
        }

        public Interval(Interval use) {
            this.a = use.a; this.b = use.b;
        }
    }

    public void Solve() throws IOException {
        Scanner in = new Scanner(new File("date.in"));
        PrintWriter out = new PrintWriter("date.out");

        int left = in.nextInt(), right = in.nextInt();

        int n = in.nextInt();
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            intervals.add(new Interval(in.nextInt(), in.nextInt()));
        }

        intervals.sort((lhs, rhs) -> {
            if (lhs.a.equals(rhs.a))
                return lhs.b.compareTo(rhs.b);
            return lhs.a.compareTo(rhs.a);
        });

        Interval use = new Interval(left, left - 1);
        int currentIndex = 0;

        List<Interval> answer = new ArrayList<>();

        while (use.b <= right) {
            Interval old = new Interval(use);
            while (currentIndex < n) {
                Interval interval = intervals.get(currentIndex);

                if(intervals.get(currentIndex).a > old.b + 1)
                    break;

                if(use.b < interval.b)
                    use = interval;
                currentIndex++;
            }
            if (use.equals(old)) {
                out.write("" + -1 + "\n");
                return;
            } else {
                answer.add(use);
            }
        }

        // Output answer
        for(Interval i : answer) {
            out.write(i.a + " " + i.b + "\n");
        }
        in.close();
        out.close();
    }
}
