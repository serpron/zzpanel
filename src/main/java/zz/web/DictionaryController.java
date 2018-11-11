package zz.web;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zz.util.DictionaryService;
import zz.util.WebResult;

import java.util.Map;

@Controller
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequiresRoles("admin")
    @ResponseBody
    @RequestMapping(value = "/dictionaries",method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    public WebResult<Map<String,String>> find(String typename){
        Map<String,String> result = dictionaryService.getProperties(typename);
        return new WebResult<>(result);
    }

    public DictionaryService getDictionaryService() {
        return dictionaryService;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
