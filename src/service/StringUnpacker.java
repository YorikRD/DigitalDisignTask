package service;

import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Main class responsible for unpacking strings, uses all other as auxiliary;
 */
public class StringUnpacker {
    /**
     * Method responsible for returning unpacked string
     * @param packed String presumed to be packed one
     * @return result string
     */
    public static String unpack(String packed) {
        if (!StringValidator.validate(packed))
            throw new IllegalArgumentException("This string is not valid for unpacking");
        char[] arr = packed.toCharArray();
        LinkedList<Bound> pairs = createPairs(arr);
        StringBuilder sb = new StringBuilder();
        if (pairs.isEmpty()) return packed;
        for (Bound pair : pairs) {
            pair.setMultiplier(countBoundM(pair, arr));
        }
        for (int i = 0; i < arr.length; i++) {
            boolean owned = false;
            for (Bound bound : pairs) {
                if (bound.owns(i)) {
                    owned = true;
                    sb.append(unpack(bound.stval(arr)));
                    i = bound.getTo();
                    pairs.remove(bound);
                }
            }
            if (Character.isLetter(arr[i]) && !owned) sb.append(arr[i]);
        }
        return sb.toString();

    }

    /**
     * Method responsible for finding and creating list of bounds
     *
     * @param arr char arr value of main string
     * @return list of bounds in corresponding string;
     */
    private static LinkedList<Bound> createPairs(char[] arr) {
        LinkedList<Integer> openings = new LinkedList<>();
        LinkedList<Integer> closingOnes = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            if (StringValidator.cIsOpening(arr[i])) openings.add(i);
            if (StringValidator.cIsClosing(arr[i])) closingOnes.add(i);
        }
        LinkedList<Bound> intervals = new LinkedList<>();
        while (!openings.isEmpty()) {
            Integer a = openings.pollLast();
            Predicate<Integer> isnextAfterA = integer -> integer.compareTo(a) > 0;
            Integer b = closingOnes.stream().filter(isnextAfterA).findFirst().get();
            closingOnes.remove(b);
            Bound bound = new Bound(a, b);
            intervals.add(bound);
        }
        return intervals;
    }

    /**
     * Method responsible for providing any Bound, a corresponding multiplier is moved hear from Bound class
     * for easy access to the main string data.
     *
     * @param bound bound for finding multiplier
     * @param main  char arr value of main string
     * @return int value (does not assign it to the bound)
     */
    private static int countBoundM(Bound bound, char[] main) {
        int razN = 0;
        int charInd = (bound.getFrom() - 1);
        while (charInd >= 0 && Character.isDigit(main[charInd])) {
            razN++;
            charInd--;
        }
        int multiplier = 1;
        if (razN > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            while (razN > 0) {
                stringBuilder.append(main[bound.getFrom() - razN]);
                razN--;
            }
            multiplier = Integer.parseInt(stringBuilder.toString());
        }
        return multiplier;
    }
}
