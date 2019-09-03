import java.util.*;
import java.util.stream.Collectors;

public class ClosestIntegersSortingUtil {

    /**
     *
     * @param input  An Array of Integers to examine.
     * @param x      The target value to find values closest to.
     * @param k      The number of values to return.
     *
     * @return      An Array of results matching the criteria.
     */
    public static Integer[] findValues(Integer[] input, int x, int k) {
        List<Integer> results = new ArrayList<>();

        //Sorts Original Input N Log N
        List<Integer> orderedInput = Arrays.asList(input);
        orderedInput.sort(Comparator.naturalOrder());

        //Binary Search to find position Log N
        int binarySearchResult = Arrays.binarySearch(input, x);

        List<Integer> precedingInts;
        List<Integer> followingInts;

        if (binarySearchResult > 0) {
            results.add(x);
            precedingInts = orderedInput.subList(0, binarySearchResult);
            followingInts = orderedInput.subList(binarySearchResult + 1, orderedInput.size());
        } else {
            binarySearchResult = Math.abs(binarySearchResult) - 1;
            precedingInts = orderedInput.subList(0, binarySearchResult);
            followingInts = orderedInput.subList(binarySearchResult, orderedInput.size());
        }

        Collections.reverse(precedingInts);

        // Feed results from two sublists. Ignoring guard clauses this would be 3 comparisions for each K
        while (results.size() < k) {
            //region Guard Clause
            if (precedingInts.size() < 1 && followingInts.size() < 1) {
                break;
            }

            if (precedingInts.size() < 1) {
                Integer b = followingInts.get(0);
                results.add(b);
                followingInts = followingInts.subList(1, followingInts.size());
                continue;
            }

            if (followingInts.size() < 1) {
                Integer a = precedingInts.get(0);
                results.add(a);
                precedingInts = precedingInts.subList(1, precedingInts.size());
                continue;
            }
            //endregion


            Integer a = precedingInts.get(0);
            Integer b = followingInts.get(0);

            Integer diffA = Math.abs(x - a);
            Integer diffB = Math.abs(x - b);

            if (diffA.equals(diffB)) {
                results.add(a);
                results.add(b);

                precedingInts = precedingInts.subList(1, precedingInts.size());
                followingInts = followingInts.subList(1, followingInts.size());
            } else if (diffA < diffB) {
                results.add(a);
                precedingInts = precedingInts.subList(1, precedingInts.size());

            } else if (diffB < diffA) {
                results.add(b);
                followingInts = followingInts.subList(1, followingInts.size());
            }
        }

        results.sort(Comparator.naturalOrder());
        System.out.println("results = " + results);
        //Expected speed N Log N + Log N + 3K
        return results.toArray(new Integer[k]);
    }
}
