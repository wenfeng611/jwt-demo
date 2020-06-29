package org.skytech.kefu.common.utils;

import org.skytech.kefu.common.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() { }

    public static User getLoginUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (auth == null) return null;
        Object principal = auth.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            return null;
        }
    }


    public static String getOrgId() {
        User user = getLoginUser();
        assert user != null;
        return user.getOrgi();
    }

}
