package gsc.projects.cachesmcs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.cachesmcs.dto.CacheCreateDto;
import gsc.projects.cachesmcs.dto.CacheDto;
import gsc.projects.cachesmcs.dto.CacheUpdateDto;
import gsc.projects.cachesmcs.model.Cache;
import gsc.projects.cachesmcs.repository.CacheRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class CacheControllerTest {

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    CacheCreateDto cacheCreateDto;
    Cache cache;
    CacheDto cacheDto;
    CacheUpdateDto cacheUpdateDto;
    @BeforeEach
    void setup() {
        cacheRepository.deleteAll();

        cacheCreateDto = new CacheCreateDto();
        cacheCreateDto.setAddress("test address");
        cacheCreateDto.setCacheCode("NewCode");
        cacheCreateDto.setTip("Tip test");

        cache = new Cache();
        cache.setAddress("test address");
        cache.setCacheCode("NewCode");
        cache.setTip("Tip test");

        cacheDto = new CacheDto();
        cacheDto.setAddress("test address");
        cacheDto.setCacheCode("NewCode");
        cacheDto.setTip("Tip test");

        cacheUpdateDto = new CacheUpdateDto();
        cacheUpdateDto.setTip("new Tip");
    }

    void persistCache(){
        cacheRepository.save(cache);
    }

    @Nested
    @Tag("Integration tests")
    class CacheIntegrationTests {

        @Test
        @DisplayName("Create a cache that doesnt exists and return 200")
        public void createACacheThatDoesntExist200() throws Exception{

            ResultActions response = mockMvc.perform(post("/cache")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(cacheCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.tip", is(cacheCreateDto.getTip())));
        }

        @Test
        @DisplayName("Try to create a cache with a cache code that already exists and return 400")
        public void TryToCreateACacheThatAlreadyExist400() throws Exception{

            CacheCreateDto newCacheCreateDto = new CacheCreateDto();
            newCacheCreateDto.setCacheCode(cacheCreateDto.getCacheCode());

            ResultActions response = mockMvc.perform(post("/cache")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(cacheCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated());

            ResultActions response2 = mockMvc.perform(post("/cache")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(newCacheCreateDto)));

            response2.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());

        }

        @Test
        @DisplayName("Get a cache for cache code and return 200")
        public void getACacheByCacheCode200() throws Exception{

            persistCache();

            ResultActions response = mockMvc.perform(get("/cache/{cacheCode}", cacheCreateDto.getCacheCode()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cacheCode", is(cacheCreateDto.getCacheCode())));
        }

        @Test
        @DisplayName("Try to get a cache with a non exists cache code return 404")
        public void tryToGetCacheWithInvalidCacheCode404() throws Exception{

            ResultActions response = mockMvc.perform(get("/cache/{cacheCode}", "Hello"));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Delete a cache by id and return 200")
        public void deleteACacheById200() throws Exception{

            persistCache();


            ResultActions response = mockMvc.perform(delete("/cache/{cacheId}", cache.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to delete a cache with invalid id return 400")
        public void tryDeleteCacheWithInvalidId400() throws Exception {

            ResultActions response = mockMvc.perform(delete("/cache/{cacheId}", 1L));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Update an exists cache and return 200")
        public void updateAnExistsCache200() throws Exception{
            persistCache();

            ResultActions response = mockMvc.perform(put("/cache/{cacheCode}", cache.getCacheCode())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(cacheUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.tip", is(cacheUpdateDto.getTip())));
        }

        @Test
        @DisplayName("Try to update a non exists cache and return 404")
        public void tryToUpdateANonExistsCache404() throws Exception{

            ResultActions response = mockMvc.perform(put("/cache/{cacheCode}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(cacheUpdateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

    }
}