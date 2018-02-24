package com.heneli.copia.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinMatchers {

    public static boolean isOnlyOneToOneBinMatch(int provide, int accept1) {
        return (provide & (accept1)) == provide;
    }

    public static boolean isOnlyOneToTwoBinMatch(int provide, int accept1, int accept2) {
        return (provide & (accept1 | accept2)) == provide;
    }

    public static boolean isOnlyOneToThreeBinMatch(int provide, int accept1, int accept2, int accept3) {
        return  (provide & (accept1 | accept2 | accept3)) == provide;
    }

    public static boolean isOnlyOneToFourBinMatch(int provide, int accept1, int accept2, int accept3, int accept4) {
        return  (provide & (accept1 | accept2 | accept3 | accept4)) == provide;
    }

    public static boolean isOnlyOneToFiveBinMatch(int provide, int accept1, int accept2, int accept3, int accept4, int accept5) {
        return  (provide & (accept1 | accept2 | accept3 | accept4 | accept5)) == provide;
    }

    public static boolean isOnlyOneToSixBinMatch(int provide, int accept1, int accept2, int accept3, int accept4, int accept5, int accept6) {
        return  (provide & (accept1 | accept2 | accept3 | accept4 | accept5)) == provide;
    }



    /* ===================================================================================== */

    public static boolean isOneToOneBinMatch(int provide, int accept) {
        return isOnlyOneToOneBinMatch(provide, accept);
    }

    public static boolean isOneToTwoBinMatch(int provide, int accept1, int accept2) {
        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2))
                .stream()
                .filter(c -> isOnlyOneToOneBinMatch(provide, c.get(0)))
                .collect(Collectors.toList());

        if (smallerMatches.size() != 0)
            return false;
        else
            return isOnlyOneToTwoBinMatch(provide, accept1, accept2);
    }

    public static boolean isOneToThreeBinMatch(int provide, int accept1, int accept2, int accept3) {
        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3))
                .stream()
                .filter(c -> isOnlyOneToOneBinMatch(provide, c.get(0)))
                .filter(c -> isOnlyOneToTwoBinMatch(provide, c.get(0), c.get(1)))
                .collect(Collectors.toList());

        if (smallerMatches.size() != 0) return false;
        else return  (provide & (accept1 | accept2 | accept3)) == provide;
    }


    public static boolean isOneToFourBinMatch(int provide, int accept1, int accept2, int accept3, int accept4) {
        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3, accept4))
                .stream()
                .filter(c -> isOnlyOneToOneBinMatch(provide, c.get(0)))
                .filter(c -> isOnlyOneToTwoBinMatch(provide, c.get(0), c.get(1)))
                .filter(c -> isOnlyOneToThreeBinMatch(provide, c.get(0), c.get(1), c.get(2)))
                .collect(Collectors.toList());

        if (smallerMatches.size() != 0) return false;
        else return  (provide & (accept1 | accept2 | accept3 | accept4)) == provide;
    }

    public static boolean isOneToFiveBinMatch(int provide, int accept1, int accept2, int accept3, int accept4, int accept5) {
        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3, accept4, accept5))
                .stream()
                .filter(c -> isOnlyOneToOneBinMatch(provide, c.get(0)))
                .filter(c -> isOnlyOneToTwoBinMatch(provide, c.get(0), c.get(1)))
                .filter(c -> isOnlyOneToThreeBinMatch(provide, c.get(0), c.get(1), c.get(2)))
                .filter(c -> isOnlyOneToFourBinMatch(provide, c.get(0), c.get(1), c.get(2), c.get(3)))
                .collect(Collectors.toList());

        if (smallerMatches.size() != 0) return false;

        return  (provide & (accept1 | accept2 | accept3 | accept4 | accept5)) == provide;
    }

//    public static boolean isOneToSixBinMatch(int provide, int accept1, int accept2, int accept3, int accept4, int accept5, int accept6) {
//        List smallerMatches = findMatchCombinations(Arrays.asList(accept1, accept2, accept3, accept4, accept5, accept6))
//                .stream()
//                .filter(c -> isOneToFiveBinMatch(provide, c.get(0), c.get(1), c.get(2), c.get(3), c.get(4)))
//                .collect(Collectors.toList());
//
//        if (smallerMatches.size() != 0) return false;
//
//        return  (provide & (accept1 | accept2 | accept3 | accept4 | accept5 | accept6)) == provide;
//    }

    // K-combinations where k = (n - 1) .. (1)
    public static List<List<Integer>> findMatchCombinations(List<Integer> input) {
        if (input.size() < 2)
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
