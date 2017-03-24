package org.sang;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 * Created by sang on 17-3-23.
 */
public class TestService {
    @RequiresRoles({"admin"})
    public void testRole() {
        System.out.println("testRole");
        Object key = SecurityUtils.getSubject().getSession().getAttribute("username");
        System.out.println(key);
    }
}
