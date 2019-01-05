package ngx.leaflet.poc.backend.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import ngx.leaflet.poc.backend.model.CoordinatesLookupResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(LocationControllerTest.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<CoordinatesLookupResult> jsonTester;

    @Before
    public void setup() {
        jsonTester.initFields(this, objectMapper);
    }

    @Test
    public void predefinedLocationsRetrievalSuccessfulTest() throws Exception {

        logger.info("Predefined locations retrieval Test started");

        mockMvc.perform(get("/location/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(20)));

        logger.info("Predefined locations retrieval Test ended");
    }

}
