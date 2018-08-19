package ee.bilal.dev.dataprocessor.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bilal90 on 8/19/2018.
 */
public class IdFactory {
    private static final AtomicLong sequence = new AtomicLong(System.currentTimeMillis());

    private IdFactory() {
        throw new AssertionError();
    }

    /**
     * Get UUID
     * @return uuid as string
     */
    public static String uuidID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get unique sequence
     * @return unique sequence as string
     */
    public static String uniqueSequence() {
        return String.valueOf(sequence.incrementAndGet());
    }
}
