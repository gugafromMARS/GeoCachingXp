package gsc.projects.usersmcs.service;


import gsc.projects.usersmcs.converter.UserConverter;
import gsc.projects.usersmcs.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImp {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

}
