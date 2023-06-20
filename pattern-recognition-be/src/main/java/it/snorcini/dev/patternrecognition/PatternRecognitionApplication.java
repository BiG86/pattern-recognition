package it.snorcini.dev.patternrecognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main executable class.
 */
@SpringBootApplication
public class PatternRecognitionApplication {

    /**
     * Application starter.
     *
     * @param args application arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(PatternRecognitionApplication.class, args);
    }
}
