package gsc.projects.registermcs.service;

import gsc.projects.registermcs.converter.RegisterConverter;
import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
import gsc.projects.registermcs.model.Register;
import gsc.projects.registermcs.repository.RegisterRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class RegisterServiceImpTest {

    @Mock
    RegisterRepository registerRepository;

    @Mock
    RegisterConverter registerConverter;

    @InjectMocks
    RegisterServiceImp registerServiceImp;

    RegisterCreateDto registerCreateDto;

    Register register;
    RegisterDto registerDto;
    CacheRegisterDto cacheRegisterDto;
    @BeforeEach
    void setup(){
        registerRepository.deleteAll();


        registerCreateDto = new RegisterCreateDto();
        registerCreateDto.setCacheCode("firstcacheapp");
        registerCreateDto.setUserEmail("newEmail@gmail.com");

        register = new Register();
        register.setCacheCode(registerCreateDto.getCacheCode());
        register.setUserEmail(registerCreateDto.getUserEmail());

        cacheRegisterDto = new CacheRegisterDto();
        cacheRegisterDto.setCacheCode(registerCreateDto.getCacheCode());

        registerDto = new RegisterDto();
        registerDto.setCacheRegisterDto(cacheRegisterDto);
    }

    @Nested
    @Tag("Unit tests")
    class RegisterUnitTests{


        @Test
        @DisplayName("Create a register for a exists cache with a user email")
        public void createARegister() {

            given(registerRepository.save(register)).willReturn(register);

            when(registerRepository.findById(register.getId())).thenReturn(Optional.ofNullable(register));

            assertEquals(registerDto.getCacheRegisterDto().getCacheCode(), register.getCacheCode());
        }


        @Test
        @DisplayName("Try to create a register that already exists")
        public void tryToCreateExistsRegister() {

            Register register1 = new Register();
            register1.setUserEmail(register.getUserEmail());
            register1.setCacheCode(register.getCacheCode());


            given(registerRepository.save(register)).willReturn(register);
            given(registerRepository.findById(register.getId())).willReturn(Optional.ofNullable(register));

            when(registerRepository.findRegisterByUserEmailAndCacheCode(register1.getUserEmail(), register1.getCacheCode())).thenThrow(ResponseStatusException.class);

            assertThrows(ResponseStatusException.class, () -> {
                registerServiceImp.createRegister(registerCreateDto);
            });
        }
    }

}