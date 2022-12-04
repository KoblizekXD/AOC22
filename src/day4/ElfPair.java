package day4;

import java.util.Arrays;

public class ElfPair {
    private final int[] fRange;
    private final int[] sRange;

    public ElfPair(int[] fRange, int[] sRange) {
        this.fRange = fRange;
        this.sRange = sRange;
    }
    public int[] getfRange() {
        return fRange;
    }
    public int[] getsRange() {
        return sRange;
    }
    public boolean areOverlappingFully() {
        if (fRange[0] <= sRange[0] && fRange[1] >= sRange[1]) {
            return true;
        } else return sRange[0] <= fRange[0] && sRange[1] >= fRange[1];
    }
    public boolean areOverlapping() {
        if ((sRange[0] >= fRange[0] && sRange[0] <= fRange[1])
                || ((fRange[0] >= sRange[0] && fRange[0] <= sRange[1]))) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + Arrays.toString(fRange) + ", " + Arrays.toString(sRange) + "]";
    }
}
