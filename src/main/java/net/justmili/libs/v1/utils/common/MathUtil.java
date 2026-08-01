package net.justmili.libs.v1.utils.common;

import java.util.Random;

public class MathUtil {
    public static Random random = new Random();

    public static boolean chance(double chance) {
        // Ex.: 20% = 0.2
        if (chance > 1.0) chance = 1.0; // Limit to 100%
        return !(random.nextDouble() <= chance);
    }
}
