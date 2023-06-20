package it.snorcini.dev.patternrecognition.exception;

import it.snorcini.dev.patternrecognition.result.PatternRecognitionResults;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.List;

/**
 * This exception is throwable by any pattern recognition service.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatternRecognitionServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private PatternRecognitionResults patternRecognitionResults;
    private List<String> resultInfo;
    private Class responseClass;


    public PatternRecognitionServiceException(final PatternRecognitionResults patternRecognitionResults,
                                              final List<String> resultInfo,
                                              final Class responseClass,
                                              final Exception e) {
        super(e);
        this.patternRecognitionResults = patternRecognitionResults;
        this.resultInfo = resultInfo;
        this.responseClass = responseClass;

    }


}
