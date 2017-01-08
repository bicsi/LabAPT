package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by bicsi on 04.12.2016.
 */
public class MountainSolver {
    public void Solve() throws IOException {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        // Read phase
        int noElements = scanner.nextInt();
        int[] heights = new int[noElements];
        for(int i = 0; i < noElements; ++i)
            heights[i] = scanner.nextInt();


        // Algorithm
        int step, ans = -1;
        for(step = 1; step <= noElements; step *= 2);
        for(step /= 2; step > 0; step /= 2)
            if(ans + step < noElements - 1 && heights[ans + step] < heights[ans + step + 1])
                ans += step;

        writer.print(heights[ans + 1]);

        scanner.close();
        writer.close();
    }
}
