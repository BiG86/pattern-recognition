package it.snorcini.dev.patternrecognition.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for expected operations result.
 */
@Getter
@AllArgsConstructor
public enum PatternRecognitionResults {

    OPERATION_SUCCESS(0, "Operation OK"),
    INVALID_REQUEST(-1, "The request is not valid"),
    GENERIC_ERROR(-9999, "Generic error");

    private int resultCode;
    private String resultMessage;
}
