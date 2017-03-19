package org.sang;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by sang on 17-3-18.
 */
public class Main {
    @Test
    public void test1() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zuolengchan", "456");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            //登录失败
            System.out.println("登录失败");
            e.printStackTrace();
        }
        subject.logout();
    }

    @Test
    public void test2() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "456");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            //登录失败
            System.out.println("登录失败");
            e.printStackTrace();
        }
        subject.logout();
    }

    @Test
    public void test3() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "111");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            //登录失败
            System.out.println("登录失败");
            e.printStackTrace();
        }
        subject.logout();
    }

    @Test
    public void test4() {
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        Iterator iterator = principals.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void login(String configFile) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
    }

}
