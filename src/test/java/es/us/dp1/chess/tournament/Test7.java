package es.us.dp1.chess.tournament;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Test7 {
    @Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;

    private String url = "/api/v1/matches";
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
		.webAppContextSetup(context)
		.apply(SecurityMockMvcConfigurers.springSecurity())
		.build();
	}    	

	@Test
    @Transactional
    @WithMockUser(username = "player", authorities = {"PLAYER"})
    public void test7CanGetMatches() throws JsonProcessingException, Exception{
        mockMvc.perform(get(url))
			.andExpect(status().isOk());			
    }

    

    @Test
    @Transactional
    @WithMockUser(username = "player", authorities = {"PLAYER"})
    public void test7CanGetMathById() throws JsonProcessingException, Exception{
        mockMvc.perform(get(url+"/1"))
			.andExpect(status().isOk())			
            .andExpect(jsonPath("$.name", is("The Immortal Match, Anderssen vs Kieseritzky 1851")));        
    }


    @Test
    @Transactional
    @WithMockUser(username = "player", authorities = {"PLAYER"})
    public void test3aCannotGetHistoryOfNonExistentPet() throws JsonProcessingException, Exception{        
       mockMvc.perform(get(url+"/474629"))
			.andExpect(status().isNotFound());
    }
}
