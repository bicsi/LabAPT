package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by bicsi on 08.01.2017.
 */
public class ScheduleSolver {
    public void solve() throws Exception {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        int n = scanner.nextInt();
        Interval[] intervals = new Interval[n];
        for(int i = 0; i < n; ++i) {
            intervals[i] = new Interval(
                    scanner.nextInt(),
                    scanner.nextInt(),
                    scanner.nextInt()
            );
        }

        Arrays.sort(intervals, (a, b) -> a.stop - b.stop);
        int dp[] = new int[n + 1];
        for(int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1];
            int seek = intervals[i - 1].start;

            Interval tmp = new Interval(0, seek, 0);
            int x = Arrays.binarySearch(intervals, tmp, (a, b) -> a.stop - b.stop);
            if(x < 0) x = -(x + 1);

            dp[i] = Math.max(dp[i], dp[x] + intervals[i - 1].value);
        }

        writer.println(dp[n]);

        int i = n;
        List<Interval> answer = new ArrayList<>();
        while(i > 0) {
            if(dp[i] == dp[i - 1]) {
                --i;
            } else {
                int seek = intervals[i - 1].start;

                Interval tmp = new Interval(0, seek, 0);
                int x = Arrays.binarySearch(intervals, tmp, (a, b) -> a.stop - b.stop);
                if(x < 0) x = -(x + 1);

                if(dp[i] != dp[x] + intervals[i - 1].value)
                    throw new RuntimeException();

                answer.add(intervals[i - 1]);
                i = x;
            }
        }

        Collections.reverse(answer);
        for(Interval interval : answer) {
            writer.println(interval.start + " " + interval.stop);
        }

        scanner.close();
        writer.close();
    }

    class Interval {
        public int start, stop, value;
        public Interval(int start, int stop, int value) {
            this.start = start;
            this.stop = stop;
            this.value = value;
        }
    }
}
