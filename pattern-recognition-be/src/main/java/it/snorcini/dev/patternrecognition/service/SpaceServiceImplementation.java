package it.snorcini.dev.patternrecognition.service;

import it.snorcini.dev.patternrecognition.dto.LineDTO;
import it.snorcini.dev.patternrecognition.dto.PatternRecognitionBaseResponse;
import it.snorcini.dev.patternrecognition.dto.PointDTO;
import it.snorcini.dev.patternrecognition.dto.PointsListResponse;
import it.snorcini.dev.patternrecognition.dto.SegmentsListResponse;
import it.snorcini.dev.patternrecognition.exception.PatternRecognitionServiceException;
import it.snorcini.dev.patternrecognition.result.PatternRecognitionResults;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service that computes data and
 * provides the logic for space, lines and points.
 */
@Service
@Validated
@Slf4j
@AllArgsConstructor
@Scope("application")
public class SpaceServiceImplementation extends BaseService implements SpaceService {

    private List<PointDTO> space;

    /**
     * Save a new Point.
     *
     * @param insertPointDTO The new point to be saved
     * @return The operation result
     */
    @Override
    public PatternRecognitionBaseResponse savePoint(@Valid final PointDTO insertPointDTO)
            throws PatternRecognitionServiceException {
        log.debug("spaceServiceImplementation.savePoint[point = {}]", insertPointDTO);
        space.add(insertPointDTO);
        return this.setOperationResult(new PatternRecognitionBaseResponse(),
                PatternRecognitionResults.OPERATION_SUCCESS, null);

    }

    /**
     * Retrieve Orders with optional filter.
     *
     * @return The response containing the points list
     */
    @Override
    public PointsListResponse getPoints() throws PatternRecognitionServiceException {
        log.debug("spaceServiceImplementation.getPoints");

        return (PointsListResponse) this.setOperationResult(
                PointsListResponse.builder().points(space).build(),
                PatternRecognitionResults.OPERATION_SUCCESS,
                null
        );
    }

    /**
     * Delete the space.
     *
     * @return The operation result
     */
    @Override
    public PatternRecognitionBaseResponse deleteSpace() throws PatternRecognitionServiceException {
        log.debug("spaceServiceImplementation.deleteSpace");
        space.clear();
        return this.setOperationResult(new PatternRecognitionBaseResponse(),
                PatternRecognitionResults.OPERATION_SUCCESS, null);
    }

    public SegmentsListResponse getLines(final int n) throws PatternRecognitionServiceException {
        log.debug("spaceServiceImplementation.getLines[n = {}]", n);
        //Validate path variable
        //If n < 2 we have infinite results
        if (n < 2) {
            List<String> resultInfo = new ArrayList<>();
            resultInfo.add("n = " + n + " is less then 2");
            throw new PatternRecognitionServiceException(PatternRecognitionResults.INVALID_REQUEST,
                    resultInfo,
                    PatternRecognitionBaseResponse.class,
                    new Exception("Invalid path param"));
        }
        List<LineDTO> listOfLines = new ArrayList<>();
        for (int a = 0; a < space.size(); a++) {
            for (int b = a + 1; b < space.size(); b++) {
                var lineAB = new LineDTO(space.get(a), space.get(b));
                listOfLines.add(lineAB);
                for (int c = b + 1; c < space.size(); c++) {
                    if (containsPoint(lineAB.getPoints(), space.get(c))) {
                        lineAB.getPoints().add(space.get(c));
                    }
                }
            }
        }
        return SegmentsListResponse.builder().segments(listOfLines).build();
    }

    public boolean containsPoint(final List<PointDTO> points, final PointDTO c) {
        var a = points.get(0);
        var b = points.get(1);

        //If all X values are equal, they are on the same line:
        if (Objects.equals(a.getX(), b.getY()) && Objects.equals(a.getX(), c.getX())) {
            return true;
        }
        //If all Y values are equal, they are on the same line:
        if (Objects.equals(a.getY(), b.getY()) && Objects.equals(a.getY(), c.getY())) {
            return true;
        }

        //If AB and AC have the same slope, they are on the same line:
        return calculateSlope(a, b) == calculateSlope(a, c);
    }

    private double calculateSlope(final PointDTO p1, final PointDTO p2) {
        double xDiff = p2.getX() - p1.getX();
        double yDiff = p2.getY() - p1.getY();

        return yDiff / xDiff;
    }
}
