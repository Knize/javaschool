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

    public static Duration countTime(int range) {
        float maxSpeedRange = range - totalOverloadRangeKm;
        long maxSpeedTimeSec = (long) (maxSpeedRange / 0.3333333f);
        return Duration.ofSeconds(maxSpeedTimeSec + totalOverloadTimeSec);
    }
}
