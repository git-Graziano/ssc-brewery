package guru.sfg.brewery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BreweryControllerIT extends BaseIT {

    @Test
    void listBreweriesCustomerTest() throws Exception {
        mockMvc.perform(get("/brewery/breweries")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void listBreweriesUserTest() throws Exception {
        mockMvc.perform(get("/brewery/breweries")
                .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());

    }

    @Test
    void listBreweriesAnonymousTest() throws Exception {
        mockMvc.perform(get("/brewery/breweries"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void listBreweriesAdminTest() throws Exception {
        mockMvc.perform(get("/brewery/breweries")
                .with(httpBasic("spring", "guru")))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void getBreweriesJsonCustomerTest() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries")
                .with(httpBasic("scott", "tiger")))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void getBreweriesJsonUserTest() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries")
                .with(httpBasic("user", "password")))
                .andExpect(status().isForbidden());

    }

    @Test
    void getBreweriesJsonAnonymousTest() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    void getBreweriesJsonAdminTest() throws Exception {
        mockMvc.perform(get("/brewery/api/v1/breweries")
                .with(httpBasic("spring", "guru")))
                .andExpect(status().is2xxSuccessful());
    }

}