package it.snorcini.dev.patternrecognition.service;

import it.snorcini.dev.patternrecognition.dto.PatternRecognitionBaseResponse;
import it.snorcini.dev.patternrecognition.result.PatternRecognitionResults;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Base Plugin implementing utility methods shared by plugins.
 * <p>
 * Contains the configuration for pagination of the list.
 */
@Slf4j
@RequiredArgsConstructor
public abstract class BaseService {

    /**
     * Populates base result info into the passed Response Object.
     *
     * @param baseResponse the response to be populated
     * @param results      the result to be written into the response
     * @param resultInfo   result message list containing dynamic contents
     * @return The populated response
     */
    protected PatternRecognitionBaseResponse setOperationResult(
            @Valid final PatternRecognitionBaseResponse baseResponse,
            final PatternRecognitionResults results,
            final List<String> resultInfo) {
        baseResponse.setResultCode(results.getResultCode());
        baseResponse.setResultMessage(results.getResultMessage());
        baseResponse.setResultInfo(resultInfo);
        return baseResponse;
    }

}
