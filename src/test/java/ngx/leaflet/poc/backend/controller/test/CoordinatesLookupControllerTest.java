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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CoordinatesLookupControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(CoordinatesLookupControllerTest.class);

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
    public void coordinatesLookupSuccessfulTest() throws Exception {

        logger.info("Coordinates Lookup Test started");

        mockMvc.perform(get("/coordinates/{node}/{way}/{relation}", "0", "0", "146656"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].lat").exists())
                .andExpect(jsonPath("$[0].lat").value("53.4791301"))
                .andExpect(jsonPath("$[0].lon").exists())
                .andExpect(jsonPath("$[0].lon").value("-2.2441009"));

        logger.info("Coordinates Lookup Test ended");
    }

}
