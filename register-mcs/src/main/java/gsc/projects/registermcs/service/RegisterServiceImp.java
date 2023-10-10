package gsc.projects.registermcs.service;


import gsc.projects.registermcs.converter.RegisterConverter;
import gsc.projects.registermcs.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterServiceImp {

    private final RegisterRepository registerRepository;

    private final RegisterConverter registerConverter;
}
