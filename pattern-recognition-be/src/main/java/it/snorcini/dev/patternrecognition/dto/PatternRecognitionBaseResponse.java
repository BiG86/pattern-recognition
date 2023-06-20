package it.snorcini.dev.patternrecognition.dto;

import it.snorcini.dev.patternrecognition.result.PatternRecognitionResults;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Generic response object returned by controller.
 * Methods:
 * - constructor
 * - getter and setter
 * - equals
 * are auto-generated by Lombok.
 * <p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PatternRecognitionBaseResponse {

    /**
     * Operation result code.
     *
     * @see PatternRecognitionResults
     */
    @NotNull
    protected Integer resultCode;
    /**
     * Description of result
     */
    protected String resultMessage;
    /**
     * Other information like validation error
     */
    protected List<String> resultInfo;
}
