package gsc.projects.cachesmcs.service;

import gsc.projects.cachesmcs.converter.CacheConverter;
import gsc.projects.cachesmcs.dto.CacheCreateDto;
import gsc.projects.cachesmcs.dto.CacheDto;
import gsc.projects.cachesmcs.dto.CacheUpdateDto;
import gsc.projects.cachesmcs.model.Cache;
import gsc.projects.cachesmcs.repository.CacheRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class CacheServiceImpTest {

        @Mock
        CacheRepository cacheRepository;
        @Mock
        CacheConverter cacheConverter;
        @InjectMocks
        CacheServiceImp cacheServiceImp;
        Cache cache;
        CacheCreateDto cacheCreateDto;
        CacheDto cacheDto;
        CacheUpdateDto cacheUpdateDto;

        @BeforeEach
        void setup(){
            cacheRepository.deleteAll();

            cacheCreateDto = new CacheCreateDto();
            cacheCreateDto.setAddress("Test Address");
            cacheCreateDto.setTip("Test Tip");
            cacheCreateDto.setCacheCode("Cache Code");

            cache = new Cache();
            cache.setAddress("Test Address");
            cache.setTip("Test Tip");
            cache.setCacheCode("Cache Code");

            cacheUpdateDto = new CacheUpdateDto();
            cacheUpdateDto.setTip("New Tip");

            cacheDto = new CacheDto();
            cacheDto.setAddress("Test Address");
            cacheDto.setTip(cacheUpdateDto.getTip());
            cacheDto.setCacheCode("Cache Code");

        }


        @Nested
        @Tag("Unit tests")
        class CacheUnitTests {


            @Test
            @DisplayName("Create a cache that doesnt exists")
            public void createACacheThatDoesntExists() {

                given(cacheRepository.save(cache)).willReturn(cache);

                when(cacheRepository.findByCacheCode(cache.getCacheCode())).thenReturn(cache);

                assertEquals(cacheDto.getCacheCode(), cache.getCacheCode());
            }

            @Test
            @DisplayName("Try to create a cache with a cache code that already exists")
            public void tryToCreateCacheAlreadyExists() {

                given(cacheRepository.save(cache)).willReturn(cache);

                when(cacheRepository.findByCacheCode(cache.getCacheCode())).thenThrow(ResponseStatusException.class);

                assertThrows(ResponseStatusException.class, () -> {
                   cacheServiceImp.createCache(cacheCreateDto);
                });
            }

            @Test
            @DisplayName("Delete an exists cache")
            public void deleteAnExistsCache() {

                given(cacheRepository.save(cache)).willReturn(cache);

                when(cacheRepository.findByCacheCode(cache.getCacheCode())).thenReturn(cache);

                cacheRepository.delete(cache);

                when(cacheRepository.findByCacheCode(cache.getCacheCode())).thenReturn(null);

            }

            @Test
            @DisplayName("Try to delete a cache that doesnt exists")
            public void tryToDeleteAnCacheThatDoesntExists(){

                when(cacheRepository.findByCacheCode(cache.getCacheCode())).thenThrow(ResponseStatusException.class);

                assertThrows(ResponseStatusException.class, () -> {
                    cacheServiceImp.deleteById(1L);
                });
            }

            @Test
            @DisplayName("Update an exists cache")
            public void updateAnExistsCache(){

                given(cacheRepository.save(cache)).willReturn(cache);

                when(cacheRepository.findByCacheCode(cache.getCacheCode())).thenReturn(cache);

                when(cacheServiceImp.updateCache(cache.getCacheCode(), cacheUpdateDto)).thenReturn(cacheDto);

                assertEquals(cacheDto.getTip(), cacheUpdateDto.getTip());
            }
        }


}