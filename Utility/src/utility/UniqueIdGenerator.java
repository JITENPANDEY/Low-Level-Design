package utility;

import java.util.concurrent.atomic.AtomicInteger;

public class UniqueIdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final int MAX_COUNTER = 999;
    private static final long EPOCH = 1_600_000_000L; // Custom epoch (2020-09-13)

    // Singleton instance
    private static final UniqueIdGenerator INSTANCE = new UniqueIdGenerator();

    private UniqueIdGenerator() {}

    public static UniqueIdGenerator getInstance() {
        return INSTANCE;
    }

    public static synchronized long generateId() {
        long secondsSinceEpoch = (System.currentTimeMillis() / 1000) - EPOCH;

        int count = counter.getAndIncrement();
        if (count > MAX_COUNTER) {
            // Reset counter and wait for next second
            counter.set(0);
            return generateId();
        }

        // Ensure 3 digits of counter
        return Long.parseLong(String.format("%07d%03d", secondsSinceEpoch, count));
    }
}
