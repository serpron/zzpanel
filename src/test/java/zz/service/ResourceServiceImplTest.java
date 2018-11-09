package zz.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zz.entity.Resource;

import java.util.List;

import static org.junit.Assert.*;

public class ResourceServiceImplTest {

    private ResourceService resourceService;
    @Before
    public void setup(){
        resourceService = (ResourceService) new ClassPathXmlApplicationContext("spring-config.xml").getBean("resourceService");
    }
    @Test
    public void find() {
        List<Resource> list = this.resourceService.find(null);
        Assert.assertNotNull(list);
    }

}