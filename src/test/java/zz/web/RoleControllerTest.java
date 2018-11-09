package zz.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zz.entity.Role;
import zz.entity.User;
import zz.util.WebResult;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml","classpath:spring-mvc.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // 设定单元测试顺序
public class RoleControllerTest {

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test1add() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/roles").accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name","ddd")
                .param("users","5,9,11"))
                .andReturn();
        JSONObject obj = (JSONObject) JSON.parse(result.getResponse().getContentAsString());
        Integer id = (Integer) obj.getJSONObject("data").get("id");
        Assert.assertNotNull(id);
    }

    @Test
    public void test2update() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/roles").accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name","总经理1")
                .param("id","22"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }
    @Test
    public void test3findById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/roles/"+22).accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("总经理1"));
    }
    @Test
    public void test4find() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/roles").accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(4));
    }

    @Test
    public void findRoleUsers() throws Exception {
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/roles/28/users").accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON));
        String json = ra.andReturn().getResponse().getContentAsString();
        System.out.println(json);
        //ra.andExpect(MockMvcResultMatchers.jsonPath("$.count").value(3));
    }

    @Test
    public void test5delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/roles/22").accept("application/json;charset=utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200));
    }

}