package com.relicum.scb.objects.signs.utils;

/**
 * SuperSkyBros First Created 23/09/13 Simple collection of static methods useful in BaseSign Management
 *
 * @author Relicum
 * @version 0.1
 */
public class directionUtil {

    /**
     * Rounds the signs Yaw to be either North,South,East or West
     *
     * @param yaw Float
     * @return float
     */
    public static float getSimpleDirection(Float yaw) {
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
