package org.sang;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by sang on 17-3-22.
 */
@Controller
public class HelloController {
    @Autowired
    TestService testService;

    @RequestMapping("/roleTest")
    public String roleTest(HttpSession session) {
        session.setAttribute("username", "sang");
        testService.testRole();
        return "redirect:/list.jsp";
    }

    @RequestMapping("/user")
    public String user() {
        System.out.println("----------");
        return "user";
    }

    @RequestMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                token.setRememberMe(true);
                subject.login(token);
            } catch (AuthenticationException e) {
                System.out.println("登录失败:" + e.getMessage());
            }
        }
        return "redirect:/list.jsp";
    }
}
