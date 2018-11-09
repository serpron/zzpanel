package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.TreeNode;

import java.util.List;

public class TreeNodeServiceImplTest {

    private TreeNodeService treeNodeService;

    @Before
    public void init(){
        treeNodeService = (TreeNodeService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("treeNodeService");
    }
    @Test
    public void generateDepartmentTree() {
        List<TreeNode> list = treeNodeService.generateDepartmentTree(null);
        Assert.assertNotNull(list);
    }

    @Test
    public void generateResourcesTree(){
        List<TreeNode> list = treeNodeService.generateResourcesTree(null);
        Assert.assertNotNull(list);
    }
}