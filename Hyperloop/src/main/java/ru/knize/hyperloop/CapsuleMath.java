package ru.knize.hyperloop;


import java.time.Duration;

/**
 * Created by knize on 11.09.16.
 */
public class CapsuleMath {
    private final static long
            accelerationTimeSec = 280,
            decelerationTimeSec = 185,
            totalOverloadTimeSec = accelerationTimeSec + decelerationTimeSec;
    private final static float
            accelerationRangeKm = (accelerationTimeSec / 2) * 0.3333333f,
            decelerationRangeKm = (decelerationTimeSec / 2) * 0.3333333f,
            totalOverloadRangeKm = accelerationRangeKm + decelerationRangeKm;

    static double computePrice(int range) {
        double coefficient = 1;
        if (range < 500) {
            coefficient = 10.0 / 100; // $10 per 100 km
        } else if (range >= 500 && range < 1500) {
            coefficient = 8.0 / 100;
        } else if (range >= 1500 && range < 5000) {
            coefficient = 7.0 / 100;
        } else if (range >= 5000) {
            coefficient = 6.0 / 100;
        }
        return (double) range * coefficient;
    }

    static Duration computeTime(int range) {
        float maxSpeedRange = range - totalOverloadRangeKm;
        long maxSpeedTimeSec = (long) (maxSpeedRange / 0.3333333f);
        return Duration.ofSeconds(maxSpeedTimeSec + totalOverloadTimeSec);
    }
}
