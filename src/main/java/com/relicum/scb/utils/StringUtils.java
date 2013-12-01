package com.relicum.scb.utils;

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
}
