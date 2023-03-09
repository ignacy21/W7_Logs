package code.loggerloop;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

@Slf4j
public class LoggerLoop {

    private static final Map<Integer, Consumer<Integer>> ACTIONS = Map.of(
            0, key -> log.debug("some debug message, key: {}", key),
            1, key -> log.info("some debug message, key: {}", key),
            2, key -> log.warn("some debug message, key: {}", key),
            3, key -> log.error("some debug message, key: {}", key)
    );

    public static void log() {
        IntStream.rangeClosed(0, 1_000_000)
                .map(i -> i % 4)
                .forEach(key -> Optional.ofNullable(ACTIONS.get(key))
                        .orElseThrow(() -> new RuntimeException("Cause not hendled"))
                        .accept(key));
    }

}
