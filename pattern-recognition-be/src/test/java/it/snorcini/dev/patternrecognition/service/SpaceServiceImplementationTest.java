package it.snorcini.dev.patternrecognition.service;

import it.snorcini.dev.patternrecognition.dto.PointDTO;
import it.snorcini.dev.patternrecognition.dto.PointsListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests regarding the SpaceServiceImplementation class")
class SpaceServiceImplementationTest {

    private SpaceServiceImplementation target;
    private PointDTO pointDTO;

    @BeforeEach
    public void initialize() {
        target = new SpaceServiceImplementation(new ArrayList<>());
        pointDTO = new PointDTO(0.0d, 1.0d);
    }

    @Test
    void testGetPoints01() {
        //PREPARE
        target.savePoint(pointDTO);

        //RUN & VERIFY
        PointsListResponse pointsListResponse = target.getPoints();

        assertEquals(pointsListResponse.getPoints().size(), 1, "These objects should be equal");
    }

    @Test
    void testGetLines01() {
        //PREPARE

        //RUN & VERIFY
    }

    @Test
    void testSavePoint01() {

        //RUN
        target.savePoint(pointDTO);

        //VERIFY
        assertEquals(target.getPoints().getPoints().get(0), pointDTO);
    }
}
