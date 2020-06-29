package org.skytech.kefu.customer.controller;

import org.skytech.kefu.common.entity.User;
import org.skytech.kefu.common.utils.SecurityUtils;

public class BaseApiController {

    public User getUser() {
        return SecurityUtils.getLoginUser();
    }

    public String getOrgId() {

        User user = getUser();
        if (user == null) {
            return null;
        }
        return user.getOrgi();
    }
}
