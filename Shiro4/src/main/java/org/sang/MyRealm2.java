package org.sang;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * Created by sang on 17-3-22.
 */
public class MyRealm2 extends AuthenticatingRealm {

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyRealm2");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        System.out.println("操作数据库处理数据---------");
        if ("unknow".equals(username)) {
            throw new UnknownAccountException("用户名输入错误");
        }
        if ("monster".equals(username)) {
            throw new LockedAccountException("用户被锁定了");
        }
        System.out.println(upToken.getPassword());
        Object principal = username;
        Object credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06---------";
        ByteSource salt = ByteSource.Util.bytes(username);

        //返回的用户名和密码都是数据库查询出来的
        return new SimpleAuthenticationInfo(principal, credentials, salt, getName());
    }

    public static void main(String[] args) {
        String hashAlgorithmName = "SHA-1";
        Object credentials = "123456";
        int hashIterations = 1024;
        String salt = "admin";
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
