package org.skytech.kefu.common.service;

import org.skytech.kefu.common.entity.User;

public interface UserService {

    User findOneById(String id);

    User findTopByUsernameAndUsertype(String username,String usertype);
}
