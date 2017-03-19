package org.sang;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by sang on 17-3-18.
 */
public class MyRealm1 implements Realm {

    public String getName() {
        return "myrealm1";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String(((char[]) token.getCredentials()));
        if (!"wang".equals(username)) {
            //用户名错误
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            //密码错误
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
