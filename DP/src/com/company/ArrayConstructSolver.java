package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by bicsi on 08.01.2017.
 */
public class ArrayConstructSolver {
        public void solve() throws Exception {
            Scanner scanner = new Scanner(new File("date.in"));
            PrintWriter writer = new PrintWriter("date.out");

            int n = scanner.nextInt();
            int k = scanner.nextInt();


            boolean[][] dp = new boolean[n + 1][k + 1];
            List<Integer>[] lists = new ArrayList[n];

            dp[0][0] = true;
            scanner.nextLine();
            for(int i = 1; i <= n; ++i) {
                String[] splitted = scanner.nextLine().split(" ");

                lists[i - 1] = new ArrayList<>();
                for(String s : splitted) {
                    int no = Integer.parseInt(s);
                    lists[i - 1].add(no);

                    for(int j = 0; j + no <= k; ++j)
                        dp[i][j + no] |= dp[i - 1][j];
                }
            }

            if(dp[n][k] == false) {
                writer.println("No solution.");
            } else {
                int[] answer = new int[n];
                for(int i = n - 1; i >= 0; --i) {
                    for(int x : lists[i]) {
                        if(k >= x && dp[i][k - x]) {
                            k -= x;
                            answer[i] = x;
                            break;
                        }
                    }
                }


                if(k != 0)
                    throw new RuntimeException();

                for(int x : answer)
                    writer.print(x + " ");
                writer.println();
            }


            scanner.close();
            writer.close();
        }
}
