package com.relicum.scb.utils;

import com.relicum.scb.types.SkyApi;

import java.text.BreakIterator;
import java.util.Locale;

/**
 * SuperSkyBros First Created 27/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class StringUtils {

    public static void stringSplitter() {
        StringBuilder sb = new StringBuilder();

        Integer c = 0;
        String s2 = "hello how are you going my friend hope you have a goood time today at the zoo The built in Java utilities for splitting strings can have some quirky behaviors. For example, String.split silently discards trailing separators, and StringTokenizer respects exactly five whitespace characters and nothing else";
        String[] sp = s2.split(" ");
        for (String s : sp) {
            if (c + (s.length() + 1) > 60) {
                sb.append("\n");
                c = 0;
            }
            c += (s.length() + 1);
            if (c != 0)
                sb.append(s).append(" ");
        }

        SkyApi.getCMsg().INFO(sb.toString());
    }

    /**
     * Format lines.
     *
     * @param target        the target
     * @param maxLength     the max length
     * @param currentLocale the current locale
     */
    public static void formatLines(String target, int maxLength, Locale currentLocale) {

        BreakIterator boundary = BreakIterator.getLineInstance(currentLocale);
        boundary.setText(target);
        int start = boundary.first();
        int end = boundary.next();
        int lineLength = 0;

        while (end != BreakIterator.DONE) {
            String word = target.substring(start, end);
            lineLength = lineLength + word.length();
            if (lineLength >= maxLength) {
                System.out.println();
                lineLength = word.length();
            }
            System.out.print(word);
            start = end;
            end = boundary.next();
        }
    }

    /**
     * Returns the direction you are looking
     *
     * @param yaw float
     * @return float
     */
    public static float getDirection(Float yaw) {
        yaw = yaw / 90;
        yaw = (float) Math.round(yaw);

        if (yaw == -4 || yaw == 0 || yaw == 4) {
            return (0.00F);
        }
        if (yaw == -1 || yaw == 3) {
            return -90.00F;
        }
        if (yaw == -2 || yaw == 2) {
            return -179.00F;
        }
        if (yaw == -3 || yaw == 1) {
            return 90.00F;
        }
        return 5.00F;
    }
}
