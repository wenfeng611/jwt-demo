package org.skytech.kefu.common.repository;

import org.skytech.kefu.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findTopByUsernameAndUsertype(String username, String usertype);
}
