package brickhack.songbird.util;

import java.lang.Thread;
import java.util.Random;

public class Notes {

    // Stores 10 octaves worth of frequencies (10 * 12)
    static double[] freqs = new double[120];
    static String[] notes = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#",
            "A", "A#", "B" };

    /**
     * Determine if the given frequency is close enough to the target to pass
     * @param currentFreq
     * @param targetFreq
     * @param slack
     * @return TRUE if frequency is in slack region.
     */
    public static boolean freqMatches(double currentFreq, double targetFreq, double slack) {
        while (getOctaveOfFreq(currentFreq) >= 0
                && getOctaveOfFreq(currentFreq) < getOctaveOfFreq(targetFreq)) {
            currentFreq *= 2;
        }
        if (currentFreq <= targetFreq + slack / 100 * targetFreq
                && currentFreq >= targetFreq - slack / 100 * targetFreq) {
            return true;
        }
        return false;
    }

    /**
     * @param freq
     * @return octave that the given frequency is in.
     */
    public static int getOctaveOfFreq(double freq) {
        if (freq < freqs[0]) {
            return -1;
        }
        int octave = 0;
        while (freq > freqs[11]) {
            freq /= 2;
            octave++;
        }
        return octave;
    }

    /**
     * @param freq
     * @return nearest frequency of a perfect note.
     */
    public static double autoTune(double freq) {
        int note = 0;
        double low = freqs[note];
        double up = freqs[note + 1];
        while (freq < low || freq > up) {
            note++;
            low = freqs[note];
            up = freqs[note + 1];
        }
        if (freq - low < up - freq) {
            return low;
        } else {
            return up;
        }
    }

    /**
     * Translates player's frequency into a y-altitude on the phone
     * @param freq
     * @return a number from 0 to 100 representing player's altitude
     */
    public static double freqToAltitude(double freq){
        double max = freqs[12*(getOctaveOfFreq(freq)+1)-1] - freqs[12*getOctaveOfFreq(freq)];
        freq -= freqs[12*getOctaveOfFreq(freq)];
        return freq / max;
    }

    /**
     * @return a random frequency from the 0th octave.
     */
    public static double getRandomFreq() {
        Random random = new Random();
        return autoTune(random.nextInt((int) (freqs[11] - freqs[0] + 1))
                + freqs[0]);
    }

    /**
     * Returns nearest note matching given frequency
     * @param freq
     * @return string representation of given frequency
     */
    public static String toString(double freq){
        freq = autoTune(freq);
        for(int note=0; note < freqs.length; note++){
            if(freqs[note] == freq){
                return notes[note%12];
            }
        }
        return null;
    }

    /**
     * Fills an array with all note frequency for the 0th octave.
     */
    public static void initialize() {
        for (int note = 0; note < freqs.length; note++) {
            freqs[note] = 440 * Math.pow(Math.pow(2, (double) 1 / 12),
                    note - 57);
        }
    }

    /**
     * Strictly used to for testing these methods
     * REMOVE
     * @param args
     */
    public static void main(String[] args) {

        initialize();

        System.out.println(getOctaveOfFreq(2050.00));
        System.out.println(autoTune(16.5));
        System.out.println(toString(17));
        System.out.println(toString(21));
        System.out.println(freqToAltitude(262));

        for (int i = 0; i <= 1975.53; i++) {
            if (freqMatches(i, 1975.53, 1)) {
                System.out.println("Accurate Frequency: " + i + " Hz");
            }
        }

        for (;;) {
            double newNote = autoTune(getRandomFreq());
            System.out.println(toString(newNote) + "\t@ " + newNote);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
