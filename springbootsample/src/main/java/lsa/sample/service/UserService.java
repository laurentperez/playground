package lsa.sample.service;

import lsa.sample.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by laurent on 17/07/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> loadAll() {
        return userRepository.findAll();
    }
}
