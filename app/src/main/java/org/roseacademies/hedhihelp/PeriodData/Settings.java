package org.roseacademies.hedhihelp.PeriodData;

public class Settings {
    private int length;
    private int gap;
    public Settings(int length, int gap) {
        this.length = length;
        this.gap = gap;
    }

    public int getLength() {
        return length;
    }

    public int getGap() {
        return gap;
    }
}
