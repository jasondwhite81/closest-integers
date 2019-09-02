import java.util.*;
import java.util.stream.Collectors;

public class ClosestIntegersUtil {

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
        Map<Integer, List<Integer>> deltaBucket = new HashMap<>();

        // Compute distance from x for each n inside of input. This is done in Liner time 1N
        int diff = 0;
        for (Integer currInteger : input) {
            diff = Math.abs(x - currInteger);
            if (deltaBucket.containsKey(diff)) {
                deltaBucket.get(diff).add(currInteger);
            } else {
                List<Integer> values = new LinkedList<>();
                values.add(currInteger);
                deltaBucket.put(diff, values);
            }
        }
        // System.out.println("//deltaBucket = " + deltaBucket);


        // Pull all of the found distances from the labels of each bucket then put those buckets in order.
        // Worst Case scenario each bucket contains only one value, built in sort uses built in mergesort n Log n
        List<Integer> keys = deltaBucket.keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        //System.out.println("//keys = " + keys);

        //Go through the ordered buckets until we have counted atleast K number of entries
        for (int i = 0; i < keys.size() || (results.size() < k); i++) {
            results.addAll(deltaBucket.get(keys.get(i)));
        }

        results = results.subList(0, k);
        //Sort subset k log k
        results.sort(Comparator.naturalOrder());

        //Estimated total time
        // 1N filing integers into bucket
        // NLogN ordering list of Distances
        // Extract K entries
        // Sort K log K entries

        // 2NLogN + 2KLogK
        return results.toArray(new Integer[k]);
    }
}
