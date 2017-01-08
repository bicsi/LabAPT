package com.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by bicsi on 04.12.2016.
 */
public class ZSolver {
    public void Solve() throws Exception {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        int matrixSize = (1 << scanner.nextInt());
        int testCases = scanner.nextInt();
        while(testCases-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            writer.print(SolveRec(matrixSize, x, y));
            writer.println();
        }

        scanner.close();
        writer.close();
    }

    public void Test() throws Exception {
        int n = (1 << 2);
        for(int i = 1; i <= n; ++i)
            for(int j = 1; j <= n; ++j)
                System.out.println(SolveRec(n, i, j) + " ");
    }

    private int SolveRec(int n, int x, int y) throws Exception {
        if(n == 1) return 1;
        n /= 2;
        if(x <= n && y <= n) return SolveRec(n, x, y);
        if(x <= n && y > n) return n * n + SolveRec(n, x, y - n);
        if(x > n && y <= n) return 2 * n * n + SolveRec(n, x - n, y);
        if(x > n && y > n) return 3 * n * n + SolveRec(n, x - n, y - n);
        throw new Exception("Invalid parameters!");
    }
}
