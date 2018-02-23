package com.heneli.copia.schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryMatchUtil {

    public static boolean matchOne(int provide, int accept) {
        return (provide & accept) == provide;
    }

    public static boolean matchTwo(int provide, int accept1, int accept2) {
        boolean smallerMatch = matchOne(provide, accept1)
                || matchOne(provide, accept2);
        if (smallerMatch) return false;

        return (provide & (accept1 | accept2)) == provide;
    }

    public static boolean matchThree(int provide, int accept1, int accept2, int accept3) {
        boolean smallerMatch = matchTwo(provide, accept1, accept2)
                || matchTwo(provide, accept1, accept3)
                || matchTwo(provide, accept2, accept3);
        if (smallerMatch) return false;

        return  (provide & (accept1 | accept2 | accept3)) == provide;
    }


    public static boolean matchFour(int provide, int accept1, int accept2, int accept3, int accept4) {
        boolean smallerMatch = matchThree(provide, accept1, accept2, accept3)
                || matchThree(provide, accept1, accept2, accept4)
                || matchThree(provide, accept1, accept3, accept4)
                || matchThree(provide, accept2, accept3, accept4);

        if (smallerMatch) return false;

//        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3, accept4))
//                .stream()
//                .filter(c -> matchThree(provide, c.get(0), c.get(1), c.get(2)))
//                .collect(Collectors.toList());
//
//        if (smallerMatches.size() != 0) return false;

        return  (provide & (accept1 | accept2 | accept3 | accept4)) == provide;
    }

    public static boolean matchFive(int provide, int accept1, int accept2, int accept3, int accept4, int accept5) {
        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3, accept4, accept5))
                .stream()
                .filter(c -> matchFour(provide, c.get(0), c.get(1), c.get(2), c.get(3)))
                .collect(Collectors.toList());

        if (smallerMatches.size() != 0) return false;

        return  (provide & (accept1 | accept2 | accept3 | accept4 | accept5)) == provide;
    }

    public static boolean matchSix(int provide, int accept1, int accept2, int accept3, int accept4, int accept5, int accept6) {
        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3, accept4, accept5, accept6))
                .stream()
                .filter(c -> matchFive(provide, c.get(0), c.get(1), c.get(2), c.get(3), c.get(4)))
                .collect(Collectors.toList());

        if (smallerMatches.size() != 0) return false;

        return  (provide & (accept1 | accept2 | accept3 | accept4 | accept5 | accept6)) == provide;
    }

    // K-combinations where k = n - 1
    public static List<List<Integer>> findMatchCombinations(List<Integer> input) {
        if (input.size() < 3)
            throw new IllegalArgumentException("K-combinations, where k is at least 1, require a list of at least 2 elements.");
        int k = input.size() - 1;

        List<List<Integer>> subsets = new ArrayList<>();

        int[] s = new int[k];

        if (k <= input.size()) {
            for (int i = 0; (s[i] = i) < k - 1; i++);
            subsets.add(getSubset(input, s));
            for(;;) {
                int i;
                for (i = k - 1; i >= 0 && s[i] == input.size() - k + i; i--);
                if (i < 0) {
                    break;
                }
                s[i]++;
                for (++i; i < k; i++) {
                    s[i] = s[i - 1] + 1;
                }
                subsets.add(getSubset(input, s));
            }
        }

        return subsets;
    }

    // generate subset by index sequence
    public static List<Integer> getSubset(List<Integer> input, int[] subset) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < subset.length; i++)
            result.add(i, input.get(subset[i]));
        return result;
    }

}
