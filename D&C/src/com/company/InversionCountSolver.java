package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by bicsi on 04.12.2016.
 */
public class InversionCountSolver {
    public void Solve() throws Exception {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        int n = scanner.nextInt();
        int[] array = new int[n];
        for(int i = 0; i < n; ++i)
            array[i] = scanner.nextInt();

        writer.print(SolveRec(array, 0, n));
        writer.println();

        scanner.close();
        writer.close();
    }

    private void Merge(int[] array, int b, int m, int e) {
        int[] aux = new int[e - b];
        int pos = 0;

        int i = b, j = m;

        while(i < m) {
            while(j < e && array[j] < array[i])
                aux[pos++] = array[j++];
            aux[pos++] = array[i++];
        }
        while(j < e)
            aux[pos++] = array[j++];

        for(i = e - 1; i >= b; --i)
            array[i] = aux[--pos];
    }

    private long SolveRec(int[] array, int b, int e) {
        if(e - b == 1) return 0;

        int m = (b + e) / 2;

        long ret = SolveRec(array, b, m) + SolveRec(array, m, e);

        int j = m;
        for(int i = b; i < m; ++i) {
            while(j < e && 2 * array[j] < array[i])
                ++j;
            ret += j - m;
        }
        Merge(array, b, m, e);

        return ret;
    }
}
