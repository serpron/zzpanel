package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.User;
import zz.util.Page;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserService userService;

    @Before
    public void init(){
        userService = (UserService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("userService");
    }

    @Test
    public void findAll() {
        Page<User> page = this.userService.find(null,1,10);
        Assert.assertNotNull(page);
    }

    @Test
    public void generateSalt(){
        for(int i=0;i<10;i++){
            System.out.println(userService.generateSalt("admin"));
        }
    }

    @Test
    public void generateHex(){
        String account = "admin";
        String pass = "123";
        String salt = "07a60384899bb7fa8df74e8244d6c4ef"; // userService.generateSalt(pass);
        String securityPass = userService.generateHex(pass, salt);
        System.out.println(securityPass);
        Assert.assertEquals("f400afd7899daeb045f0c1be1907678e",securityPass);
    }

    @Test
    public void changePass(){
        String pass = "123";
        List<User> list = this.userService.find(null);
        for(User user : list) {
            this.userService.changePass(user.getAccount(), pass);
        }
    }
    @Test
    public void login(){
        String account = "admin";
        String pass = "123";
        User user = this.userService.login(account,pass);
        Assert.assertNotNull(user);
    }

    @Test
    public void find(){
        Page<User> page = this.userService.find(null,1,10);
        Assert.assertNotNull(page);
        Assert.assertNotNull(page.getList());
    }

    @Test
    public void findAllRoles(){
        Set<String> roles = userService.findAllRoles("linghuchong");
        Assert.assertNotNull(roles);
        Assert.assertEquals(2,roles.size());
    }

    @Test
    public void findAllPermissions(){
        Set<String> roles = userService.findAllPermissions("linghuchong");
        Assert.assertNotNull(roles);
        Assert.assertEquals(3,roles.size());
    }
}