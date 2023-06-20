package it.snorcini.dev.patternrecognition.service;

import it.snorcini.dev.patternrecognition.dto.PointDTO;
import it.snorcini.dev.patternrecognition.dto.PointsListResponse;
import it.snorcini.dev.patternrecognition.dto.PatternRecognitionBaseResponse;
import it.snorcini.dev.patternrecognition.dto.SegmentsListResponse;
import it.snorcini.dev.patternrecognition.exception.PatternRecognitionServiceException;
import jakarta.validation.Valid;

/**
 * Service interface to manage Space data.
 */
public interface SpaceService {


    /**
     * Save a new Point.
     *
     * @param pointDTO The new point to be saved
     * @return Generic response patternRecognitionBaseResponse
     */
    PatternRecognitionBaseResponse savePoint(@Valid PointDTO pointDTO)
            throws PatternRecognitionServiceException;


    /**
     * Retrieve Orders with optional filter.
     *
     * @return The response containing the points list
     */
    PointsListResponse getPoints() throws PatternRecognitionServiceException;

    /**
     * Remove all points from the space.
     *
     * @throws PatternRecognitionServiceException standard service exception
     */
    PatternRecognitionBaseResponse deleteSpace() throws PatternRecognitionServiceException;

    /**
     * Get all line segments passing through at least N points.
     *
     * @param n the number of points required
     * @return The response containing the segments
     */
    SegmentsListResponse getLines(int n) throws PatternRecognitionServiceException;

}
