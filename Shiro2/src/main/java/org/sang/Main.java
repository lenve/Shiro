package org.sang;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sang on 17-3-19.
 */
public class Main {

    @Test
    public void test1() {
        login("classpath:shiro.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        //判断是否具备某一种权限
        Assert.assertTrue(subject.hasRole("role1"));//OK
        Assert.assertTrue(subject.hasRole("role2"));//OK
        List<String> roles = new ArrayList<String>();
        roles.add("role1");
        roles.add("role2");
        roles.add("role3");
        //判断是否具备某一组权限
        Assert.assertArrayEquals(new boolean[]{true, true, false}, subject.hasRoles(roles));//OK
    }

    @Test
    public void test2() {
        login("classpath:shiro-res.ini", "wang", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:create"));//OK
        Assert.assertFalse(subject.isPermittedAll("user:delete", "user:update"));//OK
        Assert.assertFalse(subject.isPermitted("user:view"));//OK
    }

    @Test
    public void role3Test() {
        login("classpath:shiro-res.ini", "user_role3", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("system:user:update"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:delete"));//OK
    }

    @Test
    public void role31Test() {
        login("classpath:shiro-res.ini", "user_role31", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("system:user:update"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:delete"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete"));//OK
    }

    @Test
    public void role4Test() {
        login("classpath:shiro-res.ini", "user_role4", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("system:user:update"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:delete"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:view"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete,view,create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete,create"));//OK
    }

    @Test
    public void role41Test() {
        login("classpath:shiro-res.ini", "user_role41", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("system:user:update"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:delete"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:view"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete,view,create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete,create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:*"));//OK
        Assert.assertTrue(subject.isPermitted("system:user"));//OK
    }

    @Test
    public void role42Test() {
        login("classpath:shiro-res.ini", "user_role42", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("system:user:update"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:delete"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:view"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete,view,create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:update,delete,create"));//OK
        Assert.assertTrue(subject.isPermitted("system:user:*"));//OK
        Assert.assertTrue(subject.isPermitted("system:user"));//OK
    }

    @Test
    public void role5Test() {
        login("classpath:shiro-res.ini", "user_role5", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:view"));//OK
    }

    @Test
    public void role51Test() {
        login("classpath:shiro-res.ini", "user_role51", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("system:user:view"));//OK
    }

    @Test
    public void role6Test() {
        login("classpath:shiro-res.ini", "user_role6", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:view:1"));//OK
    }

    @Test
    public void role61Test() {
        login("classpath:shiro-res.ini", "user_role61", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:update:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:delete:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:update,delete:1"));//OK
    }

    @Test
    public void role62Test() {
        login("classpath:shiro-res.ini", "user_role62", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:update:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:delete:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:view:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:create:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:create,view,update,delete:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:update,delete:1"));//OK
    }

    @Test
    public void role63Test() {
        login("classpath:shiro-res.ini", "user_role63", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:view:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:view:2"));//OK
        Assert.assertTrue(subject.isPermitted("user:view:3"));//OK
        Assert.assertTrue(subject.isPermitted("user:view:4"));//OK
    }

    @Test
    public void role64Test() {
        login("classpath:shiro-res.ini", "user_role64", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user:view:1"));//OK
        Assert.assertTrue(subject.isPermitted("user:update:2"));//OK
        Assert.assertTrue(subject.isPermitted("user:create:3"));//OK
        Assert.assertTrue(subject.isPermitted("user:delete:4"));//OK
    }

    @Test
    public void test99() {
        login("classpath:shiro-custom.ini", "wang", "123");
        Subject subject = SecurityUtils.getSubject();
        Assert.assertTrue(subject.isPermitted("user1:update"));//OK
        Assert.assertTrue(subject.isPermitted("user2:update"));//OK
        Assert.assertTrue(subject.isPermitted("+user1+2"));//OK
        Assert.assertTrue(subject.isPermitted("+user1+8"));//OK
        Assert.assertTrue(subject.isPermitted("+user2+10"));//OK
        Assert.assertTrue(subject.isPermitted("+user1+4"));
        Assert.assertTrue(subject.isPermitted("menu:view"));//OK
    }


    private void login(String iniFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            e.printStackTrace();
        }
    }
}
