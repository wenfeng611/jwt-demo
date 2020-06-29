package org.skytech.kefu.common.service.impl;

import org.skytech.kefu.common.entity.User;
import org.skytech.kefu.common.repository.UserRepository;
import org.skytech.kefu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOneById(String id) {
        return userRepository.findById(id).orElseGet(null);
    }

    @Override
    public User findTopByUsernameAndUsertype(String username, String usertype) {
        return userRepository.findTopByUsernameAndUsertype(username,usertype);
    }
}
