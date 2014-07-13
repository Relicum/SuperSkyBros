package com.relicum.scb.utils;

import java.text.BreakIterator;
import java.util.Locale;

public class StringBreak {

    static void extractWords(String target, BreakIterator wordIterator) {

        wordIterator.setText(target);
        int start = wordIterator.first();
        int end = wordIterator.next();

        while (end != BreakIterator.DONE) {
            String word = target.substring(start, end);
            if (Character.isLetterOrDigit(word.charAt(0))) {
                System.out.println(word);
            }
            start = end;
            end = wordIterator.next();
        }
    }

    static void reverseWords(String target, BreakIterator wordIterator) {

        wordIterator.setText(target);
        int end = wordIterator.last();
        int start = wordIterator.previous();

        while (start != BreakIterator.DONE) {
            String word = target.substring(start, end);
            if (Character.isLetterOrDigit(word.charAt(0)))
                System.out.println(word);
            end = start;
            start = wordIterator.previous();
        }
    }

    static void markBoundaries(String target, BreakIterator iterator) {

        StringBuffer markers = new StringBuffer();
        markers.setLength(target.length() + 1);
        for (int k = 0; k < markers.length(); k++) {
            markers.setCharAt(k, ' ');
        }

        iterator.setText(target);
        int boundary = iterator.first();

        while (boundary != BreakIterator.DONE) {
            markers.setCharAt(boundary, '^');
            boundary = iterator.next();
        }

        System.out.println(target);
        System.out.println(markers);
    }

    /**
     * Format lines.
     *
     * @param target        the target
     * @param maxLength     the max length of each line in chars
     * @param currentLocale the current locale
     * @return the formatted
     */
    static String formatLines(String target, int maxLength, Locale currentLocale) {

        StringBuilder sb = new StringBuilder();
        currentLocale = new Locale("en", "US");
        BreakIterator boundary = BreakIterator.getLineInstance(currentLocale);
        boundary.setText(target);
        int start = boundary.first();
        int end = boundary.next();
        int lineLength = 0;

        while (end != BreakIterator.DONE) {
            String word = target.substring(start, end);
            lineLength = lineLength + word.length();
            if (lineLength >= maxLength) {
                sb.append(System.lineSeparator());
                lineLength = word.length();
            }
            sb.append(word + " ");

            start = end;
            end = boundary.next();
        }

        return sb.toString();
    }

    static void listPositions(String target, BreakIterator iterator) {

        iterator.setText(target);
        int boundary = iterator.first();

        while (boundary != BreakIterator.DONE) {
            System.out.println(boundary);
            boundary = iterator.next();
        }
    }

    static void characterExamples() {

        BreakIterator arCharIterator = BreakIterator.getCharacterInstance(new Locale("ar", "SA"));
        // Arabic word for "house"
        String house = "\u0628" + "\u064e" + "\u064a" + "\u0652" + "\u067a" + "\u064f";
        listPositions(house, arCharIterator);
    }

    static void wordExamples() {

        Locale currentLocale = new Locale("en", "US");
        BreakIterator wordIterator = BreakIterator.getWordInstance(currentLocale);
        String someText = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        markBoundaries(someText, wordIterator);
        System.out.println();
        extractWords(someText, wordIterator);
    }

    static void sentenceExamples() {

        Locale currentLocale = new Locale("en", "US");
        BreakIterator sentenceIterator = BreakIterator.getSentenceInstance(currentLocale);
        String someText = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        markBoundaries(someText, sentenceIterator);
        String variousText = "He's vanished!  " + "What will we do?  It's up to us.";
        markBoundaries(variousText, sentenceIterator);
        String decimalText = "Please add 1.5 liters to the tank.";
        markBoundaries(decimalText, sentenceIterator);
        String donneText = "\"No man is an island . . . " + "every man . . . \"";
        markBoundaries(donneText, sentenceIterator);
        String dogText = "My friend, Mr. Jones, has a new dog.  " + "The dog's name is Spot.";
        markBoundaries(dogText, sentenceIterator);
    }

    static void lineExamples() {

        Locale currentLocale = new Locale("en", "US");
        BreakIterator lineIterator = BreakIterator.getLineInstance(currentLocale);
        String someText = "She stopped.  " + "She said, \"Hello there,\" and then went on.";
        markBoundaries(someText, lineIterator);
        String hardHyphen = "There are twenty-four hours in a day.";
        markBoundaries(hardHyphen, lineIterator);
        System.out.println();
        String moreText = "She said, \"Hello there,\" and then " + "went on down the street.  When she stopped " + "to look at the fur coats in a shop window, "
                + "her dog growled.  \"Sorry Jake,\" she said. " + " \"I didn't know you would take it personally.\"";

        System.out.println();
    }

}
