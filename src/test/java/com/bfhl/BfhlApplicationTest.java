package com.bfhl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BfhlApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	// Test from Example A
	@Test
	void testMixedInput() throws Exception {
		String body = """
                {
                  "data": ["a", "1", "334", "4", "R", "$"]
                }
                """;

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.odd_numbers[0]").value("1"))
				.andExpect(jsonPath("$.even_numbers[0]").value("334"))
				.andExpect(jsonPath("$.even_numbers[1]").value("4"))
				.andExpect(jsonPath("$.alphabets[0]").value("A"))
				.andExpect(jsonPath("$.alphabets[1]").value("R"))
				.andExpect(jsonPath("$.special_characters[0]").value("$"))
				.andExpect(jsonPath("$.sum").value("339"));
	}

	// Test from Example B
	@Test
	void testMultipleSpecialChars() throws Exception {
		String body = """
                {
                  "data": ["2", "a", "y", "4", "&", "-", "*", "5", "92", "b"]
                }
                """;

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.odd_numbers[0]").value("5"))
				.andExpect(jsonPath("$.sum").value("103"))
				.andExpect(jsonPath("$.alphabets[0]").value("A"))
				.andExpect(jsonPath("$.alphabets[1]").value("Y"))
				.andExpect(jsonPath("$.alphabets[2]").value("B"));
	}

	// Test from Example C
	@Test
	void testAlphaOnlyInput() throws Exception {
		String body = """
                {
                  "data": ["A", "ABCD", "DOE"]
                }
                """;

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.odd_numbers").isEmpty())
				.andExpect(jsonPath("$.even_numbers").isEmpty())
				.andExpect(jsonPath("$.special_characters").isEmpty())
				.andExpect(jsonPath("$.sum").value("0"))
				.andExpect(jsonPath("$.concat_string").value("EoDdCbAa"));
	}

	// Test empty input
	@Test
	void testEmptyData() throws Exception {
		String body = """
                {
                  "data": []
                }
                """;

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.sum").value("0"))
				.andExpect(jsonPath("$.concat_string").value(""));
	}

	// Test numbers only
	@Test
	void testNumbersOnly() throws Exception {
		String body = """
                {
                  "data": ["2", "3", "10"]
                }
                """;

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.even_numbers[0]").value("2"))
				.andExpect(jsonPath("$.even_numbers[1]").value("10"))
				.andExpect(jsonPath("$.odd_numbers[0]").value("3"))
				.andExpect(jsonPath("$.sum").value("15"))
				.andExpect(jsonPath("$.alphabets").isEmpty());
	}
}