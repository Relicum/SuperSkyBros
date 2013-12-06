package com.relicum.scb.utils;

import com.relicum.scb.types.SkyApi;

import java.text.BreakIterator;
import java.util.Locale;

/**
 * SuperSkyBros
 * First Created 27/11/13
 *
 * @author Relicum
 * @version 0.1
 */
public class StringUtils {

    public static String replaceUtf8Characters(String input) {
        final String[][] utf8 = new String[][]{
                {"[<3]", "❤"},
                {"[check]", "✔"},

                {"[<]", "◄"},
                {"[>]", "►"},

                {"[star]", "★"},
                {"[round_star]", "✪"},
                {"[stars]", "⁂"},
                {"[T_STAR]", "✰"},

                {"[crown]", "♛"},
                {"[chess]", "♜"},

                {"[top]", "▀"},
                {"[button]", "▄"},
                {"[side]", "▌"},
                {"[mid]", "▬"},

                {"[1]", "▂"},
                {"[2]", "▃"},
                {"[3]", "▄"},
                {"[4]", "▅"},
                {"[5]", "▆"},
                {"[6]", "▇"},
                {"[7]", "█"},
                {"[8]", "▓"},
                {"[9]", "▒"},
                {"[10]", "░"},

                {"[right_up]", "⋰"},
                {"[left_up]", "⋱"},

                {"[PHONE]", "✆"},
                {"[MAIL]", "✉"},
                {"[PLANE]", "✈"},
                {"[PENCIL]", "✎"},
                {"[X]", "✖"},
                {"[FLOWER]", "✿"},

                {"[ARROW]", "➽"},
                {"[ARROW1]", "➨"},
                {"[ARROW2]", "➤"},
                {"[ARROW3]", "➜"},
                {"[ARROW4]", "➨"},

                {"[ONE]", "❶"},
                {"[TWO]", "❷"},
                {"[THREE]", "❸"},
                {"[FOUR]", "❹"},
                {"[FIVE]", "❺"},
                {"[SIX]", "❻"},
                {"[SEVEN]", "❼"},
                {"[EIGHT]", "❽"},
                {"[NINE]", "❾"},
                {"[TEN]", "❿"},
        };

        for (String[] temp : utf8) {
            input = input.replace(temp[0], temp[1]);
        }

        return input;
    }

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

        BreakIterator boundary = BreakIterator.
                getLineInstance(currentLocale);
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
