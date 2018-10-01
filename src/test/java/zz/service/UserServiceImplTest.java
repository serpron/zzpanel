package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.User;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserService userService;

    @Before
    public void init(){
        userService = (UserService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("userService");
    }

    @Test
    public void findAll() {
        List<User> list = this.userService.findAll();
        Assert.assertNotNull(list);
    }
}