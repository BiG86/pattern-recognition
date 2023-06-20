package it.snorcini.dev.patternrecognition.v1;

import it.snorcini.dev.patternrecognition.dto.PointDTO;
import it.snorcini.dev.patternrecognition.dto.PointsListResponse;
import it.snorcini.dev.patternrecognition.dto.PatternRecognitionBaseResponse;
import it.snorcini.dev.patternrecognition.service.SpaceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpaceController.class})
@DisplayName("Tests regarding the SpaceController class")
class SpaceControllerTest {

    @Autowired
    private SpaceController target;
    @MockBean
    private SpaceService spaceServiceMock;
    @Mock
    private PointDTO pointDTOMock;
    @Mock
    private PatternRecognitionBaseResponse responseMock;
    @Mock
    private PointsListResponse responseListMock;


    @Test
    @DisplayName("Should call getPoints and return OK")
    void testGetPoints01() {
        // given
        doReturn(responseListMock).when(spaceServiceMock).getPoints();

        // when
        ResponseEntity<PointsListResponse> response = target
                .getPoints();

        // then
        verify(spaceServiceMock, times(1)).getPoints();
        assertNotNull(response.getBody(), "These objects should be not null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "These objects should be equal");
    }

    @Test
    @DisplayName("Should call savePoint and return OK")
    void testSavePoint01() {
        // given
        doReturn(responseMock).when(spaceServiceMock).savePoint(pointDTOMock);

        // when
        ResponseEntity<PatternRecognitionBaseResponse> response = target
                .savePoint(pointDTOMock);

        // then
        verify(spaceServiceMock, times(1)).savePoint(pointDTOMock);
        assertEquals(HttpStatus.OK, response.getStatusCode(), "These objects should be equal");
    }
}
