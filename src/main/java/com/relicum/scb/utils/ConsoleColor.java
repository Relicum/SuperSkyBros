package com.relicum.scb.utils;

/**
 * Name: ConsoleColor.java Created: 14 July 2014
 *
 * @author Relicum
 * @version 0.0.1
 */
public enum ConsoleColor {

    /**
     * Reset color back to normal
     */
    RESET("\033[0m"),
    BLACK("\033[30m"),      /* Black */
    RED("\033[31m"),      /* Red */
    GREEN("\033[32m"),      /* Green */
    YELLOW("\033[33m"),      /* Yellow */
    BLUE("\033[34m"),      /* Blue */
    MAGENTA("\033[35m"),      /* Magenta */
    CYAN("\033[36m"),      /* Cyan */
    WHITE("\033[37m"),      /* White */
    BOLDBLACK("\033[1m\033[30m"),      /* Bold Black */
    BOLDRED("\033[1m\033[31m"),      /* Bold Red */
    BOLDGREEN("\033[1m\033[32m"),     /* Bold Green */
    BOLDYELLOW("\033[1m\033[33m"),      /* Bold Yellow */
    BOLDBLUE("\033[1m\033[34m"),      /* Bold Blue */
    BOLDMAGENTA("\033[1m\033[35m"),      /* Bold Magenta */
    BOLDCYAN("\033[1m\033[36m"),      /* Bold Cyan */
    BOLDWHITE("\033[1m\033[37m");

    private final String color;

    private ConsoleColor(String c) {
        this.color = c;
    }

    /**
     * Get color in an ascaped string format
     *
     * @return the string
     */
    public String getColor() {
        return color;
    }
}
