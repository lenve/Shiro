package org.sang;

import java.util.LinkedHashMap;

/**
 * Created by sang on 17-3-23.
 */
public class FilterChainDefinitionMapFactory {
    public LinkedHashMap<String, String> filterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("/login.jsp", "anon");
        map.put("/login", "anon");
        map.put("/user", "roles[user]");
        map.put("/admin.jsp", "roles[admin]");
        map.put("/logout", "logout");
        map.put("/**", "authc");
        return map;
    }
}
