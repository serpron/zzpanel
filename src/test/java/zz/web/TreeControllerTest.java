package zz.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

// 导入spring的配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "classpath:spring-config.xml","classpath:spring-mvc.xml"
 })
public class TreeControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc
    }

    @Test
    public void findAllDepartmentsWithTree() throws Exception {
        /**
         * 1、使用mockMvc的perform方法发送请求
         * 2、MockMvcRequestBuilders用来发送各种请求，这里的get当然发送get请求
         * 3、accept方法用来设置响应类型、header用来设置请求头（增加data=tree）
         * 4、andExpect用来断言
         * 5、MockMvcResultMatchers的静态方法用来对相应结果进行各种分析
         */
        this.mockMvc.perform(MockMvcRequestBuilders.get("/departments").accept("application/json;charset=UTF-8")
                .header("data","tree").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

}