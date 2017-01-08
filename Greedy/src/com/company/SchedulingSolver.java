package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bicsi on 20.11.2016.
 */
public class SchedulingSolver {
    public class Activity {
        Integer duration, deadline, idx;
        public Activity(Integer duration, Integer deadline, int idx) {
            this.duration = duration;
            this.deadline = deadline;
            this.idx = idx;
        }
    }

    public void Solve() throws IOException {
        Scanner in = new Scanner(new File("date.in"));
        PrintWriter out = new PrintWriter("date.out");

        int n = in.nextInt();
        List<Activity> activities = new ArrayList<>();
        for(int i = 0; i < n; ++i)
            activities.add(new Activity(in.nextInt(), in.nextInt(), i));

        // Same as sorting by deadline, only much safer
        activities.sort((lhs, rhs) -> {
            Integer lval = Math.max(lhs.duration - lhs.deadline, lhs.duration + rhs.duration - rhs.deadline);
            Integer rval = Math.max(rhs.duration - rhs.deadline, lhs.duration + rhs.duration - lhs.deadline);
            return lval.compareTo(rval);
        });

        // Just in case
        int lastDeadline = -1;
        for(Activity act : activities) {
            if(act.deadline < lastDeadline)
                System.out.println("This will never show up, hopefully.");
            lastDeadline = act.deadline;
        }

        int endTime = 0;
        int maxDelay = 0;
        for (Activity act : activities) {
            int oldEnd = endTime;
            endTime += act.duration;
            int delay = Math.max(endTime - act.deadline, 0);
            out.println(String.format("activitatea %d: intervalul %d %d intarziere %d",
                    act.idx + 1, oldEnd, endTime, delay));
            maxDelay = Math.max(maxDelay, delay);
        }

        out.println(String.format("Intarziere planificata %d", maxDelay));
        in.close();
        out.close();
    }
}
