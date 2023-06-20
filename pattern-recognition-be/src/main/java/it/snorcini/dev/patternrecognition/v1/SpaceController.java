package it.snorcini.dev.patternrecognition.v1;

import it.snorcini.dev.patternrecognition.dto.PointDTO;
import it.snorcini.dev.patternrecognition.dto.PointsListResponse;
import it.snorcini.dev.patternrecognition.dto.PatternRecognitionBaseResponse;
import it.snorcini.dev.patternrecognition.dto.SegmentsListResponse;
import it.snorcini.dev.patternrecognition.exception.PatternRecognitionServiceException;
import it.snorcini.dev.patternrecognition.service.SpaceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/")
@Slf4j
@AllArgsConstructor
public class SpaceController {

    @NotNull
    private SpaceService spaceService;

    /**
     * Insert a single point.
     *
     * @param pointDTO point coordinates
     * @return operation result message
     */
    @PostMapping(value = "point")
    public ResponseEntity<PatternRecognitionBaseResponse> savePoint(@Valid @RequestBody final PointDTO pointDTO)
            throws PatternRecognitionServiceException {
        log.debug("spaceController.savePoint[point = {}]", pointDTO);
        spaceService.savePoint(pointDTO);
        return new ResponseEntity<>(PatternRecognitionBaseResponse.builder().resultMessage("Point recorded").build(),
                HttpStatus.OK);
    }

    /**
     * Return the available Points.
     *
     * @return a  points list
     */
    @GetMapping(value = "space")
    public ResponseEntity<PointsListResponse> getPoints() throws PatternRecognitionServiceException {
        log.debug("spaceController.getPoints");
        return new ResponseEntity<>(
                spaceService.getPoints(),
                HttpStatus.OK);
    }

    /**
     * It deletes all the points in the space.
     *
     * @return a success result message
     */
    @DeleteMapping(value = "space")
    public ResponseEntity<PatternRecognitionBaseResponse> deleteSpace() throws PatternRecognitionServiceException {
        log.debug("spaceController.deleteSpace");
        return new ResponseEntity<>(
                spaceService.deleteSpace(),
                HttpStatus.OK);
    }

    /**
     * Return the Lines passing through n Points.
     *
     * @return a  points list
     */
    @GetMapping(value = "lines/{n}")
    public ResponseEntity<SegmentsListResponse> getLines(@PathVariable final int n)
            throws PatternRecognitionServiceException {
        log.debug("spaceController.getLines");
        return new ResponseEntity<>(
                spaceService.getLines(n),
                HttpStatus.OK);
    }
}
