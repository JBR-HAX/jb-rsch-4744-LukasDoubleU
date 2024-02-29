package org.jetbrains.assignment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LocationsTest {

    @Autowired
    private MockMvc mockMvc;

    String input = """
            [{"direction":"EAST","steps":1},{"direction":"NORTH","steps":3},{"direction":"EAST","steps":3},{"direction":"SOUTH","steps":5},{"direction":"WEST","steps":2}]
            """;

    String expected = "[{\"x\":0,\"y\":0},{\"x\":1,\"y\":0},{\"x\":1,\"y\":3},{\"x\":4,\"y\":3},{\"x\":4,\"y\":-2},{\"x\":2,\"y\":-2}]";

    @Test
    void test() throws Exception {
        this.mockMvc.perform(post("/locations").contentType(MediaType.APPLICATION_JSON).content(input)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }
}