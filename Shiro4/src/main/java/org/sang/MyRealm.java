package org.sang;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sang on 17-3-22.
 */
public class MyRealm extends AuthorizingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyRealm");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        System.out.println("操作数据库处理数据---------");
        if ("unknow".equals(username)) {
            throw new UnknownAccountException("用户名输入错误");
        }
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定了");
        }
        Object credentials = null;
        if ("admin".equals(username)) {
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }
        if ("user".equals(username)) {
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }
        System.out.println(upToken.getPassword());
        Object principal = username;
        ByteSource salt = ByteSource.Util.bytes(username);

        //返回的用户名和密码都是数据库查询出来的
        return new SimpleAuthenticationInfo(principal, credentials, salt, getName());
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        int hashIterations = 1024;
        String salt = "user";
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<String>();
        roles.add("user");
        if ("admin".equals(principal)) {
            roles.add("admin");
        }
        return new SimpleAuthorizationInfo(roles);
    }
}
