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
class MovesTest {

    @Autowired
    private MockMvc mockMvc;

    String input = "[{\"x\": 0, \"y\": 0}, {\"x\": 1, \"y\": 0}, {\"x\": 1, \"y\": 3}, {\"x\": 0, \"y\": 3}, {\"x\": 0, \"y\": 0}]";

    String expected = "[{\"direction\":\"EAST\",\"steps\":1},{\"direction\":\"NORTH\",\"steps\":3},{\"direction\":\"WEST\",\"steps\":1},{\"direction\":\"SOUTH\",\"steps\":3}]";

    @Test
    void test() throws Exception {
        this.mockMvc.perform(post("/moves").contentType(MediaType.APPLICATION_JSON).content(input)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(expected)));
    }
}