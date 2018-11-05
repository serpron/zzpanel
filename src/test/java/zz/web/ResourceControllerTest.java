package zz.web;

import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zz.entity.Resource;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml","classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    public void find() throws Exception {
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/resources")
                .accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON));
        ra.andExpect(MockMvcResultMatchers.jsonPath("$.count").value(4));
        String json = ra.andReturn().getResponse().getContentAsString();
        LinkedHashMap<String,Resource> list = JsonPath.read(json,"$");
        Assert.assertNotNull(list);
    }

    @Test
    public void add() throws Exception {
        ResultActions ra =mockMvc.perform(MockMvcRequestBuilders.post("/resources")
                .accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .param("url","/permissions")
                .param("menu_name","权限列表")
                .param("parent_id","1")
                .param("create_time","2018-10-29")
                .param("sequence","15"));
        String json = ra.andReturn().getResponse().getContentAsString();
        LinkedHashMap<String,Resource> lst = JsonPath.read(json,"$");
        Assert.assertNotNull(lst);
        Assert.assertEquals(200,lst.get("code"));
    }

    @Test
    public void update() throws Exception {
        ResultActions ra =mockMvc.perform(MockMvcRequestBuilders.put("/resources")
                .accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .param("id","6")
                .param("url","/permission")
                .param("menu_name","权限列表1")
                .param("parent_id","1")
                .param("sequence","15"));
        String json = ra.andReturn().getResponse().getContentAsString();
        LinkedHashMap<String,Resource> lst = JsonPath.read(json,"$");
        Assert.assertNotNull(lst);
        Assert.assertEquals(200,lst.get("code"));
    }

    @Test
    public void delete() throws Exception {
        ResultActions ra =mockMvc.perform(MockMvcRequestBuilders.delete("/resources/6")
                .accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON));
        String json = ra.andReturn().getResponse().getContentAsString();
        LinkedHashMap<String,Resource> lst = JsonPath.read(json,"$");
        Assert.assertNotNull(lst);
        Assert.assertEquals(200,lst.get("code"));
    }
}