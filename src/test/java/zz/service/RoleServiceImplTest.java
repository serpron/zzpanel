package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.TreeNode;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class RoleServiceImplTest {

    @Autowired
    private RoleService roleService;

    @Before
    public void setup(){
        roleService = (RoleService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("roleService");
    }
    @Test
    public void findRoleResources() {
        List<TreeNode> list = roleService.findRoleResources(31);
        Assert.assertNotNull(list);
    }
}