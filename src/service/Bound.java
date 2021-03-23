package service;

/**
 * Class responsible for storing information of single pair of brackets
 * and corresponding multiplier. does not store any actual chars or strings
 */
public class Bound implements Comparable {
    private int from;
    private int to;
    private int multiplier = 1;

    /**
     * Method called for checking if character under this index belongs to the bound
     * @param index int number of char in string
     * @return boolean
     */
    public boolean owns(int index) {
        if (index > from && index < to) return true;
        return false;
    }

    /**
     * Creates String of all symbols, which belongs to the bound.
     * @param ar array of symbols from main string
     * @return String
     */

    public String stval(char[] ar) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int z = 0; z < multiplier; z++) {
            for (int i = from + 1; i < to; i++) {
                stringBuilder.append(ar[i]);
            }
        }
        return stringBuilder.toString();
    }

    public Bound(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public int compareTo(Object o) {
        if (!o.getClass().equals(Bound.class)) throw new IllegalArgumentException("bad");
        Bound second = (Bound) o;
        if (this.getFrom() > second.getFrom()) return 1;
        if (this.getFrom() < second.getFrom()) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Bound{" +
                "from=" + from +
                ", to=" + to +
                ", multiplier=" + multiplier +
                '}';
    }
}
