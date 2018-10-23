package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.TreeselectNode;

import java.util.List;

import static org.junit.Assert.*;

public class TreeselectNodeServiceImplTest {

    private TreeselectNodeService treeselectNodeService;

    @Before
    public void init(){
        treeselectNodeService = (TreeselectNodeService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("treeselectNodeService");
    }
    @Test
    public void generateDepartmentTree() {
        List<TreeselectNode> list = treeselectNodeService.generateDepartmentTree();
        Assert.assertNotNull(list);
    }
}