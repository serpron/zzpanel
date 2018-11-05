package zz.web;

import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zz.entity.Resource;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml","classpath:spring-mvc.xml"})
public class DictionaryControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void find() throws Exception {
        ResultActions rac = mockMvc.perform(MockMvcRequestBuilders.get("/dictionaries")
                        .param("typename","menu_type")
                        .accept("application/json;charset=utf-8")
                        .contentType(MediaType.APPLICATION_JSON));
        String jsonString = rac.andReturn().getResponse().getContentAsString();
        LinkedHashMap<String,Map<String,String>> lhm = JsonPath.read(jsonString,"$");
        Assert.assertNotNull(lhm);
    }
}