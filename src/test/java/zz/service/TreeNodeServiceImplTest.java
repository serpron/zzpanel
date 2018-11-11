package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.TreeNode;

import java.util.List;
import java.util.Map;

public class TreeNodeServiceImplTest {

    private TreeMapService treeNodeService;

    @Before
    public void init(){
        treeNodeService = (TreeMapService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("treeNodeService");
    }
    @Test
    public void generateDepartmentTree() {
        List<Map<String,Object>> list = treeNodeService.generateDepartmentTree(null);
        Assert.assertNotNull(list);
    }

    @Test
    public void generateResourcesTree(){
        List<Map<String,Object>> list = treeNodeService.generateResourcesTree(null);
        Assert.assertNotNull(list);
    }
}