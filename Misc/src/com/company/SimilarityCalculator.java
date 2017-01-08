package com.company;

import java.util.HashMap;

/**
 * Created by bicsi on 23.10.2016.
 */
public class SimilarityCalculator {
    public double compute(String argA, String argB) {
        class FreqPair {
            public int a = 0, b = 0;
        }
        HashMap<String, FreqPair> counter = new HashMap<String, FreqPair>();

        String[] wordsA = argA.split(" |,|.");
        String[] wordsB = argB.split(" |,|.");

        for (String word : wordsA) {
            if(!counter.containsKey(word))
                counter.put(word, new FreqPair());
            counter.get(word).a += 1;
        }
        for(String word : wordsB) {
            if(!counter.containsKey(word))
                counter.put(word, new FreqPair());
            counter.get(word).b += 1;
        }

        long up = 0, downA = 0, downB = 0;
        for(FreqPair p : counter.values()) {
            up += p.a * p.b;
            downA += p.a * p.a;
            downB += p.b * p.b;
        }

        return 1.0 * up / Math.sqrt(downA * downB);
    }
}
