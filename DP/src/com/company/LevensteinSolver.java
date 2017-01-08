package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bicsi on 08.01.2017.
 */
public class LevensteinSolver {
    public void solve() throws Exception {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        char[] a = scanner.next().toCharArray();
        char[] b = scanner.next().toCharArray();

        //System.out.println(a);
        //System.out.println(b);

        int insCost = scanner.nextInt(),
            remCost = scanner.nextInt(),
            repCost = scanner.nextInt();

        int[][] DP = new int[a.length + 1][b.length + 1];
        for(int i = 0; i <= a.length; ++i)
            for(int j = 0; j <= b.length; ++j) {
                if(i == 0 && j == 0) continue;

                DP[i][j] = (i > 0 && j > 0 && a[i - 1] == b[j - 1])
                        ? DP[i - 1][j - 1]
                        : 1000 * 1000 * 1000;

                // Replace
                if(i > 0 && j > 0) DP[i][j] = Math.min(DP[i][j], DP[i - 1][j - 1] + repCost);
                // Insert
                if(j > 0) DP[i][j] = Math.min(DP[i][j], DP[i][j - 1] + insCost);
                // Erase
                if(i > 0) DP[i][j] = Math.min(DP[i][j], DP[i - 1][j] + remCost);
            }

        writer.println(DP[a.length][b.length]);

        List<String> answer = new ArrayList<>();
        int i = a.length, j = b.length;
        while(i > 0 || j > 0) {
            if(i > 0 && j > 0 && DP[i][j] == DP[i - 1][j - 1] && a[i - 1] == b[j - 1]) {
                answer.add("keep " + a[i - 1]);
                --i; --j;
                continue;
            }
            if(i > 0 && j > 0 && DP[i][j] == DP[i - 1][j - 1] + repCost) {
                answer.add("replace " + a[i - 1] + "-" + b[j - 1]);
                --i; --j;
                continue;
            }
            if(j > 0 && DP[i][j] == DP[i][j - 1] + insCost) {
                answer.add("insert " + b[j - 1]);
                --j;
                continue;
            }
            if(i > 0 && DP[i][j] == DP[i - 1][j] + remCost) {
                answer.add("erase " + a[i - 1]);
                --i;
                continue;
            }
        }

        Collections.reverse(answer);
        answer.forEach(writer::println);
        scanner.close();
        writer.close();
    }
}
