package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by bicsi on 08.01.2017.
 */
public class DominoSolver {
    public void solve() throws Exception {
        Scanner scanner = new Scanner(new File("date.in"));
        PrintWriter writer = new PrintWriter("date.out");

        int dominoCount = scanner.nextInt();
        Domino[] dominoes = new Domino[dominoCount];
        int maxValue = 0;

        for(int i = 0; i < dominoCount; ++i) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            dominoes[i] = new Domino(a, b);
            maxValue = Math.max(maxValue, a);
            maxValue = Math.max(maxValue, b);
        }

        int[] dp = new int[dominoCount + 1];
        int[] longest = new int[maxValue + 1];
        int[] index = new int[maxValue + 1];
        int[] parent = new int[dominoCount + 1];
        BigInteger[] count = new BigInteger[maxValue + 1];

        for(int i = 0; i <= maxValue; ++i) {
            index[i] = -1;
            count[i] = new BigInteger("1");
        }

        int bestLen = 0;
        for(int i = 0; i < dominoCount; ++i) {
            Domino currDom = dominoes[i];
            dp[i] = longest[currDom.first] + 1;
            parent[i] = index[currDom.first];

            if(longest[currDom.second] < dp[i]) {
                longest[currDom.second] = dp[i];
                index[currDom.second] = i;
                count[currDom.second] = count[currDom.first];
            }
            else if(longest[currDom.second] == dp[i]) {
                count[currDom.second] = count[currDom.second].add(count[currDom.first]);
            }

            bestLen = Math.max(bestLen, dp[i]);
        }

        List<Domino> answer = new ArrayList<>();
        for(int i = 0; i < dominoCount; ++i) {
            if(dp[i] == bestLen) {
                while(i >= 0) {
                    answer.add(dominoes[i]);
                    i = parent[i];
                }
                break;
            }
        }
        Collections.reverse(answer);


        if(answer.size() == 0)
            throw new RuntimeException();

        BigInteger cnt = new BigInteger("0");
        for(int i = 0; i <= maxValue; ++i)
            if(longest[i] == bestLen)
                cnt = cnt.add(count[i]);

        for (Domino dom : answer) {
            writer.println(dom.first + " " + dom.second);
        }
        writer.println(cnt);

        writer.close();
        scanner.close();
    }

    class Domino {
        public int first, second;
        public Domino(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
